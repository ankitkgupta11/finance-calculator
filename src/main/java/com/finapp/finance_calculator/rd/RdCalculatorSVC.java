package com.finapp.finance_calculator.rd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.finapp.finance_calculator.dto.RdRequestDTO;
import com.finapp.finance_calculator.dto.RdResponseDTO;
import com.finapp.finance_calculator.dto.RdYearlyBreakdown;

@Service
public class RdCalculatorSVC {

	public RdResponseDTO calculateRd(RdRequestDTO request) {

        double monthlyDeposit = request.getMonthlyDeposit();
        double rate = request.getAnnualRate() / 100;
        int months = request.getTenureMonths();

        double totalDeposit = monthlyDeposit * months;
        double maturityAmount = 0;

        int n = 4; // quarterly compounding
        double quarterlyRate = rate / n;

        List<RdYearlyBreakdown> breakdownList = new ArrayList<>();

        for (int m = 1; m <= months; m++) {

            // Deposit added every month
            maturityAmount += monthlyDeposit;

            // Apply quarterly interest
            if (m % 3 == 0) {
                maturityAmount += maturityAmount * quarterlyRate;
            }

            // Yearly breakdown
            if (m % 12 == 0 || m == months) {

                double interestTillNow = maturityAmount - (monthlyDeposit * m);

                breakdownList.add(new RdYearlyBreakdown(
                        request.getStartDate().getYear() + (m / 12),
                        round(monthlyDeposit * m),
                        round(interestTillNow),
                        round(maturityAmount)
                ));
            }
        }

        RdResponseDTO response = new RdResponseDTO();
        response.setTotalDeposit(round(totalDeposit));
        response.setMaturityAmount(round(maturityAmount));
        response.setInterestEarned(round(maturityAmount - totalDeposit));
        response.setMaturityDate(
                request.getStartDate().plusMonths(months)
        );
        response.setYearlyBreakdown(breakdownList);

        return response;
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

}
