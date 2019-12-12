package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;

public class Item {

	private String name;
	private boolean isChargeByWeight;
	private BigDecimal price;
	private boolean hasMarkdown;

	// Constructor
	public Item(String itemName) {
		this.name = itemName;
	}

	
	// Getters & Setters
	
	public String getName() {
		return name;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(String priceAsString) {
		this.price = new BigDecimal(priceAsString);
	}

	public boolean isChargeByWeight() {
		return isChargeByWeight;
	}

	public void setChargeByWeight(boolean isChargeByWeight) {
		this.isChargeByWeight = isChargeByWeight;	
	}


	public boolean hasMarkdown() {
		return hasMarkdown;
	}


	// Methods
	
	








	

} // End Item()
