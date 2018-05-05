package org.ahoque;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class MemberImpl implements Member {

	private final String username;

	private List<TitleCopy> copies = new CopyOnWriteArrayList<>();

	private ReentrantLock reentrantLock = new ReentrantLock();

	public MemberImpl(final String username) {

		this.username = username;
	}

	@Override
	public String getName() {

		return username;
	}

	@Override
	public List<TitleCopy> getLoanItems() {

		return copies.stream()
				.collect(Collectors.toList());
	}

	@Override
	public void borrowItem(TitleCopy copy) {

		reentrantLock.lock();
		
		try {
			LoanImpl loan = new LoanImpl(copy);
			copy.setLoan(loan);
			copies.add(copy);
		}finally {
			reentrantLock.unlock();
		}
	}

	@Override
	public void returnItem(TitleCopy copy) {

		copies.stream()
		.filter(c -> c.equals(copy))
		.forEach(c -> {
			copies.remove(copy);
			copy.removeLoan();
		});
	}

}
