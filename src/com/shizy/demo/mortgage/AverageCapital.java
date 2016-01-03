package com.shizy.demo.mortgage;

import java.util.ArrayList;
import java.util.List;

import com.shizy.demo.mortgage.bean.MonthlyPayment;
import com.shizy.demo.mortgage.bean.Mortgage;

/**
 * 等额本金
 * @author shizy
 *
 */
public class AverageCapital implements MortgageCalculator {

	@Override
	public Mortgage calculate(double capital, double annualInterestRate,
			int period) {
		Mortgage mortgage = new Mortgage();
		
		final int monthOfYear = 12;
		double monthlyInterestRate = annualInterestRate / monthOfYear;
		
		MonthlyPayment mp = null;
		double totalInterest = 0;
		List<MonthlyPayment> list = new ArrayList<MonthlyPayment>(period);
		for (int i = 0; i < period; i++) {
			mp = getMonthlyPayment(capital, monthlyInterestRate, period, i + 1);
			list.add(mp);
			totalInterest += mp.getInterest();
		}
		
		mortgage.setCapital(capital);
		mortgage.setInterest(totalInterest);
		mortgage.setPeriod(period);
		mortgage.setMonthlyPaymentList(list);
		
		return mortgage;
	}

	private MonthlyPayment getMonthlyPayment(double totalCapital, double monthlyInterestRate, int period, int month) {
		MonthlyPayment mp = new MonthlyPayment();
		double capital = totalCapital / period;
		mp.setMonth(month);
		mp.setCapital(capital);
		mp.setInterest(capital * (period - month + 1) * monthlyInterestRate);
		mp.setRemaining((period - month) * capital);
		return mp;
	}
}
