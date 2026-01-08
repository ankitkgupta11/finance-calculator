package com.finapp.finance_calculator.dto;

import java.util.List;

public class SwpResponseDTO{
    private double totalWithdrawn;
    private double finalValue;
    private List<SwpYearlyBreakdownDTO> breakdown;
    
	public double getTotalWithdrawn() {
		return totalWithdrawn;
	}
	public void setTotalWithdrawn(double totalWithdrawn) {
		this.totalWithdrawn = totalWithdrawn;
	}
	public double getFinalValue() {
		return finalValue;
	}
	public void setFinalValue(double finalValue) {
		this.finalValue = finalValue;
	}
	public List<SwpYearlyBreakdownDTO> getBreakdown() {
		return breakdown;
	}
	public void setBreakdown(List<SwpYearlyBreakdownDTO> breakdown) {
		this.breakdown = breakdown;
	}
    
}

    