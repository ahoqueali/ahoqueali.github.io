package org.ahoque;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

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
	public List<TitleCopy> getLoanItems() {

		return borrowedCopies.stream()
				.collect(Collectors.toList());
	}

	@Override
	public void borrowItem(TitleCopy copy) {

		reentrantLock.lock();
		
		try {
			// critical section to stop multiple threads loaning the same copy
			LoanImpl loan = new LoanImpl(copy);
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
			borrowedCopies.remove(copy);
			copy.removeLoan();
		});
	}

}
