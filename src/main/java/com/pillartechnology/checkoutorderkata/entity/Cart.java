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
 * as a {@link CartItem}. The pre-tax total is calculated
 * every time a {@link CartItem} is added.
 *
 *@version 0.1.0
 */
public class Cart {

	Logger logger = LoggerFactory.getLogger(Cart.class);
	
	private boolean isEmpty = true;
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	
	/** recalculated whenever a CartItem is added or removed */
	private BigDecimal preTaxTotal = new BigDecimal("0.00");
	
	/** 
	 * Item Maps that store quantity of a type of item on special. This is used
	 * for calculating the total discount of an order.
	 * <p>itemsOnSpecialCBW refers to specials on items that are 
	 * charge by weight (CBW)
	 */
	private Map<Item, Integer> itemsOnSpecial = new HashMap<Item, Integer>();
	private Map<Item, Double> itemsOnSpecialCBW = new HashMap<Item, Double>();

	/** Default Constructor */
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
	
	/**
	 * Returns the last index of given {@link CartItem} from
	 * cartItems list. 
	 * 
	 * @param cartItem CartItem to get last index of.
	 * @return index of CartItem.
	 */
	public CartItem getCartItem(CartItem cartItem) {
		int index = cartItems.lastIndexOf(cartItem);
		return cartItems.get(index);
	}
	 
	
	/* Methods */
	
	/**
	 * Adds {@link CartItem} to the cart. If this CartItem
	 * contains an {@link Item} that has a {@link Special}, will add
	 * the item to itemsOnSpecial map to track the number
	 * of items of that special type.
	 * <p> Should the Item be a charge by weight type, it is added
	 * to itemsOnSpecialCBW map to track total weight per item
	 * type on special.
	 *
	 * @param cartItem {@link CartItem} item to add to the cart.
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

	/** 
	 * Delete's the last scanned CartItem from the cart. It also
	 * removes the quantity or weight from the respective items on
	 * special map.
	 */
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
	
	/**
	 * Iterates through the cartItems until it finds
	 * a cartItem matching the itemName.
	 * <p>It also removes the quantity or weight from the respective items on
	 * special map.</P>
	 * 
	 * @param itemName item name to look for.
	 */
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
	 * so that if any items are deleted, the correct amount can
	 * be recalculated.
	 * <p> If there are any specials, the total discount amount is subtracted
	 * from the pre-tax total.
	 * </p>
	 */
	public void calculatePreTaxTotal() {
		preTaxTotal = new BigDecimal("0.00");
		
		for (CartItem cartItem : cartItems) {
			preTaxTotal = preTaxTotal.add(cartItem.getSellPrice());
		}
		
		/* Go through specials and adjust price accordingly
		 * This way, if an item or items are removed to the point
		 * that the special no longer applies, it will recalculate
		 * without applying the special.
		 */
		this.adjustForSpecials();
	}

	/**
	 * Returns the quantity of given {@link Item} that has a special.
	 * The item is the key in the map and the value is the number of items
	 * of that type.
	 * 
	 * @param item Item to search for.
	 * @return int representing the quantity of given Item.
	 * @exception e If an item cannot be found in the map.
	 */
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
	 * Adjusts for any items that have a {@link Special}. <em>This includes any limits that
	 * may be applied to the Special</em>.
	 */
	public void adjustForSpecials() {
	
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
