package com.finapp.finance_calculator.dto;

public class LumpSumYearlyBreakdown {
    private int year;
    private double interestEarned;
    private double totalValue;

    public LumpSumYearlyBreakdown(int year, double interestEarned, double totalValue) {
        this.year = year;
        this.interestEarned = interestEarned;
        this.totalValue = totalValue;
    }

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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
