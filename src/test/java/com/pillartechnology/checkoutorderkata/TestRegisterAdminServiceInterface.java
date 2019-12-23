package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pillartechnology.checkoutorderkata.service.RegisterAdminService;

@SpringBootTest
public class TestRegisterAdminServiceInterface {

	private RegisterAdminService registerAdminService;
	
	@Autowired
	public void setRegisterAdminService(RegisterAdminService registerAdminService) {
		this.registerAdminService = registerAdminService;
	}
	
	@Test
	public void shouldCreateItemAndAddToInventory() {
		registerAdminService.createItem("soup","1.99",false);
		
		assertEquals("soup", registerAdminService.getItem("Soup").getName());
	}
	
	@Test
	public void shouldCreateSpecialBuyNForXAndAddToSpecials() {
		registerAdminService.createSpecialBuyNForX("2 For $5.00", 2, "5.00");
		
		assertEquals("2 for $5.00", registerAdminService.getSpecial("2 for $5.00").getName().toLowerCase());
	}

	@Test
	public void shouldCreateSpecialBuyNGetMAtXPercentOffAndAddToSpecials() {
		registerAdminService.createSpecialBuyNGetMAtXPercentOff("Buy 2 get 1 half off", 2, 1, 50);
		
		assertEquals("buy 2 get 1 half off", registerAdminService.getSpecial("Buy 2 get 1 half off").getName().toLowerCase());
	}
	
} //EndTestAdminRegisterInterface
