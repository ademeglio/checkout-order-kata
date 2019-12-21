package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;

public class BuyNGetMatXPercentOff extends Special{

	// Constructor
	
	public BuyNGetMatXPercentOff(int buyQtyRequirement,
			int receiveQtyItems, double percentOff) {
		super(buyQtyRequirement);
		this.receiveQtyItems = receiveQtyItems;
		this.discountPercentage = percentOff;
	}
	
	@Override
	public BigDecimal calculateDiscountAmount(Item item, int itemBuyCount) {
		BigDecimal discountAmount = new BigDecimal("0.00");
		int buyQtyRequirement = this.getBuyQtyRequirement();
		int itemsRemaining = itemBuyCount;
		int receiveDiscountOnQty = this.getReceiveQtyItems();
		
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
		return discountAmount.setScale(2);
	}
	
	@Override
	public String toString() {
		int buyQty = this.getBuyQtyRequirement();
		double percentOff = (this.getDiscountPercentage());
		String itemOrItems = buyQty > 1
				? "items"
				: "item";
				
		if (percentOff == 1.00) {
			return "Buy " + buyQty + " get " + this.getReceiveQtyItems() 
				+ " free";
		}
		
		return "Buy " + buyQty + " " + itemOrItems + ", get " 
			+ this.receiveQtyItems + " at " + percentOff
			+ "% off";
	}



} // End BuyNGetMatXPercentOff()
