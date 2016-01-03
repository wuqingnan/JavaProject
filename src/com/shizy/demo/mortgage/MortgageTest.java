package com.shizy.demo.mortgage;

import java.util.List;

import com.shizy.demo.mortgage.bean.MonthlyPayment;
import com.shizy.demo.mortgage.bean.Mortgage;

public class MortgageTest {

	public static void main(String[] args) {

//		MortgageCalculator mc = new AverageCapital();
		MortgageCalculator mc = new AverageCapitalPlusInterest();
		Mortgage mortgage = mc.calculate(1000000, 4.9f / 100, 20 * 12);
		
		System.out.println(mortgage.getCapital() + mortgage.getInterest());
		List<MonthlyPayment> list = mortgage.getMonthlyPaymentList();
		MonthlyPayment mp = null;
		for (int i = 0; i < list.size(); i++) {
			mp = list.get(i);
			System.out.printf("%5d", i + 1);
			System.out.printf("%10.2f", mp.getInterest());
			System.out.printf("%10.2f", mp.getCapital());
			System.out.printf("%10.2f", mp.getCapital() + mp.getInterest());
			System.out.printf("%10.2f", mp.getRemaining());
			System.out.println("\n--------------------------------------");
		}
		
	}

}
