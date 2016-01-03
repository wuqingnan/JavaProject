package com.shizy.demo.mortgage;

import com.shizy.demo.mortgage.bean.Mortgage;

public interface MortgageCalculator {

	/**
	 * 计算房贷
	 * @param capital				贷款金额
	 * @param annualInterestRate	年利率
	 * @param period				期限：1年=12期
	 * @return
	 */
	Mortgage calculate(double capital, double annualInterestRate, int period);
	
}
