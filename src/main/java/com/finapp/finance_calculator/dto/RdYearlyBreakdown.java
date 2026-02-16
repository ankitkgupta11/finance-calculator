package com.finapp.finance_calculator.dto;

public class RdYearlyBreakdown {
	private int year;
    private double totalDepositTillYear;
    private double interestTillYear;
    private double maturityValueTillYear;

    public RdYearlyBreakdown(int year,
                             double totalDepositTillYear,
                             double interestTillYear,
                             double maturityValueTillYear) {
        this.year = year;
        this.totalDepositTillYear = totalDepositTillYear;
        this.interestTillYear = interestTillYear;
        this.maturityValueTillYear = maturityValueTillYear;
    }

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getTotalDepositTillYear() {
		return totalDepositTillYear;
	}

	public void setTotalDepositTillYear(double totalDepositTillYear) {
		this.totalDepositTillYear = totalDepositTillYear;
	}

	public double getInterestTillYear() {
		return interestTillYear;
	}

	public void setInterestTillYear(double interestTillYear) {
		this.interestTillYear = interestTillYear;
	}

	public double getMaturityValueTillYear() {
		return maturityValueTillYear;
	}

	public void setMaturityValueTillYear(double maturityValueTillYear) {
		this.maturityValueTillYear = maturityValueTillYear;
	}
    
    
}
