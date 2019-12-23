package com.pillartechnology.checkoutorderkata.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pillartechnology.checkoutorderkata.discounts.BuyNForX;
import com.pillartechnology.checkoutorderkata.discounts.BuyNGetMatXPercentOff;
import com.pillartechnology.checkoutorderkata.discounts.Special;
import com.pillartechnology.checkoutorderkata.entity.Item;

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

	

} // End RegisterAdminServiceImpl()
