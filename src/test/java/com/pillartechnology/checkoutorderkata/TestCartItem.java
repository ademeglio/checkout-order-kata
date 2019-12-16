package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCartItem {

	Item item1 = new Item("Soup", "1.99");
	CartItem cartItem1;
	
	@BeforeEach
	public void setUp(){
		cartItem1 = new CartItem(item1);		
	}
	
	/* TESTS */
	
	@Test
	public void shouldReturnCartPrice() {
		// Soup @ $1.99 per unit
		
		assertEquals("1.99", cartItem1.getPrice().toString());
	}
	
	@Test
	public void shouldReturnInitialCartItemSalePrice() {
		// Soup @ $1.99 per unit
		
		assertEquals("1.99", cartItem1.getSalePrice().toString());
	}
	
	@Test
	public void shouldReturnMarkedDownCartItemSalePrice() {
		// Soup @ $1.99 per unit
		Markdown markdownBy1Dollar = new Markdown("$1.00 OFF", "1.00");
		item1.addMarkdown(markdownBy1Dollar);
		
		CartItem cartItem1 = new CartItem(item1);
		assertEquals("0.99", cartItem1.getSalePrice().toString());	
	}
	
	@Test
	public void shouldReturnTrueIfCartItemChargesByWeightIsSetTrue() {
		Item item2 = new Item("Apples", "1.89", true);
		
		CartItem cartItem2 = new CartItem(item2);
		
		assertEquals(true, cartItem2.isChargeByWeight());
	}
	
	@Test
	public void shouldReturnFalseIfCartItemChargeByWeightIsNotSet() {
		Item item3 = new Item("Pears", "1.89");
		
		CartItem cartItem3 = new CartItem(item3);
		
		assertEquals(false, cartItem3.isChargeByWeight());
	}
	
	@Test
	public void shouldReturnCartItemWeightIfWeightIsEntered() {
		Item item2 = new Item("Apples", "1.89", true);
		
		CartItem cartItem2 = new CartItem(item2);
		cartItem2.setWeight(5.0);
		
		assertEquals(5.0, cartItem2.getWeight());
	}
	
	
} // End TestCartItem()
