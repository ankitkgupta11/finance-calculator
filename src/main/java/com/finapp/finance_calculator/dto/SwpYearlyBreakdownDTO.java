package com.finapp.finance_calculator.dto;

public class SwpYearlyBreakdownDTO {
    private int year;
    private double totalWithdrawn;
    private double interestEarned;
    private double closingBalance;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getTotalWithdrawn() {
		return totalWithdrawn;
	}
	public void setTotalWithdrawn(double totalWithdrawn) {
		this.totalWithdrawn = totalWithdrawn;
	}
	public double getInterestEarned() {
		return interestEarned;
	}
	public void setInterestEarned(double interestEarned) {
		this.interestEarned = interestEarned;
	}
	public double getClosingBalance() {
		return closingBalance;
	}
	public void setClosingBalance(double closingBalance) {
		this.closingBalance = closingBalance;
	}
    
}
