package com.pillartechnology.checkoutorderkata;

public class BuyNGetMatXPercentOff extends Special{

	// Constructor
	
	public BuyNGetMatXPercentOff(int buyQtyRequirement,
			int receiveQtyItems, double percentOff) {
		super(buyQtyRequirement);
		this.receiveQtyItems = receiveQtyItems;
		
	}



} // End BuyNGetMatXPercentOff()
