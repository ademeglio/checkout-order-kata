package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestItem {

	Item item1 = new Item("Soup");
	Item item2 = new Item("Bananas");
	
	@Test
	public void shouldBeAbleToReturnWhatTheItemIs() {
		assertEquals("Soup", item1.getName());
	}
	
	@Test
	public void shouldIdentifyIsChargedByWeightIsFalseIfNotSet() {
		assertEquals(false, item1.isChargeByWeight());
	}
	
	@Test
	public void shouldIdentifyIsChargedByWeightTrueIfSetToTrue() {
		item2.setChargeByWeight(true);
		
		assertEquals(true, item2.isChargeByWeight());
	}
	
	@Test
	public void shouldAcceptPriceToChargeAsString() {
		item1.setPrice("2.99");
		
		assertEquals("2.99", item1.getPrice().toString());
	}
	
	
} // End TestItem()
