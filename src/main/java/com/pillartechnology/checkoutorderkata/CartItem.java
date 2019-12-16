package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;

public class CartItem extends Item {

	private BigDecimal salePrice;
	private double weight;

	// Constructor
	public CartItem(Item item) {
		super(item.getName(), item.getPrice().toString(), item.isChargeByWeight());
		super.addMarkdown(item.getMarkdown());
		this.salePrice = setSalePrice();
	}

	
	// Getters & Setters
	
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
	
	public void setWeight(double itemWeight) {
		this.weight = itemWeight;
	}
	
	public Double getWeight() {
		return weight;
	}


} // End CartItem()
