package com.pillartechnology.checkoutorderkata.service;

import java.math.BigDecimal;
import java.util.HashMap;

import com.pillartechnology.checkoutorderkata.entity.Cart;

/**
 * Interface for managing cart actions.
 */
public interface RegisterService {

	HashMap<Integer, Cart> CARTS = new HashMap<Integer, Cart>();

	
	void initiateCart();

	Cart getCart(Integer cartIdNo);
	
	BigDecimal getPreTaxCartTotal(Integer cartIdNo);

	void scanItem(Integer cartIdNo, String itemName, double weight);

	void removeLastItemScanned(Integer cartIdNo);

} // End RegisterService()
