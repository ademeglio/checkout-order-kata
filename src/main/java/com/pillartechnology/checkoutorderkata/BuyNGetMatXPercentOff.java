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
				BigDecimal discountedUnitSalePrice = item.getSalePrice().multiply(new BigDecimal(this.getDiscountPercentage()/100)); 
				discountAmount = discountedUnitSalePrice.multiply(new BigDecimal(itemsRemaining));
			}
			
			return discountAmount.setScale(2);
		}
		
		return discountAmount.setScale(2);
	}
	
	@Override
	public String toString() {
		int buyQty = this.getBuyQtyRequirement();
		int percentOff = (int) (this.getDiscountPercentage() * 100);
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
