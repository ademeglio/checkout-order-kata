package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pillartechnology.checkoutorderkata.discounts.Markdown;
import com.pillartechnology.checkoutorderkata.discounts.Special;
import com.pillartechnology.checkoutorderkata.entity.Item;
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
	
	@Test
	public void shouldCreateMarkdownAndAddToMarkdowns() {
		registerAdminService.createMarkdown("$1.00 OFF", "1.00");
		
		assertEquals("$1.00 off", registerAdminService.getMarkdown("$1.00 OFF").getDescription().toLowerCase());
	}
	
	@Test
	public void shouldProvideListOfInventory() {
		registerAdminService.createItem("test2", "2.00", false);
		registerAdminService.createItem("test1", "1.00", false);
		
		ArrayList<Item> inventory = new ArrayList<Item>(registerAdminService.getInventory());
		assertEquals(false, inventory.isEmpty());
	}
	
	@Test
	public void shouldProvideListOfSpecials() {
		registerAdminService.createSpecialBuyNGetMAtXPercentOff("Buy 2 get 1 half off", 2, 1, 50);
		registerAdminService.createSpecialBuyNForX("2 For $5.00", 2, "5.00");

		ArrayList<Special> specials = new ArrayList<Special>(registerAdminService.getSpecials());
		
		assertEquals(false, specials.isEmpty());
		
	}
	
	@Test
	public void shouldProvideListOfMarkdowns() {
		registerAdminService.createMarkdown("$1.00 OFF", "1.00");
		registerAdminService.createMarkdown("$2.00 OFF", "2.00");
		
		ArrayList<Markdown> markdowns = new ArrayList<Markdown>(registerAdminService.getMarkdowns());
		
		assertEquals(false, markdowns.isEmpty());
	}
	
	@Test
	public void shouldUpdateItemWhenAddingSpecialOrMarkdown() {
		registerAdminService.createItem("test2", "2.00", false);
		
		Item item = registerAdminService.getItem("test2");
		
		registerAdminService.createMarkdown("$1.00 OFF", "1.00");
		registerAdminService.createSpecialBuyNForX("2 For $5.00", 2, "5.00");
		
		// Update Price, add Markdown, add Special
		registerAdminService.updateItem("test2", "2.50", "$1.00 off", "2 for $5.00");
		
		assertAll("item",
				() -> assertEquals("2.50", item.getDefaultPrice().toString()),
				() -> assertEquals("$1.00 OFF", item.getMarkdown().getDescription()),
				() -> assertEquals("2 For $5.00", item.getSpecial().getName()));
	}
} //EndTestAdminRegisterInterface
