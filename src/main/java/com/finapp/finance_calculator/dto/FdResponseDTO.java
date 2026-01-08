package com.finapp.finance_calculator.dto;

import java.time.LocalDate;

public class FdResponseDTO {

	private double principal;
	private double maturityAmount;
	private double totalInterestEarned;

	private double periodicPayout;
	private LocalDate maturityDate;
	private String payoutType;

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getMaturityAmount() {
		return maturityAmount;
	}

	public void setMaturityAmount(double maturityAmount) {
		this.maturityAmount = maturityAmount;
	}

	public double getTotalInterestEarned() {
		return totalInterestEarned;
	}

	public void setTotalInterestEarned(double totalInterestEarned) {
		this.totalInterestEarned = totalInterestEarned;
	}

	public double getPeriodicPayout() {
		return periodicPayout;
	}

	public void setPeriodicPayout(double periodicPayout) {
		this.periodicPayout = periodicPayout;
	}

	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getPayoutType() {
		return payoutType;
	}

	public void setPayoutType(String payoutType) {
		this.payoutType = payoutType;
	}
}
