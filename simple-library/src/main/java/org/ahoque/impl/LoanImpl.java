package org.ahoque.impl;

import java.time.LocalDate;
import java.time.Period;

import org.ahoque.Loan;

public class LoanImpl implements Loan {

	private static final int LOAN_PERIOD_IN_DAYS = 7;
	
	private final LocalDate startDate;
	private final LocalDate endDate;
	
	public LoanImpl() {
		this.startDate = LocalDate.now();
		this.endDate = startDate.plusDays(LOAN_PERIOD_IN_DAYS);
	}
	
	public LoanImpl(LocalDate startDate, LocalDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	@Override
	public boolean isOverdue() {
		return Period.between(startDate, LocalDate.now()).getDays() > getLoanPeriod().getDays();
	}

	@Override
	public Period getLoanPeriod() {
		return Period.between(startDate, endDate);
	}
}