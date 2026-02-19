package com.finapp.finance_calculator.dto;

public class LoanEligibilityRequestDTO {

	private double monthlyIncome;
	private double existingEmi;
	private int cibilScore;

	private double annualRate;
	private int tenureYears;

	private int age;
	private String employmentType;

	public double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public double getExistingEmi() {
		return existingEmi;
	}

	public void setExistingEmi(double existingEmi) {
		this.existingEmi = existingEmi;
	}

	public int getCibilScore() {
		return cibilScore;
	}

	public void setCibilScore(int cibilScore) {
		this.cibilScore = cibilScore;
	}

	public double getAnnualRate() {
		return annualRate;
	}

	public void setAnnualRate(double annualRate) {
		this.annualRate = annualRate;
	}

	public int getTenureYears() {
		return tenureYears;
	}

	public void setTenureYears(int tenureYears) {
		this.tenureYears = tenureYears;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

}
