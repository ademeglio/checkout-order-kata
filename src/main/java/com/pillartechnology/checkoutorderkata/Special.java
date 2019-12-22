package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;

public abstract class Special {
	
	private int buyQtyRequirement;
	protected int receiveQtyItems;
	protected double discountPercentage;
	private int limit;
	
	// Constructor
	public Special(int buyQtyRequirement) {
		this.buyQtyRequirement = buyQtyRequirement;
	}
	
	
	// Getters & Setters
	
	public int getBuyQtyRequirement() {
		return buyQtyRequirement;
	}
	
	public int getReceiveQtyItems() {
		return receiveQtyItems;
	}
	
	public double getDiscountPercentage() {
		return discountPercentage;
	}
	
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	// methods
	
	public abstract BigDecimal calculateDiscountAmount(Item item, int itemBuyCount);
	
	
} // End Special()
