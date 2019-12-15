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
		cartItems.remove(cartItems.size() - 1);
	}

	public void calculatePreTaxTotal() {
		preTaxTotal = new BigDecimal("0.00");
		
		for (CartItem cartItem : cartItems) {
			preTaxTotal = preTaxTotal.add(cartItem.getSalePrice());
		}
	}

	public void deleteCartItem(CartItem cartItem1) {
		cartItems.remove(cartItem1);
	}	

	
} // End Cart();
