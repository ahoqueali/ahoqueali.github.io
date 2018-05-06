package org.ahoque.interfaces;

import java.util.List;

public interface Member {

	public String getName();
	
	public List<TitleCopy> getBorrowedItems();
	
	public void borrowItem(TitleCopy item);
	
	public void returnItem(TitleCopy item);

	public void borrowItem(TitleCopy item, Loan loan);
}
