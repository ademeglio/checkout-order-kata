package com.pillartechnology.checkoutorderkata.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pillartechnology.checkoutorderkata.discounts.BuyNForX;
import com.pillartechnology.checkoutorderkata.discounts.BuyNGetMatXPercentOff;
import com.pillartechnology.checkoutorderkata.discounts.Markdown;
import com.pillartechnology.checkoutorderkata.discounts.Special;
import com.pillartechnology.checkoutorderkata.entity.Item;

/**
 * Implementation of {@link RegisterAdminService}.
 * 
 * @version 0.1.0
 */
@Service
public class RegisterAdminServiceImpl implements RegisterAdminService {

	private static final Logger logger = LoggerFactory.getLogger(RegisterAdminServiceImpl.class);
	
	
	@Override
	public void createItem(String itemName, String itemPrice, boolean isChargeByWeight) {
		itemName = itemName.toLowerCase();
		Item item = new Item(itemName, itemPrice, isChargeByWeight);
		
		INVENTORY.put(itemName, item);
	}

	@Override
	public Item getItem(String itemName) {
		Item item = INVENTORY.get(itemName.toLowerCase());
		
		if (item == null) {
			logger.error("Sorry, " + itemName + " cannot be found.");
			return item;
		}
		return item;
	}

	@Override
	public void createSpecialBuyNForX(String specialName, int buyQtyRequirement, String discountPrice) {
		BuyNForX special = new BuyNForX(buyQtyRequirement, discountPrice);
		special.setName(specialName);
		
		SPECIALS.put(specialName.toLowerCase(), special);
	}

	@Override
	public void createSpecialBuyNGetMAtXPercentOff(String specialName, int buyQtyRequirement, int receiveQtyItems,
			double percentOff) {
		BuyNGetMatXPercentOff special = new BuyNGetMatXPercentOff(buyQtyRequirement,
				receiveQtyItems, percentOff);
		special.setName(specialName);
		
		SPECIALS.put(specialName.toLowerCase(), special);
	}
	
	@Override
	public Special getSpecial(String specialName) {
		Special special = SPECIALS.get(specialName.toLowerCase());
		
		if (special == null) {
			logger.error("Sorry, " + specialName + " cannot be found.");
			return special;
		}
		return special;
	}

	@Override
	public void createMarkdown(String description, String markdownAmount) {
		Markdown markdown = new Markdown(description, markdownAmount);
		
		MARKDOWNS.put(description.toLowerCase(),markdown);
	}

	@Override
	public Markdown getMarkdown(String markdownDescription) {
		Markdown markdown = MARKDOWNS.get(markdownDescription.toLowerCase());
		
		if (markdown == null) {
			logger.error("Sorry, " + markdownDescription + " cannot be found.");
			return markdown;
		}
		return markdown;
	}

	@Override
	public Collection<Item> getInventory() {
		ArrayList<Item> items = new ArrayList<Item>(INVENTORY.values());
		
		Comparator<Item> compareByName = (Item o1, Item o2) ->
			o1.getName().compareTo(o2.getName());
		items.sort(compareByName);

		return items;
	}

	@Override
	public Collection<? extends Special> getSpecials() {
		ArrayList<Special> specials = new ArrayList<Special>(SPECIALS.values());
		
		Comparator<Special> compareByName = (Special o1, Special o2) ->
			o1.getName().compareTo(o2.getName());
		specials.sort(compareByName);
		
		return specials;
	}

	@Override
	public Collection<Markdown> getMarkdowns() {
		ArrayList<Markdown> markdowns = new ArrayList<Markdown>(MARKDOWNS.values());
		
		Comparator<Markdown> compareByDescription = (Markdown o1, Markdown o2) ->
			o1.getDescription().compareTo(o2.getDescription());
		markdowns.sort(compareByDescription);
		
		return markdowns;
	}

	@Override
	public void updateItem(String itemName, String newDefaultPrice, 
			String markdownDescription, String specialName) {
		Item item = null;
		Markdown markdown = null;
		Special special = null;
		
		// Try and fetch item
		try {
			item = INVENTORY.get(itemName);
		} catch (Exception e) {
			logger.error("Sorry, " + itemName + " not found");
			e.printStackTrace();
		}
		
		
		if (item != null) {
			
			if (newDefaultPrice != null && !newDefaultPrice.isEmpty()) {
				item.setDefaultPrice(newDefaultPrice);
			}
			
			if (markdownDescription != null && !markdownDescription.isEmpty()) {
				try {
					markdown = MARKDOWNS.get(markdownDescription);
				} catch (Exception e) {
					logger.error("Sorry, " + markdownDescription + " not found");
					e.printStackTrace();
				}
				item.addMarkdown(markdown);
			}
			
			if (specialName != null && !specialName.isEmpty()) {
				try {
					special = SPECIALS.get(specialName);
				} catch (Exception e) {
					logger.error("Sorry, " + specialName + " not found");
					e.printStackTrace();
				}
				item.addSpecial(special);
			}
		}
	}

	@Override
	public void addLimitToSpecial(String specialName, int limitQty) {
		Special special = this.getSpecial(specialName);
		
		special.setLimit(limitQty);
	}

	

} // End RegisterAdminServiceImpl()
