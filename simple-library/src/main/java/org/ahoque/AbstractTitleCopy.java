package org.ahoque;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractTitleCopy implements TitleCopy {

	private static final int HASH = 7;
	private static final int HASH_PRIME = 31;

	private final String id;

	// When a copy is loaned then the loan object is set. 
	// Using non-blocking compare-and-swap AtomicRef to avoid thread deadlock issues.
	private AtomicReference<Optional<Loan>> atomicRef;

	/**
	 * Constructs a copy.
	 * 
	 * @param id the unique identifier of the copy
	 */
	public AbstractTitleCopy(String id) {
		this.id = id;
		atomicRef = new AtomicReference<>(Optional.empty());
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public boolean isLoanable() {
		return !atomicRef.get().isPresent();
	}
	
	@Override
	public boolean isOverdue() {
		
		if(atomicRef.get().isPresent()) {
			atomicRef.get().get().isOverdue();
		}
		
		return false;
	}

	@Override
	public void setLoan(LoanImpl loan) {
		this.atomicRef.getAndSet(Optional.of(loan));
	}

	@Override
	public void removeLoan() {
		this.atomicRef.getAndSet(Optional.empty());
	}

	@Override
	public int hashCode() {
		int hash = HASH;
		hash = HASH_PRIME * hash + id.hashCode();
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
	public Optional<Loan> getLoan() {
		return atomicRef.get();
	}
}
