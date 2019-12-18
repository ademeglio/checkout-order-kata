package com.pillartechnology.checkoutorderkata;

public class BuyNGetMatXPercentOff extends Special{

	// Constructor
	
	public BuyNGetMatXPercentOff(int buyQtyRequirement,
			int receiveQtyItems, double percentOff) {
		super(buyQtyRequirement);
		this.receiveQtyItems = receiveQtyItems;
		this.discountPercentage = percentOff;
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
