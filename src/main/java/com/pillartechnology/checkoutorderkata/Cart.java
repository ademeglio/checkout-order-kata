package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {

	private boolean isEmpty = true;
	private List<CartItem> items = new ArrayList<CartItem>();
	private BigDecimal preTaxTotal = new BigDecimal("0.00");

	// Constructor
	
	
	
	// Getters & Setters
	
	public boolean isEmpty() {
		return isEmpty;
	}
	
	public List<CartItem> getItems() {
		return items;
	}
	
	public String getPreTaxTotal() {
		return preTaxTotal.toString() ;
	}

	
	// Methods
	
	public void addCartItem(CartItem cartItem) {
		items.add(cartItem);
//		preTaxTotal = preTaxTotal.add(item.getPrice());
		if (isEmpty) {
			this.isEmpty = false;
		}
	}
	
	public Item getCartItem(CartItem cartItem) {
		int index = items.lastIndexOf(cartItem);
		return items.get(index);
	}

	public void deleteLastItem() {
		Item item = items.get(items.size() - 1);
		preTaxTotal = preTaxTotal.subtract(item.getPrice());
		items.remove(items.size() - 1);
	}

	public void adjustPreTaxTotal(String priceAdjustment) {
		BigDecimal priceAdjustmentAmount = new BigDecimal(priceAdjustment);
		preTaxTotal = preTaxTotal.add(priceAdjustmentAmount);
	}	

	
} // End Cart();
