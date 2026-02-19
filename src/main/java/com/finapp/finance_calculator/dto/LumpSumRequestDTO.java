package com.finapp.finance_calculator.dto;

public class LumpSumRequestDTO {
    private double investmentAmount;
    private double annualRate;
    private int durationYears;
	public double getInvestmentAmount() {
		return investmentAmount;
	}
	public void setInvestmentAmount(double investmentAmount) {
		this.investmentAmount = investmentAmount;
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
