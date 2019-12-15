package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
	
	@Test
	public void shouldReturnMarkedDownCartItemSalePrice() {
		Markdown markdownBy1Dollar = new Markdown("$1.00 OFF", "1.00");
		Item item1 = new Item("Soup", "1.99");
		item1.addMarkdown(markdownBy1Dollar);
		
		CartItem cartItem1 = new CartItem(item1);
		assertEquals("0.99", cartItem1.getSalePrice().toString());	
	}
	
	@Test
	public void shouldReturnTrueIfCartItemChargesByWeightIsSetTrue() {

		Item item1 = new Item("Apples", "1.89", true);
		
		CartItem cartItem = new CartItem(item1);
		
		assertEquals(true, cartItem.isChargeByWeight());
	}
	
	
	
} // End TestCartItem()
