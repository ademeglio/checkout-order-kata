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
	public void shouldIdentifyIsChargedByWeightTrueIfSetToTrue() {
		Item item1 = new Item("Bananas");
		
		item1.setChargeByWeight(true);
		
		assertEquals(true, item1.isChargeByWeight());
	}
	
	
} // End TestItem()
