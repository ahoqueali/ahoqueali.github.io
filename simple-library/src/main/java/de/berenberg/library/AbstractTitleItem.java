package de.berenberg.library;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractTitleItem implements TitleCopy {

	private static final int HASH = 7;
	private static final int HASH_PRIME = 31;

	private final String id;

	private AtomicReference<Optional<Loan>> atomicLoan = new AtomicReference<>(Optional.empty());

	/**
	 * Constructs a copy.
	 * 
	 * @param id the unique identifier
	 */
	public AbstractTitleItem(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public boolean isLoanable() {
		return !atomicLoan.get().isPresent();
	}

	@Override
	public boolean isOverdue() {
		return atomicLoan.get().map(loan -> loan.isOverdue()).orElse(false);
	}

	@Override
	public void setLoan(Loan loan) {
		this.atomicLoan.getAndSet(Optional.of(loan));
	}

	@Override
	public void removeLoan() {
		this.atomicLoan.getAndSet(Optional.empty());
	}

	@Override
	public int hashCode() {
		int hash = HASH;
		hash = HASH_PRIME * hash + id.hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object obj) {

		// check if memory address is the same
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
		return atomicLoan.get();
	}
}
