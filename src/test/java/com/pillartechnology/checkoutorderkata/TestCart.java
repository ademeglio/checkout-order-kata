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
	
} // End TestCart()
