package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.pillartechnology.checkoutorderkata.discounts.Markdown;
import com.pillartechnology.checkoutorderkata.entity.CartItem;
import com.pillartechnology.checkoutorderkata.entity.Item;

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
	public void shouldReturnCartItemDefaultPrice() {
		// Soup @ $1.99 per unit
		
		assertEquals("1.99", cartItem1.getItem().getDefaultPrice().toString());
	}
	
	@Test
	public void shouldReturnInitialCartItemSellPrice() {
		// Soup @ $1.99 per unit
		
		assertEquals("1.99", cartItem1.getSellPrice().toString());
	}
	
	@Test
	public void shouldReturnMarkedDownCartItemSellPrice() {
		// Soup @ $1.99 per unit
		Markdown markdownBy1Dollar = new Markdown("$1.00 OFF", "1.00");
		item1.addMarkdown(markdownBy1Dollar);
		
		CartItem cartItem1 = new CartItem(item1);
		assertEquals("0.99", cartItem1.getSellPrice().toString());	
	}
	
	@Test
	public void shouldReturnTrueIfCartItemChargesByWeightIsSetTrue() {
		Item item2 = new Item("Apples", "1.89", true);
		
		CartItem cartItem2 = new CartItem(item2);
		
		assertEquals(true, cartItem2.getItem().isChargeByWeight());
	}
	
	@Test
	public void shouldReturnFalseIfCartItemChargeByWeightIsNotSet() {
		Item item3 = new Item("Pears", "1.89");
		
		CartItem cartItem3 = new CartItem(item3);
		
		assertEquals(false, cartItem3.getItem().isChargeByWeight());
	}
	
	@Test
	public void shouldReturnCartItemWeightIfWeightIsEntered() {
		Item item4 = new Item("Steak", "3.00", true);
		
		CartItem cartItem4 = new CartItem(item4);
		cartItem4.setWeight(5.0);
				
		assertEquals(5.0, cartItem4.getWeight());
	}
	
	@Test
	public void shouldReturnCorrectSellPriceIfCartItemChargeByWeightIsSetTrue() {
		Item item5 = new Item("Chicken", "2.00", true);
		
		CartItem cartItem5 = new CartItem(item5);
		
		cartItem5.setWeight(5.0);
		cartItem5.calculateSellPrice();
		
		assertEquals("10.00", cartItem5.getSellPrice().toString());
		
	}
	
} // End TestCartItem()
