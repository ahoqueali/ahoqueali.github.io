package org.ahoque.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import org.ahoque.Loan;
import org.ahoque.Member;
import org.ahoque.TitleCopy;

public class MemberImpl implements Member {

	private final String username;

	private final List<TitleCopy> borrowedCopies = new CopyOnWriteArrayList<>();

	// Static lock so that there is just one lock that all treads need to go through
	private static final ReentrantLock reentrantLock = new ReentrantLock();

	public MemberImpl(final String username) {
		this.username = username;
	}

	@Override
	public String getName() {
		return username;
	}

	@Override
	public List<TitleCopy> getBorrowedItems() {
		return borrowedCopies;
	}

	@Override
	public void borrowItem(TitleCopy copy) {

		reentrantLock.lock();

		try {
			// critical section to stop multiple threads loaning the same copy
			LoanImpl loan = new LoanImpl();
			copy.setLoan(loan);
			borrowedCopies.add(copy);
		}finally {
			reentrantLock.unlock();
		}
	}
	
	@Override
	public void borrowItem(TitleCopy copy, Loan loan) {
		
		reentrantLock.lock();

		try {
			// critical section to stop multiple threads loaning the same copy
			copy.setLoan(loan);
			borrowedCopies.add(copy);
		}finally {
			reentrantLock.unlock();
		}
		
	}

	@Override
	public void returnItem(TitleCopy copy) {


		borrowedCopies.stream()
		.filter(c -> c.equals(copy))
		.forEach(c -> {
			
			reentrantLock.lock();
			
			try {
				// critical section to stop multiple threads from updating
				borrowedCopies.remove(copy);
				copy.removeLoan();
			}finally {
				reentrantLock.unlock();
			}
		});

	}

}
