package com.pillartechnology.checkoutorderkata.service;

import java.math.BigDecimal;
import java.util.HashMap;

import com.pillartechnology.checkoutorderkata.entity.Cart;

/**
 * Interface for managing cart actions.
 * 
 * 
 */
public interface RegisterService {

	HashMap<Integer, Cart> CARTS = new HashMap<Integer, Cart>();

	/** 
	 * Initiation of cart or order.   
	 */
	void initiateCart();

	/**
	 * Get the {@link Cart} from <code>CARTS</cart> using the <code>cartIdNo</code>
	 * as a key for the object map.
	 *  
	 * @param cartIdNo 			integer representing the unique cart identifier.
	 * @return					{@link Cart}
	 * @see						Cart
	 */
	Cart getCart(Integer cartIdNo);
	
	/**
	 * Get the pre-tax cart total using the <code>cartIdNo</code>
	 * as a key for the Cart object map.
	 * 
	 * @param cartIdNo			integer representing the unique cart identifier.
	 * @return					{@link Cart) pre-tax total as a BigDecimal for 
	 * 							further processing by a consumer.
	 * @see						Cart
	 */
	BigDecimal getPreTaxCartTotal(Integer cartIdNo);

	/**
	 * Scan adds {@link CartItem} to specified cart. Uses <code>cartIdNo</code> as
	 * a key for <code>CARTS</code>; <code>itemName</code> as a key for the <code>
	 * INVENTORY</code>; and if the item <code>isChargeByWeight</code> is 
	 * <code>true</code> passes the weight for {@link CartItem} initiation.
	 * 
	 * @param cartIdNo			integer representing the unique cart identifier.
	 * @param itemName			name of the item to search for.
	 * @param weight			weight of the item, if applicable.
	 * @see						Item
	 * @see						CartItem
	 * @see						Cart
	 */
	void scanItem(Integer cartIdNo, String itemName, double weight);

	/**
	 * Removes the last scanned {@link CartItem} from the specified cart.
	 * 
	 * @param cartIdNo			integer representing the unique cart identifier.
	 */
	void removeLastItemScanned(Integer cartIdNo);
	
	/**
	 * Removes a scanned {@link CartItem} from the specified {@link Cart} by searching
	 * through the cart for a CartItem using the same scanned<itemName>.
	 * 
	 * @param cartIdNo			integer representing the unique cart identifier.
	 * @param itemName			name of the item to search for.
	 * @see						CartItem
	 * @see						Cart
	 */
	void removeScannedItem(Integer cartIdNo, String itemName);

	/**
	 * Simple way to empty an existing cart if the user needs to start over.
	 * Uses <cartIdNo> to search for the {@link Cart}.
	 * @param cartIdNo			integer representing the unique cart identifier.
	 * @see						Cart
	 */
	void clearCart(Integer cartIdNo);

} // End RegisterService()
