package com.shizy.demo.mortgage.bean;

import java.util.List;

public class Mortgage {
	
	private double capital;
	private double interest;
	private int period;
	
	private List<MonthlyPayment> monthlyPaymentList;

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public List<MonthlyPayment> getMonthlyPaymentList() {
		return monthlyPaymentList;
	}

	public void setMonthlyPaymentList(List<MonthlyPayment> monthlyPaymentList) {
		this.monthlyPaymentList = monthlyPaymentList;
	}
	
}
