package org.ahoque;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class TitleImpl implements Title{

	private final String title;
	private final String publisher;
	
	private Collection<TitleCopy> copies = new ArrayList<>();
	
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
	public Collection<TitleCopy> getLoanableCopies() {
		return copies.stream()
				.filter(copy -> copy.isLoanable())
				.collect(Collectors.toList());
	}
	
	
}
