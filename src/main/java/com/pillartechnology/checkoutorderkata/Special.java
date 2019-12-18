package com.pillartechnology.checkoutorderkata;

public class Special {
	
	private int buyQtyRequirement;
	protected int receiveQtyItems;
	
	// Constructor
	public Special(int buyQtyRequirement) {
		this.buyQtyRequirement = buyQtyRequirement;
	}
	
	
	// Getters & Setters
	
	public int getBuyQtyRequirement() {
		return buyQtyRequirement;
	}
	
	public int getReceiveQtyItems() {
		return receiveQtyItems;
	}
	
	
} // End Special()
