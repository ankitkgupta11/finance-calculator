package com.finapp.finance_calculator.emi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.finapp.finance_calculator.dto.EMIRequestDTO;
import com.finapp.finance_calculator.dto.EMIResponseDTO;
import com.finapp.finance_calculator.dto.EMIYearlyBreakDownDTO;

public class EMICalculatorSVC {

	private static final Map<String, Double> defaultRates = Map.of("home", 8.5, "gold", 9.0, "vehicle", 9.5, "personal",
			11.5, "education", 10.0);

	private static final Map<String, Double> defaultPrincipal = Map.of("home", 3000000.00, "gold", 500000.0, "vehicle",
			200000.0, "personal", 500000.0, "education", 400000.0);

	private static final Map<String, Integer> defaultYears = Map.of("home", 30, "gold", 2, "vehicle", 7, "personal", 5,
			"education", 8);

	public static EMIResponseDTO calculateEMI(EMIRequestDTO request) {
        String loanType = Optional.ofNullable(request.getLoanType()).orElse("home").toLowerCase();

		Double requestPrincipal = request.getPrincipal();
		double principal = (requestPrincipal == null || requestPrincipal == 0.0) ? defaultPrincipal.get(loanType)
				: requestPrincipal;

		Double requestRate = request.getAnnualRate();
		double annualRate = (requestRate == null || requestRate == 0.0) ? defaultRates.get(loanType) : requestRate;

		Integer requestdurationValue = request.getDurationValue();

		int durationValue = (requestdurationValue == null || requestdurationValue == 0.0) ? defaultYears.get(loanType)
				: requestdurationValue;

		String durationUnit = Optional.ofNullable(request.getDurationUnit()).orElse("years");

		int months = convertToMonths(durationValue, durationUnit);

		double monthlyRate = annualRate / 12 / 100;
		double emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, months))
				/ (Math.pow(1 + monthlyRate, months) - 1);

		double remainingPrincipal = principal;

		String requestStartDate = (request.getStartDate() == null) ? LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
				: request.getStartDate();
		LocalDate startDate = LocalDate.parse(requestStartDate, DateTimeFormatter.ISO_LOCAL_DATE);
		Map<Integer, EMIYearlyBreakDownDTO> yearlyMap = new LinkedHashMap<>();

		for (int i = 1; i <= months; i++) {
			double interest = remainingPrincipal * monthlyRate;
			double principalComponent = emi - interest;
			remainingPrincipal -= principalComponent;
 
			LocalDate currentMonth = startDate.plusMonths(i - 1);
			int year = currentMonth.getYear();

			EMIYearlyBreakDownDTO summary = yearlyMap.getOrDefault(year, new EMIYearlyBreakDownDTO());
			summary.setYear(year);
			summary.setPrincipalPaid(summary.getPrincipalPaid() + principalComponent);
			summary.setInterestPaid(summary.getInterestPaid() + interest);
			summary.setTotalPaid(summary.getTotalPaid() + emi);
			summary.setRemainingPrincipal(Math.max(remainingPrincipal, 0));

			yearlyMap.put(year, summary);
		}

		EMIResponseDTO response = new EMIResponseDTO();
		response.setEmi(emi);
		response.setTotalAmount(emi * months);
		response.setTotalInterest((emi * months) - principal);
		response.setSchedule(new ArrayList<>(yearlyMap.values()));

		return response;
	}

	private static int convertToMonths(int value, String unit) {
		return switch (unit.toLowerCase()) {
		case "years" -> value * 12;
		case "weeks" -> value * 12 / 52;
		case "days" -> value * 12 / 365;
		case "months" -> value;
		default -> throw new IllegalArgumentException("Invalid duration unit: " + unit);
		};
	}
}
