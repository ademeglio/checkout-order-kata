package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCart {
	
	Cart cart = new Cart();
	Item item1 = new Item();
	Item item2 = new Item();
	
	@Test
	public void shouldStartEmpty() {
		assertEquals(true, cart.isEmpty());
	}
	
	@Test
	public void shouldBeAbleToAddAnItem() {
		cart.addItem(item1);
		
		assertEquals(item1, cart.getItem(item1));
	}
	
	@Test
	public void shouldNotBeEmptyAfterAddingItem() {
		cart.addItem(item1);
		
		assertEquals(false, cart.isEmpty());
	}
	
	@Test
	public void shouldBeAbleToAddMoreThanOneItem() {
		
		cart.addItem(item1);
		cart.addItem(item2);
		
		assertEquals(2, cart.getItems().size());
	}
	
	@Test
	public void shouldBeAbleToDeleteLastAddedItem() {
		
		cart.addItem(item1);
		cart.addItem(item2);
		
		cart.deleteLastItem();
		
		assertEquals(1, cart.getItems().size());
		assertEquals(false, cart.getItems().contains(item2));
	}
	
	@Test void shouldReturnPreTaxTotalAsZeroWhenEmpty() {
		assertEquals(0.00, cart.getPreTaxTotal());
	}
	
} // End TestCart()
