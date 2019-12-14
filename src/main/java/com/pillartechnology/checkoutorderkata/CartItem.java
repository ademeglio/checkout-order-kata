package com.pillartechnology.checkoutorderkata;

public class CartItem extends Item {

	// Constructor
	public CartItem(Item item) {
		super(item.getName(), item.getPrice().toString()); 
	}

} // End CartItem()
