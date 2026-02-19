package com.finapp.finance_calculator.sip;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.finapp.finance_calculator.dto.SIPYearlyBreakDownDTO;
import com.finapp.finance_calculator.dto.SipRequestDTO;
import com.finapp.finance_calculator.dto.SipResponseDTO;

@Service
public class SipCalculatorSVC {

    public SipResponseDTO calculateSip(SipRequestDTO request) {

        double monthlyInvestment = request.getMonthlyInvestment();
        double annualRate = request.getAnnualRate();
        int totalMonths = request.getDurationMonth();

        double monthlyRate = annualRate / 12 / 100;

        double maturityValue = monthlyInvestment *
                (Math.pow(1 + monthlyRate, totalMonths) - 1)
                * (1 + monthlyRate) / monthlyRate;

        double totalInvested = monthlyInvestment * totalMonths;
        double estimatedReturns = maturityValue - totalInvested;

        List<SIPYearlyBreakDownDTO> breakdown = new ArrayList<>();

        int totalYears = totalMonths / 12;

        for (int year = 1; year <= totalYears; year++) {

            int months = year * 12;

            double value = monthlyInvestment *
                    (Math.pow(1 + monthlyRate, months) - 1)
                    * (1 + monthlyRate) / monthlyRate;

            double invested = monthlyInvestment * months;
            double interest = value - invested;

            breakdown.add(
                    new SIPYearlyBreakDownDTO(
                            year,
                            round(invested),
                            round(interest),
                            round(value)
                    )
            );
        }

        return new SipResponseDTO(
                round(totalInvested),
                round(estimatedReturns),
                round(maturityValue),
                breakdown
        );
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
