package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCart {
	
	Cart cart = new Cart();
	Item item = new Item();
	
	@Test
	public void shouldStartEmpty() {
		assertEquals(cart.isEmpty(), true);
	}
	
	@Test
	public void shouldBeAbleToAddAnItem() {
		cart.addItem(item);
		
		assertEquals(cart.getItem(item), item);
	}
	
	@Test
	public void shouldNotBeEmptyAfterAddingItem() {
		cart.addItem(item);
		
		assertEquals(cart.isEmpty(), false);
	}
	
	@Test
	public void shouldBeAbleToAddMoreThanOneItem() {
		Item item2 = new Item();
		
		cart.addItem(item);
		cart.addItem(item2);
		
		assertEquals(cart.getItems().size(), 2);
	}
	
	@Test
	public void shouldBeAbleToDeleteLastAddedItem() {
		Item item2 = new Item();
		
		cart.addItem(item);
		cart.addItem(item2);
		
		cart.deleteLastItem();
		
		assertEquals(cart.getItems().size(), 1);
		assertEquals(cart.getItems().contains(item2), false);
	}
	
} // End TestCart()
