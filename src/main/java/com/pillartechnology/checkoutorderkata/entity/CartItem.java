package com.pillartechnology.checkoutorderkata.entity;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CartItem stores an Item that is added to a Cart.
 *
 */
public class CartItem {

	Logger logger = LoggerFactory.getLogger(CartItem.class);
	
	private Item item;
	private BigDecimal sellPrice;
	private double weight = 0.0;

	/* Constructors */
	
	public CartItem(Item item) {
		this.item = item;
		calculateSellPrice();
		logger.info("Created cart-item with: " + item.toString());
	}

	public CartItem(Item item, double weight) {
		this.item = item;
		this.weight = weight;
		
		calculateSellPrice();
		logger.info("Created cart-item with: " + item.toString()
				+ "[weight: " + weight + "]");
	}
	
	/* Getters & Setters */
	
	public String getName() {
		return item.getName();
	}
	
	public Item getItem() {
		return item;
	}
	
	public BigDecimal getSellPrice() {
		return sellPrice;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public Double getWeight() {
		return weight;
	}
	
	
	/* Methods */

	/**
	 * CalculateSellPrice by copying the item sell
	 * price to the CartItem sell price. If the CartItem
	 * is charge by weight, the sell price is
	 * recalculated in the method by multiplying the cartItem.weight
	 *  x cartItem.sellPrice.
	 */
	public void calculateSellPrice() {
		this.sellPrice = item.getSalePrice();
		
		if (item.isChargeByWeight()) {
			
			/* Since this is run at initialization, we need to bypass
			 * This at the first pass, or a zero weight sets sellPrice to zero.
			 */
			
			if (this.getWeight() != 0.0) {
				sellPrice = sellPrice.multiply(new BigDecimal(getWeight()));
				logger.info(item.getName() 
						+ " is charged by weight. Total weight: " + this.getWeight()
						+ " units. Calculated sell price is: " + sellPrice.toString());
			}	
		}
	}


} // End CartItem()
