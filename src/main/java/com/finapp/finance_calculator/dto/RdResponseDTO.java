package com.finapp.finance_calculator.dto;

import java.time.LocalDate;
import java.util.List;

public class RdResponseDTO {

	 private double totalDeposit;
	 private double interestEarned;
	 private double maturityAmount;
	 private LocalDate maturityDate;
	 
	 private List<RdYearlyBreakdown> yearlyBreakdown;
	
	 public double getTotalDeposit() {
		return totalDeposit;
	}
	public void setTotalDeposit(double totalDeposit) {
		this.totalDeposit = totalDeposit;
	}
	public double getInterestEarned() {
		return interestEarned;
	}
	public void setInterestEarned(double interestEarned) {
		this.interestEarned = interestEarned;
	}
	public double getMaturityAmount() {
		return maturityAmount;
	}
	public void setMaturityAmount(double maturityAmount) {
		this.maturityAmount = maturityAmount;
	}
	public LocalDate getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}
	public List<RdYearlyBreakdown> getYearlyBreakdown() {
		return yearlyBreakdown;
	}
	public void setYearlyBreakdown(List<RdYearlyBreakdown> yearlyBreakdown) {
		this.yearlyBreakdown = yearlyBreakdown;
	}
	 
	 
	 
	    
}
