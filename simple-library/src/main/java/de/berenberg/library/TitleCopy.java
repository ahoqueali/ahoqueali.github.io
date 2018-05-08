package de.berenberg.library;

import java.util.Optional;

public interface TitleCopy {
	
	public boolean isLoanable();
	
	public void setLoan(Loan loan);
	
	public String getId();
	
	public void removeLoan();
	
	public Optional<Loan> getLoan();

	public boolean isOverdue();
}
