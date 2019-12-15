package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCartItem {

	@Test
	public void shouldReturnCartPrice() {
		Item item1 = new Item("Soup", "1.99");
		
		CartItem cartItem1 = new CartItem(item1);
		
		assertEquals("1.99", cartItem1.getPrice().toString());
		
	}
	
	@Test
	public void shouldReturnInitialCartItemSalePrice() {
		Item item1 = new Item("Soup", "1.99");
		
		CartItem cartItem1 = new CartItem(item1);
		
		assertEquals("1.99", cartItem1.getSalePrice().toString());
	}
	
	
	
} // End TestCartItem()
