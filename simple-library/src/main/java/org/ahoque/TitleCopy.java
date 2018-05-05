package org.ahoque;

import java.util.Optional;

public interface TitleCopy {
	
	public boolean isLoanable();
	
	public void setLoan(LoanImpl loan);
	
	public String getId();
	
	public void removeLoan();
	
	public Optional<LoanImpl> getLoan();
}
