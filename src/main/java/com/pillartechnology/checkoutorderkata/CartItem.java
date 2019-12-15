package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;

public class CartItem extends Item {

	private BigDecimal salePrice;

	// Constructor
	public CartItem(Item item) {
		super(item.getName(), item.getPrice().toString(), item.isChargeByWeight());
		super.addMarkdown(item.getMarkdown());
		this.salePrice = setSalePrice();
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}
	
	private BigDecimal setSalePrice() {
		BigDecimal salePrice = super.getPrice();
		if (super.getMarkdown() != null) {
			salePrice = salePrice.subtract(super.getMarkdown().getMarkdownAmount());
			return salePrice;
		}
		
		return salePrice;
	}

} // End CartItem()
