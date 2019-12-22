package com.pillartechnology.checkoutorderkata.discounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.pillartechnology.checkoutorderkata.entity.Item;

/**
 * BuyNGetMatXPercentOff extends Special() allowing to configure
 * Buy N items and get M items at %x off.
 */
public class BuyNGetMatXPercentOff extends Special{

	// Constructor
	public BuyNGetMatXPercentOff(int buyQtyRequirement,
			int receiveQtyItems, double percentOff) {
		super(buyQtyRequirement);
		this.receiveQtyItems = receiveQtyItems;
		this.discountPercentage = percentOff;
	}
	
	/**
	 * Returns a BigDecimal representing the total calculated
	 * discount for a given Item in the cart that is on special.
	 * @param item an item object in the cart that has a special.
	 * @param itemBuyCount is the number of items of type item in the cart on special.
	 * @return The total calculated decimal amount as a Big Decimal.
	 */
	@Override
	public BigDecimal calculateDiscountAmount(Item item, int itemBuyCount) {
		BigDecimal discountAmount = new BigDecimal("0.00");
		int buyQtyRequirement = this.getBuyQtyRequirement();
		int itemsRemaining = itemBuyCount;
		int receiveDiscountOnQty = this.getReceiveQtyItems();
		
		if (this.getLimit() > 0) {
			itemsRemaining = this.getLimit();
		} 

		// TODO double check that this shouldn't be >= instead of >
		while (itemsRemaining > buyQtyRequirement) {
			BigDecimal amountToAddToDiscount = new BigDecimal("0.0");
			
			// Special Applies; adjust items remaining first
			itemsRemaining = itemsRemaining - buyQtyRequirement;
			
			// Calculate discounted unit price * item default/ sale price
			BigDecimal discountedUnitSalePrice = item.getSalePrice()
					.multiply(new BigDecimal(this.getDiscountPercentage()/100));

			// Calculate the amount to add to the total discount amount
			amountToAddToDiscount = discountedUnitSalePrice
					.multiply(new BigDecimal(receiveDiscountOnQty));
			
			/* Add the calculated amount and adjust the items remaining by items
			 * purchased at discount. 
			 */
			discountAmount = discountAmount.add(amountToAddToDiscount);
			itemsRemaining = itemsRemaining - receiveDiscountOnQty;
		} // End while
		return discountAmount.setScale(2, RoundingMode.HALF_UP);
	}

	@Override
	public String toString() {
		int buyQty = this.getBuyQtyRequirement();
		double percentOff = (this.getDiscountPercentage());
		String itemOrItems = buyQty > 1
				? "items"
				: "item";
		
		String addLimit = this.getLimit() > 0
				? ", limit " + this.getLimit()
				: "";
		if (percentOff == 1.00) {
			return "Buy " + buyQty + " get " + this.getReceiveQtyItems() 
				+ " free";
		}
		
		
		
		return "Buy " + buyQty + " " + itemOrItems + ", get " 
			+ this.receiveQtyItems + " at " + percentOff
			+ "% off" + addLimit;
	}


} // End BuyNGetMatXPercentOff()
