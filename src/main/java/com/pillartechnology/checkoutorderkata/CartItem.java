package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartItem extends Item {

	Logger logger = LoggerFactory.getLogger(CartItem.class);
	
	private BigDecimal sellPrice;
	private double weight = 0.0;

	// Constructor
	public CartItem(Item item) {
		super(item.getName(), item.getDefaultPrice().toString(), item.isChargeByWeight());
		super.addMarkdown(item.getMarkdown());
		
		calculateSellPrice();
		logger.info("Created cart-item with: " + item.toString());
	}

	public CartItem(Item item, double weight) {
		super(item.getName(), item.getDefaultPrice().toString(), item.isChargeByWeight());
		super.addMarkdown(item.getMarkdown());
		this.weight = weight;
		
		calculateSellPrice();
		logger.info("Created cart-item with: " + item.toString()
				+ "[weight: " + weight + "]");
	}
	
	// Getters & Setters
	
	public BigDecimal getSellPrice() {
		return sellPrice;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public Double getWeight() {
		return weight;
	}
	
	
	// Methods
	
	public void calculateSellPrice() {
		this.sellPrice = super.getDefaultPrice();
		
		if (super.getMarkdown() != null) {
			logger.info(super.getMarkdown().getDescription() 
					+ " markdown applied to default price for " + this.getName());
			sellPrice = sellPrice.subtract(super.getMarkdown().getMarkdownAmount());
		}
		
		if (super.isChargeByWeight()) {
			
			/* Since this is run at initialization, we need to bypass
			 * This at the first pass, or a zero weight sets sellPrice to zero.
			 */
			
			if (this.getWeight() != 0.0) {
				sellPrice = sellPrice.multiply(new BigDecimal(getWeight()));
				logger.info(super.getName() 
						+ " is charged by weight. Total weight: " + this.getWeight()
						+ " units. Calculated sell price is: " + sellPrice.toString());
			}	
			
		}
		
	}


} // End CartItem()
