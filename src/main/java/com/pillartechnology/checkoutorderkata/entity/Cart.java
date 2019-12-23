package com.pillartechnology.checkoutorderkata.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pillartechnology.checkoutorderkata.discounts.Special;

/**
 * Cart allows tracking of items added to a cart
 * as a CartItem. The pre-tax total is calculated
 * every time a CartItem is added.
 *
 */
public class Cart {

	Logger logger = LoggerFactory.getLogger(Cart.class);
	
	private boolean isEmpty = true;
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	private BigDecimal preTaxTotal = new BigDecimal("0.00");
	private Map<Item, Integer> itemsOnSpecial = new HashMap<Item, Integer>();
	
	// Items on special that are Charge By Weight(CBW)
	private Map<Item, Double> itemsOnSpecialCBW = new HashMap<Item, Double>();

	// Default Constructor
	public Cart() {}
	
	
	/* Getters & Setters */
	
	public boolean isEmpty() {
		return isEmpty;
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	
	public Map<Item, Integer> getItemsOnSpecial() {
		return itemsOnSpecial;
	}
	
	public Map<Item, Double> getItemsOnSpecialCBW() {
		return itemsOnSpecialCBW;
	}
	
	public BigDecimal getPreTaxTotal() {
		return preTaxTotal;
	}
	
	public String getPreTaxTotalString() {
		return preTaxTotal.toString() ;
	}
	
	public CartItem getCartItem(CartItem cartItem) {
		int index = cartItems.lastIndexOf(cartItem);
		return cartItems.get(index);
	}
	 
	
	/* Methods */
	
	/**
	 * Adds CartItem to the cart and if the CartItem
	 * contains an Item that is on special, will add
	 * the item to itemsOnSpecial to track the number
	 * of items of that type.
	 * @param cartItem is an item added to the cart containing
	 * a reference to the Item.
	 */
	public void addCartItem(CartItem cartItem) {
		cartItems.add(cartItem);
		Item item = cartItem.getItem();
		
		if (isEmpty) {
			this.isEmpty = false;
		}
		
		// Track number of items that are on special
		if (item.hasSpecial() && item.isChargeByWeight() == false) {
			Integer itemCount = 0;
			
			if (itemsOnSpecial.containsKey(item)) {
				itemCount = itemsOnSpecial.get(item);
				itemsOnSpecial.put(item,  itemCount + 1);
			} else {
				this.itemsOnSpecial.put(item, 1);
			}
		}
		
		if (item.hasSpecial() && item.isChargeByWeight()) {
			Double itemWeight = 0.0;
			
			if (itemsOnSpecialCBW.containsKey(item)) {
				itemWeight = itemsOnSpecialCBW.get(item);
				itemsOnSpecialCBW.put(item, itemWeight + cartItem.getWeight());
			} else {
				this.itemsOnSpecialCBW.put(item, cartItem.getWeight());
			}
		}
	}

	public void deleteLastCartItem() {
		int indexOfLast = cartItems.size() - 1;
		CartItem cartItem = cartItems.get(indexOfLast);
		Item item = cartItems.get(indexOfLast).getItem();
		Integer itemCount = 0;
		Double itemsWeight = 0.0;
		
		if (item.hasSpecial() && item.isChargeByWeight()) {
			itemsWeight = itemsOnSpecialCBW.get(item);
			itemsOnSpecialCBW.put(item, itemsWeight - cartItem.getWeight());
		} else if (item.hasSpecial() && item.isChargeByWeight() == false) {
			itemCount = itemsOnSpecial.get(item);
			itemsOnSpecial.put(item, itemCount - 1);			
		}
		
		cartItems.remove(indexOfLast);
	}
	
	public void deleteCartItem(String itemName) {
		CartItem cartItem = null;
		Item item = null;
		
		Integer itemCount = 0; 
		Double itemsWeight = 0.0;
		
		Iterator<CartItem> cartIterator = cartItems.iterator();
		
		while (cartIterator.hasNext()) {
			cartItem = cartIterator.next();
			if (cartItem.getName().equalsIgnoreCase(itemName)) {
				break;
			}
		}
		
		item = cartItem.getItem();
		
		// remove item from itemsOnSpecial if applicable
		if (item.hasSpecial() && item.isChargeByWeight()) {
			itemsWeight = itemsOnSpecialCBW.get(item);
			itemsOnSpecialCBW.put(item, itemsWeight - cartItem.getWeight());
		} else if (item.hasSpecial() && item.isChargeByWeight() == false) {
			itemCount = itemsOnSpecial.get(item);
			itemsOnSpecial.put(item, itemCount - 1);
		}
		
		cartItems.remove(cartItem);
	}

	/**
	 * Iterate through the cartItems and total up the pre-tax total.
	 * The preTaxTotal is reset to zero each time this method is run
	 * so that if an item or items are deleted, the correct amount can
	 * be calculated.
	 * <p> If there are any specials, the total discount amount is subtracted
	 * from the pre-tax total.
	 * </p>
	 */
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
		this.adjustForSpecials();
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


	/**
	 * Adjusts for any items that are on special, including any limits that
	 * may be applied.
	 */
	public void adjustForSpecials() {
		// Local Variables
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
				
				logger.info("The following non-cbw item is on special: " 
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
		
		/* Get List of specials for items that are charge by weight
		 * to iterate through, log if there aren't any for trouble shooting.
		 */
		
		if (itemsOnSpecialCBW.isEmpty()) {
			logger.info("No specials on CBW items that are charge by weight to calculate");
		} else {
			for (Map.Entry<Item, Double> value : itemsOnSpecialCBW.entrySet()) {
				Item item = value.getKey();
				Double itemWeightCount = value.getValue();
				Special special = item.getSpecial();
				
				logger.info("The following CBW item is on special: "
						+ item.getName() + ", "
						+ special.toString());
				
				/* Then process the sellPrice against the special, for
				 * example Buy N Get M at X Percent Off. We need the
				 * total amount to subtract from the pre-tax total
				 * calculateDiscountAmountCBW(Item item, double itemWeightCount)
				 */
				specialDiscountAmount = special.calculateDiscountAmountCBW(item, itemWeightCount);
			}
			this.preTaxTotal = preTaxTotal.subtract(specialDiscountAmount);	
		}
	}
	
	
} // End Cart();
