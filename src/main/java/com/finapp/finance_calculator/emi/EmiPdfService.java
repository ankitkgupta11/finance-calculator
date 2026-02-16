package com.finapp.finance_calculator.emi;

import com.finapp.finance_calculator.dto.EMIRequestDTO;
import com.finapp.finance_calculator.dto.EMIResponseDTO;
import com.finapp.finance_calculator.dto.EMIYearlyBreakDownDTO;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;

import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmiPdfService {

    // ===== COLOR SYSTEM (MATCH IMAGE) =====
    private static final Color NAVY = new Color(23, 54, 93);
    private static final Color BG_LIGHT = new Color(245, 247, 250);
    private static final Color DIVIDER = new Color(220, 223, 228);
    private static final Color TEXT_MUTED = new Color(120, 120, 120);

    public static byte[] generateEmiPdf(EMIRequestDTO request, EMIResponseDTO response) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 36, 36, 36, 36);

        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();

            // ===== FONTS =====
            Font heroTitle = new Font(Font.HELVETICA, 18, Font.BOLD, Color.WHITE);
            Font heroSub = new Font(Font.HELVETICA, 11, Font.NORMAL, new Color(220, 220, 220));
            Font sectionTitle = new Font(Font.HELVETICA, 13, Font.BOLD, NAVY);
            Font labelFont = new Font(Font.HELVETICA, 9, Font.NORMAL, TEXT_MUTED);
            Font valueFont = new Font(Font.HELVETICA, 12, Font.BOLD, Color.BLACK);
            Font tableHeaderFont = new Font(Font.HELVETICA, 10, Font.BOLD, Color.WHITE);
            Font tableCellFont = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.BLACK);

            // ================= HERO =================
            PdfPTable hero = new PdfPTable(1);
            hero.setWidthPercentage(100);

            PdfPCell heroCell = new PdfPCell();
            heroCell.setBackgroundColor(NAVY);
            heroCell.setPadding(22);
            heroCell.setBorder(Rectangle.NO_BORDER);

            heroCell.addElement(new Paragraph("EMI CALCULATION", heroTitle));
            heroCell.addElement(new Paragraph("Loan Repayment Summary", heroSub));

            hero.addCell(heroCell);
            document.add(hero);
            document.add(Chunk.NEWLINE);

            // ================= LOAN OVERVIEW =================
            document.add(new Paragraph("Loan Overview", sectionTitle));

            LineSeparator sep = new LineSeparator();
            sep.setLineColor(DIVIDER);
            document.add(sep);
            document.add(Chunk.NEWLINE);

            PdfPTable summary = new PdfPTable(3);
            summary.setWidthPercentage(100);
            summary.setSpacingBefore(15);
            summary.setSpacingAfter(25);

            addCard(summary, "Customer", "Ankit Kumar", labelFont, valueFont);
            addCard(summary, "Loan Amount", format(request.getPrincipal()), labelFont, valueFont);
            addCard(summary, "Loan Amount", format(request.getPrincipal()), labelFont, valueFont);

            addCard(summary, "EMI", format(response.getEmi()), labelFont, valueFont);
            addCard(summary, "Tenure",
                    request.getDurationValue() + " Years",
                    labelFont, valueFont);
            addCard(summary, "Interest Rate",
                    request.getAnnualRate() + " %",
                    labelFont, valueFont);

            document.add(summary);

            // ================= EMI SCHEDULE =================
            document.add(new Paragraph("Year-wise EMI Schedule", sectionTitle));
            document.add(sep);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1, 2, 2, 2, 2});

            addHeader(table, "Year", tableHeaderFont);
            addHeader(table, "Principal", tableHeaderFont);
            addHeader(table, "Interest", tableHeaderFont);
            addHeader(table, "Total", tableHeaderFont);
            addHeader(table, "Balance", tableHeaderFont);

            List<EMIYearlyBreakDownDTO> schedule = response.getSchedule();
            int row = 0;

            for (EMIYearlyBreakDownDTO y : schedule) {
                Color bg = row % 2 == 0 ? Color.WHITE : BG_LIGHT;

                addCell(table, String.valueOf(y.getYear()), tableCellFont, bg);
                addCell(table, format(y.getPrincipalPaid()), tableCellFont, bg);
                addCell(table, format(y.getInterestPaid()), tableCellFont, bg);
                addCell(table, format(y.getTotalPaid()), tableCellFont, bg);
                addCell(table, format(y.getRemainingPrincipal()), tableCellFont, bg);

                row++;
            }

            document.add(table);

            // ================= FOOTER =================
            writer.setPageEvent(new PdfPageEventHelper() {
                @Override
                public void onEndPage(PdfWriter writer, Document document) {
                    ColumnText.showTextAligned(
                            writer.getDirectContent(),
                            Element.ALIGN_CENTER,
                            new Phrase("Finance Calculator · Generated on: "+ LocalDateTime.now(),
                                    new Font(Font.HELVETICA, 9, Font.NORMAL, TEXT_MUTED)),
                            297,
                            20,
                            0
                    );
                }
            });

            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Error generating EMI PDF", e);
        }

        return out.toByteArray();
    }

    // ================= HELPERS =================

    private static void addCard(PdfPTable table, String label, String value,
                                Font labelFont, Font valueFont) {

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BG_LIGHT);
        cell.setPadding(14);
        cell.setBorderColor(DIVIDER);
        cell.setBorderWidth(1f);

        Paragraph l = new Paragraph(label, labelFont);
        l.setAlignment(Element.ALIGN_CENTER);

        Paragraph v = new Paragraph(value, valueFont);
        v.setAlignment(Element.ALIGN_CENTER);

        cell.addElement(l);
        cell.addElement(v);

        table.addCell(cell);
    }

    private static void addHeader(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(NAVY);
        cell.setPadding(10);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
    }

    private static void addCell(PdfPTable table, String text, Font font, Color bg) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(bg);
        cell.setPadding(10);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
    }

    private static String format(double value) {
        return "₹ " + String.format("%,.2f", value);
    }
}