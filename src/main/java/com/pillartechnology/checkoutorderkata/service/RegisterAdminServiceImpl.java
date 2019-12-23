package com.pillartechnology.checkoutorderkata.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
			logger.warn("Sorry, " + itemName + " cannot be found.");
			return item;
		}
		return item;
	}

	
	

} // End RegisterAdminServiceImpl()
