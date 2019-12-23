package com.pillartechnology.checkoutorderkata.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pillartechnology.checkoutorderkata.entity.Cart;

@Service
public class RegisterServiceImp implements RegisterService {

	private static final Logger logger = LoggerFactory.getLogger(RegisterServiceImp.class);
	
	private Integer cartIdNo = 0;
	
	@Override
	public void initiateCart() {
		Cart cart = new Cart();
		addCart(cart);

	}

	@Override
	public BigDecimal getPreTaxCartTotal(int cartId) {
		Cart cart = null;
		
		try {
			cart = CARTS.get(cartId);
		} catch (Exception e){
			logger.error("Sorry, Cart with id #" + cartId + " not found" );
			e.printStackTrace();
		}
		
		return cart.getPreTaxTotal();
	}

	/* OTHER METHODS */
	private void addCart(Cart cart) {
		CARTS.put(cartIdNo, cart);
		this.cartIdNo++;	
	}
	
} // End RegisterServiceImp()
