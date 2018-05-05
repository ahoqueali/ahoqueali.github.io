package org.ahoque;

import java.util.Optional;

public class TitleCopyImpl implements TitleCopy {

	private final String id;
	
	/** When a copy is loaned the loan object is set.
	 */
	private Optional<LoanImpl> loan = Optional.empty();

	/**
	 * Constructs a copy.
	 * 
	 * @param id the unique identifier of the copy
	 */
	public TitleCopyImpl(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public boolean isLoanable() {
		return !loan.isPresent();
	}

	@Override
	public void setLoan(LoanImpl loan) {
		this.loan = Optional.of(loan);
	}

	@Override
	public void removeLoan() {
		this.loan = Optional.empty();
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + id.hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object obj) {

		// check if memeory address is the same
		if(obj == this) {
			return true;
		}

		// check if same type
		if(!(obj instanceof TitleCopy)) {
			return false;
		}

		// compare state
		TitleCopy copy = (TitleCopy) obj;
		return copy.getId().equals(id); 
	}

	@Override
	public Optional<LoanImpl> getLoan() {
		return loan;
	}
}
