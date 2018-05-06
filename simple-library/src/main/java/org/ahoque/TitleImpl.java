package org.ahoque;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class TitleImpl implements Title{

	private final String title;
	private final String publisher;
	
	private final List<TitleCopy> copies = new CopyOnWriteArrayList<>();
	
	public TitleImpl(String title, String publisher) {
		this.title = title;
		this.publisher = publisher;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void add(TitleCopy copy) {
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
