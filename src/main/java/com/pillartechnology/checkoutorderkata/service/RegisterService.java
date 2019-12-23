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

	BigDecimal getPreTaxCartTotal(int cartId);

} // End RegisterService()
