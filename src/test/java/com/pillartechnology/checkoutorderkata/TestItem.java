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
	
	@Test
	public void shouldIdentifyHasMarkdownFalseIfNotSet() {
		assertEquals(false, item1.hasMarkdown());
	}
	
	@Test
	public void shouldUpdatePriceIfMarkdownIsAdded() {
		item1.setPrice("1.99");
		item1.addMarkdownAmount("1.00");
		
		assertEquals("0.99", item1.getPrice().toString());
	}
	
	@Test
	public void shouldIdentifyHasMarkdownTrueIfMarkdownAdded() {
		item1.setPrice("1.99");
		item1.addMarkdownAmount("1.00");
		
		assertEquals(true, item1.hasMarkdown());
	}
} // End TestItem()
