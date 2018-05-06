package org.ahoque.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import org.ahoque.interfaces.Loan;
import org.ahoque.interfaces.Member;
import org.ahoque.interfaces.TitleCopy;

public class MemberImpl implements Member {

	private final String username;

	private final List<TitleCopy> borrowedItems = new CopyOnWriteArrayList<>();

	public MemberImpl(final String username) {
		this.username = username;
	}

	@Override
	public String getName() {
		return username;
	}

	@Override
	public List<TitleCopy> getBorrowedItems() {
		return borrowedItems;
	}

	@Override
	public void borrowItem(TitleCopy item) {

		item.setLoan(new LoanImpl());
		borrowedItems.add(item);
	}

	@Override
	public void borrowItem(TitleCopy item, Loan loan) {

		item.setLoan(loan);
		borrowedItems.add(item);
	}

	@Override
	public void returnItem(TitleCopy item) {

		borrowedItems.stream()
		.filter(c -> c.equals(item))
		.forEach(c -> {
			borrowedItems.remove(item);
			item.removeLoan();
		});

	}

}
