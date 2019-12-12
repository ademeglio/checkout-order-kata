package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;

public class Item {

	private String name;
	private boolean isChargeByWeight;

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

	public boolean isChargeByWeight() {
		return isChargeByWeight;
	}

	public void setChargeByWeight(boolean isChargeByWeight) {
		this.isChargeByWeight = isChargeByWeight;	
	}





	

} // End Item()
