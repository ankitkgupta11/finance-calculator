package com.finapp.finance_calculator.dto;

public class LoanEligibilityResponseDTO {
	
	private boolean eligible;
    private double maxEligibleLoanAmount;
    private double maxAllowedEmi;
    private double foirPercentage;
    private String message;
    
	public boolean isEligible() {
		return eligible;
	}
	public void setEligible(boolean eligible) {
		this.eligible = eligible;
	}
	public double getMaxEligibleLoanAmount() {
		return maxEligibleLoanAmount;
	}
	public void setMaxEligibleLoanAmount(double maxEligibleLoanAmount) {
		this.maxEligibleLoanAmount = maxEligibleLoanAmount;
	}
	public double getMaxAllowedEmi() {
		return maxAllowedEmi;
	}
	public void setMaxAllowedEmi(double maxAllowedEmi) {
		this.maxAllowedEmi = maxAllowedEmi;
	}
	public double getFoirPercentage() {
		return foirPercentage;
	}
	public void setFoirPercentage(double foirPercentage) {
		this.foirPercentage = foirPercentage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
      
    

}
