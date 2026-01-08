package com.finapp.finance_calculator.dto;

public class SipRequestDTO {
    private double monthlyInvestment;
    private double annualRate;
    private int durationYears;
	public double getMonthlyInvestment() {
		return monthlyInvestment;
	}
	public void setMonthlyInvestment(double monthlyInvestment) {
		this.monthlyInvestment = monthlyInvestment;
	}
	public double getAnnualRate() {
		return annualRate;
	}
	public void setAnnualRate(double annualRate) {
		this.annualRate = annualRate;
	}
	public int getDurationYears() {
		return durationYears;
	}
	public void setDurationYears(int durationYears) {
		this.durationYears = durationYears;
	}
    

}
