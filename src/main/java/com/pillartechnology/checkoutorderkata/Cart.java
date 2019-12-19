package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {

	private boolean isEmpty = true;
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	private BigDecimal preTaxTotal = new BigDecimal("0.00");
	private Map<String, Integer> itemsOnSpecial = new HashMap<String, Integer>();

	// Default Constructor
	public Cart() {}
	
	
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
		System.out.println("Cart Item (" + cartItem.getName() 
			+ ") Has Special: " + cartItem.hasSpecial());
		if (isEmpty) {
			this.isEmpty = false;
		}
		
		// Track number of items that are on special
		if (cartItem.hasSpecial()) {
			String itemName = cartItem.getName();
			Integer itemCount = 0;
			if (itemsOnSpecial.containsKey(itemName)) {
				itemCount = itemsOnSpecial.get(itemName);
				itemsOnSpecial.put(itemName,  itemCount + 1);
			} else {
				this.itemsOnSpecial.put(cartItem.getName(), 1);
			}
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
			preTaxTotal = preTaxTotal.add(cartItem.getSellPrice());
		}
	}

	public void deleteCartItem(CartItem cartItem1) {
		cartItems.remove(cartItem1);
	}

	public int countItemOnSpecial(Item item) {
		String itemNameOnSpecial = item.getName();
		int qtyItemOnSpecial = 0;
		
		try {
			 qtyItemOnSpecial = itemsOnSpecial.get(itemNameOnSpecial);
		}
		catch(Exception e) {
			return qtyItemOnSpecial;
		}
		
		return qtyItemOnSpecial;
	}	

	
} // End Cart();
