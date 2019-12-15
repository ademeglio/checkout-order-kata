package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;

public class CartItem extends Item {

	private BigDecimal salePrice = getPrice();

	// Constructor
	public CartItem(Item item) {
		super(item.getName(), item.getPrice().toString()); 
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

} // End CartItem()
