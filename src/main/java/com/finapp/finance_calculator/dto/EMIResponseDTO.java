package com.finapp.finance_calculator.dto;

import java.util.List;

public class EMIResponseDTO {
	private double emi;
    private double totalInterest;
    private double totalAmount;
    private List<EMIYearlyBreakDownDTO> schedule;
	public double getEmi() {
		return emi;
	}
	public void setEmi(double emi) {
		this.emi = emi;
	}
	public double getTotalInterest() {
		return totalInterest;
	}
	public void setTotalInterest(double totalInterest) {
		this.totalInterest = totalInterest;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<EMIYearlyBreakDownDTO> getSchedule() {
		return schedule;
	}
	public void setSchedule(List<EMIYearlyBreakDownDTO> schedule) {
		this.schedule = schedule;
	}

    
    
}
