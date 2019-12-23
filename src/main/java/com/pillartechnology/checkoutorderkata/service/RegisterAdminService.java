package com.pillartechnology.checkoutorderkata.service;

import java.util.HashMap;

import com.pillartechnology.checkoutorderkata.discounts.Special;
import com.pillartechnology.checkoutorderkata.entity.Item;

/**
 * Interface for managing:
 * <ul>
 *   <li>Inventory</li>
 *   <li>Specials</li>
 *   <li>Markdowns</li>
 * </ul>
 */
public interface RegisterAdminService {

	HashMap<String, Item> INVENTORY = new HashMap<String, Item>();
	HashMap<String, Special> SPECIALS = new HashMap<String, Special>();

	void createItem(String itemName, String itemPrice, boolean isChargeByWeight);

	Item getItem(String itemName);

	void createSpecialBuyNForX(String specialName, int buyQtyRequirement, String discountPrice);

	Special getSpecial(String specialName); 
	

} // End RegisterAdminService()
