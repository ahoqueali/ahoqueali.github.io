package de.berenberg.library;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class MemberImpl implements Member {

	private final String username;

	private final List<TitleCopy> borrowedItems = new CopyOnWriteArrayList<>();

	private final ReentrantLock borrowedItemsLock = new ReentrantLock();

	public MemberImpl(final String username) {
		this.username = username;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public List<TitleCopy> getBorrowedItems() {
		return borrowedItems;
	}

	@Override
	public void borrowItem(TitleCopy item) {

		borrowedItemsLock.lock();

		try {
			if(!borrowedItems.contains(item)) {
				item.setLoan(new LoanImpl());
				borrowedItems.add(item);
			}
		}finally {
			borrowedItemsLock.unlock();
		}

	}

	@Override
	public void borrowItem(TitleCopy item, Loan loan) {

		borrowedItemsLock.lock();

		try {
			item.setLoan(loan);
			borrowedItems.add(item);
		}finally {
			borrowedItemsLock.unlock();
		}
	}

	@Override
	public void returnItem(TitleCopy item) {

		borrowedItems.stream()
		.filter(c -> c.equals(item))
		.forEach(c -> {
			borrowedItemsLock.lock();
			try {
				item.removeLoan();
				borrowedItems.remove(item);
			}finally{
				borrowedItemsLock.unlock();
			}
		});

	}

}
