package org.ahoque;

import java.time.Period;

public interface Loan {

	public boolean isOverdue();

	public Period getLoanPeriod();
}
