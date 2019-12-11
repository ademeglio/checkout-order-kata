package com.pillartechnology.checkoutorderkata;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<Item> items = new ArrayList<Item>();

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return true;
	}

	public void addItem(Item item) {
		items.add(item);
	}
	
	public Item getItem(Item item) {
		int index = items.lastIndexOf(item);
		return items.get(index);
	}

	

	
} // End Cart();
