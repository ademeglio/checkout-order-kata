package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;

public class CartItem extends Item {

	private BigDecimal sellPrice;
	private double weight;

	// Constructor
	public CartItem(Item item) {
		super(item.getName(), item.getPrice().toString(), item.isChargeByWeight());
		super.addMarkdown(item.getMarkdown());
		this.sellPrice = setSellPrice();
	}

	
	// Getters & Setters
	
	public BigDecimal getSellPrice() {
		return sellPrice;
	}
	
	public BigDecimal setSellPrice() {
		BigDecimal sellPrice = super.getPrice();
		if (super.getMarkdown() != null) {
			sellPrice = sellPrice.subtract(super.getMarkdown().getMarkdownAmount());
			return sellPrice;
		}
		return sellPrice;
	}
	
	public void setWeight(double itemWeight) {
		this.weight = itemWeight;
	}
	
	public Double getWeight() {
		return weight;
	}


} // End CartItem()
