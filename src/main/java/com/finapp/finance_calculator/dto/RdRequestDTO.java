package com.finapp.finance_calculator.dto;

import java.time.LocalDate;

public class RdRequestDTO {

	private double monthlyDeposit;
    private double annualRate;
    private int tenureMonths;
    private LocalDate startDate;
    
	public double getMonthlyDeposit() {
		return monthlyDeposit;
	}
	public void setMonthlyDeposit(double monthlyDeposit) {
		this.monthlyDeposit = monthlyDeposit;
	}
	public double getAnnualRate() {
		return annualRate;
	}
	public void setAnnualRate(double annualRate) {
		this.annualRate = annualRate;
	}
	public int getTenureMonths() {
		return tenureMonths;
	}
	public void setTenureMonths(int tenureMonths) {
		this.tenureMonths = tenureMonths;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	

	
}
