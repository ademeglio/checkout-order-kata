package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cart {

	Logger logger = LoggerFactory.getLogger(Cart.class);
	
	private boolean isEmpty = true;
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	private BigDecimal preTaxTotal = new BigDecimal("0.00");
	private Map<Item, Integer> itemsOnSpecial = new HashMap<Item, Integer>();

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
		
		if (isEmpty) {
			this.isEmpty = false;
		}
		
		// Track number of items that are on special
		if (cartItem.getItem().hasSpecial()) {
			Item item = cartItem.getItem();
			Integer itemCount = 0;
			
			if (itemsOnSpecial.containsKey(item)) {
				itemCount = itemsOnSpecial.get(item);
				itemsOnSpecial.put(item,  itemCount + 1);
			} else {
				this.itemsOnSpecial.put(item, 1);
			}
		}
	}
	
	public CartItem getCartItem(CartItem cartItem) {
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
		
		/* Go through specials and manually adjust price accordingly
		 * This way, if an item or items are removed to the point
		 * that the special no longer applies, it will recalculate
		 * accordingly.
		 */
		
//		preTaxTotal = preTaxTotal.adjustForSpecials();
	}

	public void deleteCartItem(CartItem cartItem1) {
		cartItems.remove(cartItem1);
	}

	public int countItemOnSpecial(Item item) {
		
		int qtyItemOnSpecial = 0;
		
		try {
			 qtyItemOnSpecial = itemsOnSpecial.get(item);
		}
		catch(Exception e) {
			return qtyItemOnSpecial;
		}
		
		return qtyItemOnSpecial;
	}


	public void adjustForSpecials() {
		// Instance Variables
		BigDecimal specialDiscountAmount = new BigDecimal(0.0);
		/* Get List of Specials to iterate through, log if
		 * there aren't any for trouble shooting. 
		 */
		if (itemsOnSpecial.isEmpty()) {
			logger.info("No specials to calculate");
		} else {
			for (Map.Entry<Item, Integer> value : itemsOnSpecial.entrySet()) {
				
				Item item = value.getKey();
				int itemBuyCount = value.getValue();
				Special special = item.getSpecial();
				
				/* Need a reference to the CartItem.getItem() to get the special
				 * to apply for each item on special
				 */
				logger.info("The following item is on special: " 
						+ item.getName() + ", " 
						+ special.toString());

				/* Then process the sellPrice against the special, for
				 * example Buy N Get M at X Percent Off. We need the
				 * total amount to subtract from the pretax total
				 */
				
				specialDiscountAmount = special.calculateDiscountAmount(item, itemBuyCount);
				
				
				
			}
		
		this.preTaxTotal = preTaxTotal.subtract(specialDiscountAmount);
			
		}
		// for each key
		
			
		
	}
	
	
} // End Cart();
