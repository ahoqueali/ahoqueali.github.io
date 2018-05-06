package org.ahoque;

import java.time.LocalDate;
import java.time.Period;

public class LoanImpl implements Loan {

	/** Loan period in days **/
	private static final int LOAN_PERIOD = 7;
	
	private TitleCopy copy;
	private final LocalDate startDate;
	private final LocalDate endDate;
	private final Period period;
	
	public LoanImpl(TitleCopy copy) {
		this.copy = copy;
		this.startDate = LocalDate.now();
		this.endDate = startDate.plusDays(LOAN_PERIOD);
		this.period = Period.between(startDate, endDate);
	}
	
	@Override
	public TitleCopy getItem() {
		return copy;
	}

	@Override
	public Period getPeriod() {
		return period;
	}
	
	@Override
	public boolean isOverdue() {
		return LocalDate.now().isAfter(endDate);
	}
}