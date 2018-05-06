package org.ahoque.interfaces;

import java.time.Period;

public interface Loan {

	public boolean isOverdue();

	public Period getLoanPeriod();
}
