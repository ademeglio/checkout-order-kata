package com.pillartechnology.checkoutorderkata.service;

import java.util.HashMap;

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

	void createItem(String itemName, String itemPrice, boolean isChargeByWeight);

	Item getItem(String itemName); 
	

} // End RegisterAdminService()
