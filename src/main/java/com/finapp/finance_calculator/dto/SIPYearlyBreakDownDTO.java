package com.finapp.finance_calculator.dto;

public class SIPYearlyBreakDownDTO {
    private int year;
    private double investedAmount;
    private double interestEarned;
    private double totalValue;

    public SIPYearlyBreakDownDTO(int year, double investedAmount, double interestEarned, double totalValue) {
        this.year = year;
        this.investedAmount = investedAmount;
        this.interestEarned = interestEarned;
        this.totalValue = totalValue;
    }

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getInvestedAmount() {
		return investedAmount;
	}

	public void setInvestedAmount(double investedAmount) {
		this.investedAmount = investedAmount;
	}

	public double getInterestEarned() {
		return interestEarned;
	}

	public void setInterestEarned(double interestEarned) {
		this.interestEarned = interestEarned;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

    
}
