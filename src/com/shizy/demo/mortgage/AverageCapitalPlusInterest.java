package com.shizy.demo.mortgage;

import java.util.ArrayList;
import java.util.List;

import com.shizy.demo.mortgage.bean.MonthlyPayment;
import com.shizy.demo.mortgage.bean.Mortgage;

/**
 * 等额本息
 * @author shizy
 *
 */
public class AverageCapitalPlusInterest implements MortgageCalculator {

	@Override
	public Mortgage calculate(double capital, double annualInterestRate,
			int period) {
		Mortgage mortgage = new Mortgage();
		
		final int monthOfYear = 12;
		double monthlyInterestRate = annualInterestRate / monthOfYear;
		
		MonthlyPayment mp = null;
		double paidCapital = 0;
		double totalInterest = 0;
		List<MonthlyPayment> list = new ArrayList<MonthlyPayment>(period);
		for (int i = 0; i < period; i++) {
			mp = getMonthlyPayment(capital, paidCapital, monthlyInterestRate, period, i + 1);
			list.add(mp);
			paidCapital += mp.getCapital();
			totalInterest += mp.getInterest();
		}
		
		mortgage.setCapital(capital);
		mortgage.setInterest(totalInterest);
		mortgage.setPeriod(period);
		mortgage.setMonthlyPaymentList(list);
		
		return mortgage;
	}

	private MonthlyPayment getMonthlyPayment(double totalCapital, double paidCapital, double monthlyInterestRate, int period, int month) {
		MonthlyPayment mp = new MonthlyPayment();
		
		double capitalPlusInterest = totalCapital * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, period) / (Math.pow(1 + monthlyInterestRate, period) - 1);
		double interest = (totalCapital - paidCapital) * monthlyInterestRate;
		double capital = capitalPlusInterest - interest;
		mp.setMonth(month);
		mp.setCapital(capital);
		mp.setInterest(interest);
		mp.setRemaining(totalCapital - paidCapital - capital);
		return mp;
	}
}
