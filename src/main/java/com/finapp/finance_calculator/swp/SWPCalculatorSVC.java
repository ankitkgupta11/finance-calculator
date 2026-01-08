package com.finapp.finance_calculator.swp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.finapp.finance_calculator.dto.EMIRequestDTO;
import com.finapp.finance_calculator.dto.EMIResponseDTO;
import com.finapp.finance_calculator.dto.EMIYearlyBreakDownDTO;
import com.finapp.finance_calculator.dto.SwpRequestDTO;
import com.finapp.finance_calculator.dto.SwpResponseDTO;
import com.finapp.finance_calculator.dto.SwpYearlyBreakdownDTO;

public class SWPCalculatorSVC {

	public SwpResponseDTO calculateSwp(SwpRequestDTO request) {
	    double balance = request.getInitialInvestment();
	    double rate = request.getAnnualRate() / 12 / 100;
	    double withdrawal = request.getMonthlyWithdrawal();
	    int months = request.getDurationMonths();

	    double totalWithdrawn = 0;
	    double totalInterest = 0;

	    List<SwpYearlyBreakdownDTO> breakdown = new ArrayList<>();

	    for (int m = 1; m <= months; m++) {
	        double interest = balance * rate;
	        balance += interest;

	        if (balance >= withdrawal) {
	            balance -= withdrawal;
	            totalWithdrawn += withdrawal;
	        } else {
	            totalWithdrawn += balance;
	            balance = 0;
	        }

	        totalInterest += interest;

	        if (m % 12 == 0 || m == months) {
	            SwpYearlyBreakdownDTO yb = new SwpYearlyBreakdownDTO();
	            yb.setYear((m / 12));
	            yb.setInterestEarned(totalInterest);
	            yb.setTotalWithdrawn(totalWithdrawn);
	            yb.setClosingBalance(balance);
	            breakdown.add(yb);

	            totalInterest = 0;
	        }

	        if (balance <= 0) break;
	    }

	    SwpResponseDTO response = new SwpResponseDTO();
	    response.setTotalWithdrawn(totalWithdrawn);
	    response.setFinalValue(balance);
	    response.setBreakdown(breakdown);
	    return response;
	}

}
