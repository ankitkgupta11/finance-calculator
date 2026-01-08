package com.finapp.finance_calculator.dto;

public class EMIYearlyBreakDownDTO {
	private int year;
	private double principalPaid;
	private double interestPaid;
	private double totalPaid;
	private double remainingPrincipal;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrincipalPaid() {
		return principalPaid;
	}

	public void setPrincipalPaid(double principalPaid) {
		this.principalPaid = principalPaid;
	}

	public double getInterestPaid() {
		return interestPaid;
	}

	public void setInterestPaid(double interestPaid) {
		this.interestPaid = interestPaid;
	}

	public double getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(double totalPaid) {
		this.totalPaid = totalPaid;
	}

	public double getRemainingPrincipal() {
		return remainingPrincipal;
	}

	public void setRemainingPrincipal(double remainingPrincipal) {
		this.remainingPrincipal = remainingPrincipal;
	}

}
