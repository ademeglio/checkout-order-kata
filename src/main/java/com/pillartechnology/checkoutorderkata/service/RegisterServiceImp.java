package com.pillartechnology.checkoutorderkata.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pillartechnology.checkoutorderkata.entity.Cart;
import com.pillartechnology.checkoutorderkata.entity.CartItem;
import com.pillartechnology.checkoutorderkata.entity.Item;

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
	public Cart getCart(Integer cartIdNo) {
		Cart cart = null;

		try {
			cart = CARTS.get(cartIdNo);
		} catch (Exception e) {
			logger.error("Sorry, cart with id " + cartIdNo + " not found");
			e.printStackTrace();
		}
		
		return cart;
	}
	
	@Override
	public BigDecimal getPreTaxCartTotal(Integer cartIdNo) {
		Cart cart = this.getCart(cartIdNo);
				
		return cart.getPreTaxTotal();
	}

	@Override
	public void scanItem(Integer cartIdNo, String itemName, double weight) {
		Cart cart = this.getCart(cartIdNo);
		Item item = null;
		
		// Get item from inventory 
		try {
			item = RegisterAdminService.INVENTORY
					.get(itemName.toLowerCase());
		} catch (Exception e) {
			logger.error("Sorry, " + itemName + " not found");
			e.printStackTrace();
		}
		
		// Create and add Cart Item
		try {
			if(item.isChargeByWeight()) {
				CartItem cartItem = new CartItem(item, weight);
				cart.addCartItem(cartItem);
				cart.calculatePreTaxTotal();
			} else {
				CartItem cartItem = new CartItem(item);
				cart.addCartItem(cartItem);
				cart.calculatePreTaxTotal();
			}
		} catch (Exception e) {
			logger.error("Something went wrong while adding a cart item");
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void removeLastItemScanned(Integer cartIdNo) {
		Cart cart = this.getCart(cartIdNo);
		
		cart.deleteLastCartItem();
		cart.calculatePreTaxTotal();
	}
	
	
	/* OTHER METHODS */
	private void addCart(Cart cart) {
		CARTS.put(cartIdNo, cart);
		logger.info("Added new cart with id: " + cartIdNo);
		this.cartIdNo++;	
	}

	
} // End RegisterServiceImp()
