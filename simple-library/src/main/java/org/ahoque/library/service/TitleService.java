package org.ahoque.library.service;

import org.ahoque.library.domain.Title;
import org.ahoque.library.domain.TitleCopy;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class TitleService implements Title {

	private final String title;
	
	private final List<TitleCopy> items = new CopyOnWriteArrayList<>();
	
	public TitleService(String title) {
		this.title = title;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void addTitleCopy(TitleCopy item) {
		items.add(item);
	}

	@Override
	public List<TitleCopy> getLoanableItems() {
		return items.stream()
				.filter(item -> item.isLoanable())
				.collect(Collectors.toList());
	}

	@Override
	public List<TitleCopy> getOverdueItems() {
		return items.stream()
				.filter(item -> item.isOverdue())
				.collect(Collectors.toList());
	}
}
