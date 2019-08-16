package org.ahoque.library.service;

import org.ahoque.library.domain.Loan;
import org.ahoque.library.domain.impl.LoanImpl;
import org.ahoque.library.domain.Member;
import org.ahoque.library.domain.TitleCopy;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class MemberService implements Member {

	private final String username;

	private final List<TitleCopy> borrowedItems = new CopyOnWriteArrayList<>();

	private final ReentrantLock borrowedItemsLock = new ReentrantLock();

	public MemberService(final String username) {
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
