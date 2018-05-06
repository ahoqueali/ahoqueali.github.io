package org.ahoque.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import org.ahoque.interfaces.Loan;
import org.ahoque.interfaces.Member;
import org.ahoque.interfaces.TitleCopy;

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
	public void borrowItem(TitleCopy item) {

		LoanImpl loan = new LoanImpl();
		
		reentrantLock.lock();
		
		try {
			// critical section to stop multiple threads loaning the same copy
			item.setLoan(loan);
			borrowedCopies.add(item);
		}finally {
			reentrantLock.unlock();
		}
	}
	
	@Override
	public void borrowItem(TitleCopy item, Loan loan) {
		
		reentrantLock.lock();

		try {
			// critical section to stop multiple threads loaning the same copy
			item.setLoan(loan);
			borrowedCopies.add(item);
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
