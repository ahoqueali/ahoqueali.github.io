package org.ahoque.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.ahoque.Title;
import org.ahoque.TitleCopy;

public class TitleImpl implements Title{

	private final String title;
	
	private final List<TitleCopy> copies = new CopyOnWriteArrayList<>();
	
	public TitleImpl(String title) {
		this.title = title;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void addTitleCopy(TitleCopy copy) {
		copies.add(copy);
	}

	@Override
	public List<TitleCopy> getLoanableCopies() {
		return copies.stream()
				.filter(copy -> copy.isLoanable())
				.collect(Collectors.toList());
	}

	@Override
	public List<TitleCopy> getOverdueCopies() {
		return copies.stream()
				.filter(copy -> copy.isOverdue())
				.collect(Collectors.toList());
	}
}
