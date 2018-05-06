package org.ahoque.interfaces;

import java.util.List;

public interface Member {

	public String getName();
	
	public List<TitleCopy> getBorrowedItems();
	
	public void borrowItem(TitleCopy copy);
	
	public void returnItem(TitleCopy copy);

	public void borrowItem(TitleCopy copy, Loan loan);
}
