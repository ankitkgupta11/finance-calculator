package com.finapp.finance_calculator.dto;

import java.util.List;

public class SipResponseDTO {
    private double totalInvested;
    private double estimatedReturns;
    private double maturityValue;
    private List<SIPYearlyBreakDownDTO> yearlyBreakdown;

    public SipResponseDTO(double totalInvested, double estimatedReturns, double maturityValue,
                       List<SIPYearlyBreakDownDTO> yearlyBreakdown) {
        this.totalInvested = totalInvested;
        this.estimatedReturns = estimatedReturns;
        this.maturityValue = maturityValue;
        this.yearlyBreakdown = yearlyBreakdown;
    }

	public double getTotalInvested() {
		return totalInvested;
	}

	public void setTotalInvested(double totalInvested) {
		this.totalInvested = totalInvested;
	}

	public double getEstimatedReturns() {
		return estimatedReturns;
	}

	public void setEstimatedReturns(double estimatedReturns) {
		this.estimatedReturns = estimatedReturns;
	}

	public double getMaturityValue() {
		return maturityValue;
	}

	public void setMaturityValue(double maturityValue) {
		this.maturityValue = maturityValue;
	}

	public List<SIPYearlyBreakDownDTO> getYearlyBreakdown() {
		return yearlyBreakdown;
	}

	public void setYearlyBreakdown(List<SIPYearlyBreakDownDTO> yearlyBreakdown) {
		this.yearlyBreakdown = yearlyBreakdown;
	}

}
