package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCart {
	
	Cart cart = new Cart();
	
	@Test
	public void shouldStartEmpty() {
		assertEquals(cart.isEmpty(), true);
	}
	
	@Test
	public void shouldBeAbleToAddAnItem() {
		Item item = new Item();
		
		cart.addItem(item);
		
		assertEquals(cart.getItem(item), item);
	}
	
	
	
} // End TestCart()
