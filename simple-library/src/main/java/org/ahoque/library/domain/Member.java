package org.ahoque.library.domain;

import java.util.List;

public interface Member {

	public String getUsername();
	
	public List<TitleCopy> getBorrowedItems();
	
	public void borrowItem(TitleCopy item);
	
	public void returnItem(TitleCopy item);

	public void borrowItem(TitleCopy item, Loan loan);
}
