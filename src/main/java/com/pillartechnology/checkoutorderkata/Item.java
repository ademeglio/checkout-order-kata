package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;

public class Item {

	private String name;

	// Constructor
	public Item(String itemName) {
		this.name = itemName;
	}

	
	// Getters & Setters
	
	public String getName() {
		return name;
	}
	
	public BigDecimal getPrice() {
		BigDecimal price = new BigDecimal("1.99");
		return price;
	}


	

} // End Item()
