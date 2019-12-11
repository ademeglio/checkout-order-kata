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
		assertEquals(cart.isEmpty(), true);
	}
	
	@Test
	public void shouldBeAbleToAddAnItem() {
		cart.addItem(item1);
		
		assertEquals(cart.getItem(item1), item1);
	}
	
	@Test
	public void shouldNotBeEmptyAfterAddingItem() {
		cart.addItem(item1);
		
		assertEquals(cart.isEmpty(), false);
	}
	
	@Test
	public void shouldBeAbleToAddMoreThanOneItem() {
		
		cart.addItem(item1);
		cart.addItem(item2);
		
		assertEquals(cart.getItems().size(), 2);
	}
	
	@Test
	public void shouldBeAbleToDeleteLastAddedItem() {
		
		cart.addItem(item1);
		cart.addItem(item2);
		
		cart.deleteLastItem();
		
		assertEquals(cart.getItems().size(), 1);
		assertEquals(cart.getItems().contains(item2), false);
	}
	
} // End TestCart()
