package com.finapp.finance_calculator.lumpsum;

import com.finapp.finance_calculator.dto.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LumpSumCalculatorService {

    public LumpSumResponseDTO calculateLumpSum(LumpSumRequestDTO request) {
        double principal = request.getInvestmentAmount();
        double rate = request.getAnnualRate() / 100;
        int years = request.getDurationYears();

        double maturity = principal * Math.pow(1 + rate, years);
        double interest = maturity - principal;

        List<LumpSumYearlyBreakdown> breakdownList = new ArrayList<>();
        for (int year = 1; year <= years; year++) {
            double yearMaturity = principal * Math.pow(1 + rate, year);
            double yearInterest = yearMaturity - principal;
            breakdownList.add(new LumpSumYearlyBreakdown(year, round(yearInterest), round(yearMaturity)));
        }

        return new LumpSumResponseDTO(
                round(principal),
                round(interest),
                round(maturity),
                breakdownList
        );
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
