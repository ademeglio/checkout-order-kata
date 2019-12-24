package com.pillartechnology.checkoutorderkata.discounts;

import java.math.BigDecimal;

import com.pillartechnology.checkoutorderkata.entity.Item;

/**
 * Special is the abstract base class for all specials
 * which allows assigning different specials to items.
 *
 *@version 0.1.0
 *@see BuyNForX
 *@see 
 */
public abstract class Special {
	
	/**
	 * Number of items required to purchase
	 * in order for the Special to apply.
	 */
	private int buyQtyRequirement;
	
	/**
	 * Once the buy quantity is met, this 
	 * represents the number of items to
	 * receive at a discounted price.
	 */
	protected int receiveQtyItems;
	
	/**
	 * Percentage of the discount entered as a double,
	 * but as a whole number. The number is converted
	 * to a decimal later.
	 * <p><strong>Example:</strong> Enter 50% as 50 or 50.0.</p>
	 */
	protected double discountPercentage;
	
	/**
	 * Stores the limit on number of items that a special
	 * can be applied to the Special. This number includes
	 * the buyQtyRequirement and receiveQtyItems.
	 */
	private int limit;
	
	/** 
	 * Descriptive name used as an identifier.
	 * <p><strong>Example:</strong> Buy 2 get 1 for half-off</p>
	 */
	private String name;
	
	// Constructor
	
	/**
	 * Class Constructor
	 * 
	 * @param buyQtyRequirement minimum quantity of items needed in cart
	 * before Special applies.
	 */
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String specialName) {
		this.name = specialName;
	}
	
	
	/* methods */
	
	public abstract BigDecimal calculateDiscountAmount(Item item, int itemBuyCount);

	public abstract BigDecimal calculateDiscountAmountCBW(Item item, Double itemWeightCount);
	
	
} // End Special()
