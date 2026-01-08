package com.finapp.finance_calculator.dto;

import java.util.List;

public class LumpSumResponseDTO {
    private double investedAmount;
    private double interestEarned;
    private double maturityValue;
    private List<LumpSumYearlyBreakdown> yearlyBreakdown;

    public LumpSumResponseDTO(double investedAmount, double interestEarned,
                           double maturityValue, List<LumpSumYearlyBreakdown> yearlyBreakdown) {
        this.investedAmount = investedAmount;
        this.interestEarned = interestEarned;
        this.maturityValue = maturityValue;
        this.yearlyBreakdown = yearlyBreakdown;
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

	public double getMaturityValue() {
		return maturityValue;
	}

	public void setMaturityValue(double maturityValue) {
		this.maturityValue = maturityValue;
	}

	public List<LumpSumYearlyBreakdown> getYearlyBreakdown() {
		return yearlyBreakdown;
	}

	public void setYearlyBreakdown(List<LumpSumYearlyBreakdown> yearlyBreakdown) {
		this.yearlyBreakdown = yearlyBreakdown;
	}

    
}
