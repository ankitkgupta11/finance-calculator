package com.finapp.finance_calculator.dto;

public class SwpRequestDTO {
    private double initialInvestment;
    private double annualRate;
    private double monthlyWithdrawal;
    private int durationMonths;
	public double getInitialInvestment() {
		return initialInvestment;
	}
	public void setInitialInvestment(double initialInvestment) {
		this.initialInvestment = initialInvestment;
	}
	public double getAnnualRate() {
		return annualRate;
	}
	public void setAnnualRate(double annualRate) {
		this.annualRate = annualRate;
	}
	public double getMonthlyWithdrawal() {
		return monthlyWithdrawal;
	}
	public void setMonthlyWithdrawal(double monthlyWithdrawal) {
		this.monthlyWithdrawal = monthlyWithdrawal;
	}
	public int getDurationMonths() {
		return durationMonths;
	}
	public void setDurationMonths(int durationMonths) {
		this.durationMonths = durationMonths;
	}
    
    
    
}

