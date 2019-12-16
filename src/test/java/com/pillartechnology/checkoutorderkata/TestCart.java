package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCart {
	
	Cart cart = new Cart();
	Item item1 = new Item("Soup", "1.99");
	Item item2 = new Item("Bread", "1.99");
	
	CartItem cartItem1;
	CartItem cartItem2;
	
	@BeforeEach
	public void setup() {
		cartItem1 = new CartItem(item1);
		cartItem2 = new CartItem(item2);
	}
	
	/* TESTS */
	
	@Test
	public void shouldStartEmpty() {
		assertEquals(true, cart.isEmpty());
	}
	
	@Test
	public void shouldBeAbleToAddACartItem() {
		cart.addCartItem(cartItem1);
		
		assertEquals(cartItem1, cart.getCartItem(cartItem1));
	}
	
	@Test
	public void shouldNotBeEmptyAfterAddingCartItem() {
		cart.addCartItem(cartItem1);
		
		assertEquals(false, cart.isEmpty());
	}
	
	@Test
	public void shouldBeAbleToAddMoreThanOneCartItem() {	
		cart.addCartItem(cartItem1);
		cart.addCartItem(cartItem2);
		
		assertEquals(2, cart.getCartItems().size());
	}
	
	@Test
	public void shouldBeAbleToDeleteLastAddedItem() {
		cart.addCartItem(cartItem1);
		cart.addCartItem(cartItem2);
		
		cart.deleteLastCartItem();
		
		assertEquals(1, cart.getCartItems().size());
		assertEquals(false, cart.getCartItems().contains(item2));
	}
	
	@Test 
	public void shouldReturnPreTaxTotalAsZeroWhenEmpty() {
		assertEquals("0.00", cart.getPreTaxTotal());
	}
	
	@Test
	public void shouldReturnPositivePreTaxTotalAfterCalculatingAddedUnitTypeCartItems() {
		cart.addCartItem(cartItem1); // Unit price of $1.99
		cart.addCartItem(cartItem2); // Unit price of $1.99
		
		cart.calculatePreTaxTotal();
		
		assertEquals("3.98", cart.getPreTaxTotal());
	}
	
	@Test
	public void shouldReturnCorrectTotalAfterDeletingCartItem() {
		cart.addCartItem(cartItem1); // Unit price of $1.99
		cart.addCartItem(cartItem2); // Unit price of $1.99
		cart.calculatePreTaxTotal();
		
		cart.deleteLastCartItem();
		cart.calculatePreTaxTotal();
		
		assertEquals("1.99", cart.getPreTaxTotal());
	}

	@Test
	public void shouldDeleteRescannedCartItemAndDisplayCorrectPreTaxTotal() {
		cart.addCartItem(cartItem1); // Soup w/ unit price of $1.99
		cart.addCartItem(cartItem1); // Soup w/ unit price of $1.99
		cart.addCartItem(cartItem2); // Bread w/ unit price of $1.99
		cart.calculatePreTaxTotal();
		
		cart.deleteCartItem(cartItem1);
		cart.calculatePreTaxTotal();
		
		assertAll("cart",
			() -> assertEquals(2, cart.getCartItems().size()),
			() -> assertEquals("3.98", cart.getPreTaxTotal())
		);
	}
	
	@Test
	public void shouldReturnCorrectPreTaxTotalIfACartItemHasMarkdown() {
		Item item3 = new Item("Chips", "1.99");
		Markdown markdown = new Markdown("$1.00 OFF", "1.00");
		item3.addMarkdown(markdown);
		CartItem cartItem3 = new CartItem(item3);
		
		cart.addCartItem(cartItem1); // Unit price of $1.99
		cart.addCartItem(cartItem3); // Sale Price of 0.99
		cart.calculatePreTaxTotal();
		
		assertEquals("2.98", cart.getPreTaxTotal());
	}
	
	@Test
	public void shouldReturnCorrectPreTaxTotalIfACartItemIsChargeByWeight() {
		Item item4 = new Item("Steak", "3.00", true);
		
		/* TODO Application should prompt for weight if(isChargeByWeight)*/
		
		CartItem cartItem4 = new CartItem(item4, 5.00);
		
		cart.addCartItem(cartItem4);
		cart.calculatePreTaxTotal();
		
		assertEquals("15.00", cart.getPreTaxTotal());
	}
	
	
} // End TestCart()
