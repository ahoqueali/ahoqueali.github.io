package org.ahoque;

import java.util.List;

public interface Member {

	public String getName();
	
	public List<TitleCopy> getLoanItems();
	
	public void borrowItem(TitleCopy copy);
	
	public void returnItem(TitleCopy copy);
}
