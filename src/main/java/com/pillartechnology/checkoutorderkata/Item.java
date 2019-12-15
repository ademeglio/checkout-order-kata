package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;

public class Item {

	private String name;
	private boolean isChargeByWeight;
	private BigDecimal price;
	private boolean hasMarkdown;
	private Markdown markdown;
	private boolean hasSpecial;
	private Special special;

	// Constructors
	public Item(String itemName) {
		this.name = itemName;
	}
	
	public Item(String itemName, String price) {
		this.name = itemName;
		this.price = new BigDecimal(price);
	}
	

	public Item(String itemName, String price, boolean isChargeByWeight) {
		this.name = itemName;
		this.price = new BigDecimal(price);
		this.isChargeByWeight = isChargeByWeight;
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


} // End Item()
