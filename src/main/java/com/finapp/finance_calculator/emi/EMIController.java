package com.finapp.finance_calculator.emi;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finapp.finance_calculator.dto.EMIRequestDTO;
import com.finapp.finance_calculator.dto.EMIResponseDTO;
import com.finapp.finance_calculator.repo.CalculationHistoryRepository;
import com.finapp.finance_calculator.vo.CalculationHistoryVO;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class EMIController {
    private final CalculationHistoryRepository calculationHistoryRepository;
    
    public EMIController(CalculationHistoryRepository calculationHistoryRepository) {
        this.calculationHistoryRepository = calculationHistoryRepository;
    }
    @PostMapping("/emi")
    public ResponseEntity<EMIResponseDTO> calculateEMI(@RequestBody EMIRequestDTO request) throws JsonProcessingException {
        EMIResponseDTO result = EMICalculatorSVC.calculateEMI(request);
        CalculationHistoryVO history = new CalculationHistoryVO();
        history.setCalculatorType("EMI");
        ObjectMapper objectMapper= new ObjectMapper();
        history.setRequestPayload(objectMapper.writeValueAsString(request));
        history.setResponsePayload(objectMapper.writeValueAsString(result));
        history.setUsername("admin");

        calculationHistoryRepository.save(history);
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("emi/download/pdf")
    public ResponseEntity<byte[]> downloadEmiPdf(@RequestBody EMIRequestDTO request) {

        EMIResponseDTO response = EMICalculatorSVC.calculateEMI(request);

        byte[] pdfBytes = null;
		try {
			pdfBytes = EmiPdfService.generateEmiPdf(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=emi-report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

}
