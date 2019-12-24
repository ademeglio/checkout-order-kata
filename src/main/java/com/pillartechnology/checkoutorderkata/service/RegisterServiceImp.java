package com.pillartechnology.checkoutorderkata.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pillartechnology.checkoutorderkata.entity.Cart;
import com.pillartechnology.checkoutorderkata.entity.CartItem;
import com.pillartechnology.checkoutorderkata.entity.Item;

/**
 * Implementation of Register Service.
 * 
 * @version 0.1.0
 * @see RegisterService
 */
@Service
public class RegisterServiceImp implements RegisterService {

	private static final Logger logger = LoggerFactory.getLogger(RegisterServiceImp.class);
	
	/** unique cart identification */
	private Integer cartIdNo = 0;
	
	/** 
	 * Initiation of cart or order. Once initiated
	 * the <code>addCart</code> method will reference
	 * <code>cartIdNo</code> and increment to the
	 * next cartIdNo.  
	 */
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

	/**
	 * Scan adds {@link CartItem} to specified cart. Uses <code>cartIdNo</code> as
	 * a key for <code>CARTS</code>; <code>itemName</code> as a key for the <code>
	 * INVENTORY</code>; and if the item <code>isChargeByWeight</code> is 
	 * <code>true</code> passes the weight for {@link CartItem} initiation.
	 * <p>Each time this method is run, the cart's pre-tax total is recalculated.</p>
	 * 
	 * @param cartIdNo			integer representing the unique cart identifier.
	 * @param itemName			name of the item to search for.
	 * @param weight			weight of the item, if applicable.
	 * @see						Item
	 * @see						CartItem
	 * @see						Cart
	 */
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
	
	/**
	 * Removes the last scanned {@link CartItem} from the specified cart.
	 * <p>Each time this method is run, the cart's pre-tax total is recalculated.</p>
	 * 
	 * @param cartIdNo			integer representing the unique cart identifier.
	 */
	@Override
	public void removeLastItemScanned(Integer cartIdNo) {
		Cart cart = this.getCart(cartIdNo);
		
		cart.deleteLastCartItem();
		cart.calculatePreTaxTotal();
	}
	
	/**
	 * Removes a scanned {@link CartItem} from the specified {@link Cart} by searching
	 * through the cart for a CartItem using the same scanned<itemName>.
	 * <p>Each time this method is run, the cart's pre-tax total is recalculated.</p>
	 * 
	 * @param cartIdNo			integer representing the unique cart identifier.
	 * @param itemName			name of the item to search for.
	 * @see						CartItem
	 * @see						Cart
	 */
	@Override
	public void removeScannedItem(Integer cartIdNo, String itemName) {
		Cart cart = this.getCart(cartIdNo);
		
		cart.deleteCartItem(itemName);
		cart.calculatePreTaxTotal();
	}
	
	@Override
	public void clearCart(Integer cartIdNo) {
		Cart cart = this.getCart(cartIdNo);
		cart.getCartItems().clear();
		cart.getItemsOnSpecial().clear();
		cart.getItemsOnSpecialCBW().clear();
		cart.calculatePreTaxTotal();
	}
	
	
	/* OTHER METHODS */
	private void addCart(Cart cart) {
		CARTS.put(cartIdNo, cart);
		logger.info("Added new cart with id: " + cartIdNo);
		this.cartIdNo++;	
	}

	
} // End RegisterServiceImp()
