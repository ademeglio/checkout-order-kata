package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;

public class Item {

	private String name;
	private boolean isChargeByWeight;
	private BigDecimal defaultPrice;
	private boolean hasMarkdown;
	private Markdown markdown;
	private boolean hasSpecial;
	private Special special;

	// Constructors
	public Item(String itemName) {
		this.name = itemName;
	}
	
	public Item(String itemName, String defaultPrice) {
		this.name = itemName;
		this.defaultPrice = new BigDecimal(defaultPrice);
	}
	

	public Item(String itemName, String defaultPrice, boolean isChargeByWeight) {
		this.name = itemName;
		this.defaultPrice = new BigDecimal(defaultPrice);
		this.isChargeByWeight = isChargeByWeight;
	}

	
	// Getters & Setters

	public String getName() {
		return name;
	}
	
	public BigDecimal getDefaultPrice() {
		return defaultPrice;
	}
	
	public void setDefaultPrice(String defaultPrice) {
		this.defaultPrice = new BigDecimal(defaultPrice);
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

	public Markdown getMarkdown() {
		return markdown;
	}
	
	public boolean hasSpecial() {
		return hasSpecial;
	}
	
	public Special getSpecial() {
		return special;
	}	

	
	// Methods

	public void addMarkdown(Markdown markdown) {
		this.hasMarkdown = true;
		this.markdown = markdown;
	}


	public void addSpecial(Special special1) {
		this.hasSpecial = true;
		this.special = special1;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", defaultPrice=" + defaultPrice + ", isChargeByWeight=" + isChargeByWeight
				+ ", hasMarkdown=" + hasMarkdown + ", markdown=" + markdown + ", hasSpecial=" + hasSpecial
				+ ", special=" + special + "]";
	}

	
} // End Item()
