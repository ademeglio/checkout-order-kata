package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {

	private boolean isEmpty = true;
	private List<Item> items = new ArrayList<Item>();
	private BigDecimal preTaxTotal = new BigDecimal("0.00");

	// Constructor
	
	
	
	// Getters & Setters
	
	public boolean isEmpty() {
		return isEmpty;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public String getPreTaxTotal() {
		return preTaxTotal.toString() ;
	}

	
	// Methods
	
	public void addItem(Item item) {
		items.add(item);
		if (isEmpty) {
			this.isEmpty = false;
		}
	}
	
	public Item getItem(Item item) {
		int index = items.lastIndexOf(item);
		return items.get(index);
	}

	public void deleteLastItem() {
		items.remove(items.size() - 1);
	}	

	
} // End Cart();
