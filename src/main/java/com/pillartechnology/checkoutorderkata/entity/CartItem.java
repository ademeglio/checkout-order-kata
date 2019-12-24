package com.pillartechnology.checkoutorderkata.entity;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CartItem stores an {@link Item} that is added to a {@link Cart}.
 * <p>This stores the final sale price and if the {@link Item}
 * isChargeByWeight, stores that weight in this.</p>
 *
 *@version 0.1.0
 *@see Item
 *@see Cart
 */
public class CartItem {

	Logger logger = LoggerFactory.getLogger(CartItem.class);
	
	private Item item;
	/**
	 * Stores the calculated selling price
	 * of the CartItem. An applied {@link Markdown}
	 * will reduce the {@link Item} selling price.
	 */
	private BigDecimal sellPrice;
	/**
	 * If {@link Item} isChargeByWeight, the weight
	 * of the {@link Item} is stored here.
	 */
	private double weight = 0.0;

	/* Constructors */
	/**
	 * Class Constructor.
	 * 
	 * @param item the {@link Item} reference for this class.
	 */
	public CartItem(Item item) {
		this.item = item;
		calculateSellPrice();
		logger.info("Created cart-item with: " + item.toString());
	}

	/**
	 * Class Constructor for {@link Item} that isChargeByWeight.
	 * 
	 * @param item the {@link Item} reference for this class.
	 * @param weight the measured weight of the {@link Item}
	 * that isChargeByWeight.
	 */
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
	 *  * cartItem.sellPrice.
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
	
	@Override 
	public String toString() {
		String itemName = this.getItem().getName();
		boolean isChargeByWeight = this.getItem().isChargeByWeight();
		String weight = "";
		
		if (isChargeByWeight) {
			weight = ", weight=" + this.getWeight().toString();
		}
		
		return "CartItem [name=" + itemName + weight + ", sellPrice=" + this.sellPrice + "]";
	}


} // End CartItem()
