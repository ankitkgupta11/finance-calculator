package com.finapp.finance_calculator.dto;

import java.time.LocalDate;

public class FdRequestDTO {

	private double principal;
	private double annualRate;

	private int tenureDays;
	private String compounding;

	private LocalDate startDate;

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getAnnualRate() {
		return annualRate;
	}

	public void setAnnualRate(double annualRate) {
		this.annualRate = annualRate;
	}

	public int getTenureDays() {
		return tenureDays;
	}

	public void setTenureDays(int tenureDays) {
		this.tenureDays = tenureDays;
	}

	public String getCompounding() {
		return compounding;
	}

	public void setCompounding(String compounding) {
		this.compounding = compounding;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	// getters & setters
}
