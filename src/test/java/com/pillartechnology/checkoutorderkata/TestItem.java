package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestItem {

	@Test
	public void shouldBeAbleToReturnWhatTheItemIs() {
		Item item1 = new Item("Soup");
		
		assertEquals("Soup", item1.getName());
	}
	
	@Test
	public void shouldIdentifyIsChargedByWeightIsFalseIfNotSet() {
		Item item1 = new Item("Soup");
		
		assertEquals(false, item1.isChargeByWeight());
	}
	
	@Test
	public void shouldIdentifyIsChargedByWeightTrueIfSetToTrue() {
		Item item2 = new Item("Bananas");
		
		item2.setChargeByWeight(true);
		
		assertEquals(true, item2.isChargeByWeight());
	}
	
	@Test
	public void shouldAcceptPriceToChargeAsString() {
		Item item1 = new Item("Soup");
		
		item1.setPrice("2.99");
		
		assertEquals("2.99", item1.getPrice().toString());
	}
	
	
} // End TestItem()
