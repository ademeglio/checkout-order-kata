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
		
		if (itemBuyCount >= buyQtyRequirement) {
			
			itemsRemaining = itemsRemaining - buyQtyRequirement;
			
			if (itemsRemaining >= receiveDiscountOnQty) {
			discountAmount = item.getSalePrice().multiply(new BigDecimal(itemsRemaining)); 
			}
			
			return discountAmount;
		}
		
		return discountAmount;
	}
	
	@Override
	public String toString() {
		int buyQty = this.getBuyQtyRequirement();
		int percentOff = (int) (this.getDiscountPercentage() * 100);
		String itemOrItems = buyQty > 1
				? "items"
				: "item";
				
		if (percentOff == 100) {
			return "Buy " + buyQty + " get " + this.getReceiveQtyItems() 
				+ " free";
		}
		
		return "Buy " + buyQty + " " + itemOrItems + ", get " 
			+ this.receiveQtyItems + " at " + percentOff 
			+ "% off";
	}



} // End BuyNGetMatXPercentOff()
