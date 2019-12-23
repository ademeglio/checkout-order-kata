package com.pillartechnology.checkoutorderkata.service;

import java.util.Collection;
import java.util.HashMap;

import com.pillartechnology.checkoutorderkata.discounts.Markdown;
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
	HashMap<String, Markdown> MARKDOWNS = new HashMap<String, Markdown>();

	void createItem(String itemName, String itemPrice, boolean isChargeByWeight);

	Item getItem(String itemName);

	void createSpecialBuyNForX(String specialName, int buyQtyRequirement, String discountPrice);

	void createSpecialBuyNGetMAtXPercentOff(String specialName, int buyQtyRequirement, 
			int receiveQtyItems, double percentOff); 
	
	Special getSpecial(String specialName);

	void createMarkdown(String description, String markdownAmount);

	Markdown getMarkdown(String markdownDescription);

	Collection<Item> getInventory();

	Collection<? extends Special> getSpecials();
	

} // End RegisterAdminService()
