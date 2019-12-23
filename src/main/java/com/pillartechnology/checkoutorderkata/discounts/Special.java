package com.pillartechnology.checkoutorderkata.discounts;

import java.math.BigDecimal;

import com.pillartechnology.checkoutorderkata.entity.Item;

/**
 * Special is the abstract base class for all specials
 * which allows assigning different specials to items.
 *
 *@version 1.0.0
 *@since 1.0.0
 */
public abstract class Special {
	
	private int buyQtyRequirement;
	protected int receiveQtyItems;
	protected double discountPercentage;
	private int limit;
	
	// Constructor
	public Special(int buyQtyRequirement) {
		this.buyQtyRequirement = buyQtyRequirement;
	}
	
	
	/* Getters & Setters */
	
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
	
	/* methods */
	
	public abstract BigDecimal calculateDiscountAmount(Item item, int itemBuyCount);

	public abstract BigDecimal calculateDiscountAmountCBW(Item item, Double itemWeightCount);
	
	
} // End Special()
