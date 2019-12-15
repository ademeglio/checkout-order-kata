package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {

	private boolean isEmpty = true;
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	private BigDecimal preTaxTotal = new BigDecimal("0.00");

	// Constructor
	
	
	
	// Getters & Setters
	
	public boolean isEmpty() {
		return isEmpty;
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	
	public String getPreTaxTotal() {
		return preTaxTotal.toString() ;
	}

	
	// Methods
	
	public void addCartItem(CartItem cartItem) {
		cartItems.add(cartItem);
		if (isEmpty) {
			this.isEmpty = false;
		}
	}
	
	public Item getCartItem(CartItem cartItem) {
		int index = cartItems.lastIndexOf(cartItem);
		return cartItems.get(index);
	}

	public void deleteLastCartItem() {
		Item item = cartItems.get(cartItems.size() - 1);
		preTaxTotal = preTaxTotal.subtract(item.getPrice());
		cartItems.remove(cartItems.size() - 1);
	}

	//TODO Remove unused code?
	public void adjustPreTaxTotal(String priceAdjustment) {
		BigDecimal priceAdjustmentAmount = new BigDecimal(priceAdjustment);
		preTaxTotal = preTaxTotal.add(priceAdjustmentAmount);
	}

	public void calculatePreTaxTotal() {
		for (CartItem cartItem : cartItems) {
			preTaxTotal = preTaxTotal.add(cartItem.getPrice());
		}
	}	

	
} // End Cart();
