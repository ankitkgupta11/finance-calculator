package com.finapp.finance_calculator.fd;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.finapp.finance_calculator.dto.FdRequestDTO;
import com.finapp.finance_calculator.dto.FdResponseDTO;

@Service
public class FdCalculatorSVC {

	public FdResponseDTO calculateFd(FdRequestDTO request) {

		double principal = request.getPrincipal();
        double rate = request.getAnnualRate() / 100;

        LocalDate startDate = request.getStartDate();
        LocalDate maturityDate = startDate.plusDays(request.getTenureDays());

        double years = daysToYears(request.getTenureDays(), startDate);

        FdPayoutType payoutType =
                FdPayoutType.valueOf(request.getCompounding().toUpperCase());

        FdResponseDTO response = new FdResponseDTO();
        response.setPrincipal(round(principal));
        response.setPayoutType(payoutType.name());
        response.setMaturityDate(maturityDate);

        if (payoutType == FdPayoutType.AT_MATURITY) {
        	int n = 4; // quarterly
        	double years1 = request.getTenureDays() / 365.0;

        	double maturityAmount =
        	        principal * Math.pow(1 + rate / n, n * years1);
            response.setMaturityAmount(round(maturityAmount));
            response.setTotalInterestEarned(round(maturityAmount - principal));
            response.setPeriodicPayout(0);

        } else {
            double annualInterest = principal * rate;
            double totalInterest = annualInterest * years;

            double payoutPerPeriod =
                    annualInterest / payoutType.getPeriods();

            response.setMaturityAmount(round(principal)); // principal returned
            response.setTotalInterestEarned(round(totalInterest));
            response.setPeriodicPayout(round(payoutPerPeriod));
        }

        return response;
    }

    private double daysToYears(int days, LocalDate startDate) {
        int daysInYear = startDate.isLeapYear() ? 366 : 365;
        return (double) days / daysInYear;
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

}
