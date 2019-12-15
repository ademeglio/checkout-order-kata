package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCart {
	
	Cart cart = new Cart();
	Item item1 = new Item("Soup");
	Item item2 = new Item("Bread");
	
	CartItem cartItem1;
	CartItem cartItem2;
	
	@BeforeEach
	public void setup() {
		item1.setPrice("1.99");
		item2.setPrice("1.99");
		
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
		CartItem cartItem1 = new CartItem(item1);
		cart.addCartItem(cartItem1);
		
		assertEquals(cartItem1, cart.getCartItem(cartItem1));
	}
	
	@Test
	public void shouldNotBeEmptyAfterAddingCartItem() {
		CartItem cartItem1 = new CartItem(item1);
		cart.addCartItem(cartItem1);
		
		assertEquals(false, cart.isEmpty());
	}
	
	@Test
	public void shouldBeAbleToAddMoreThanOneCartItem() {
		CartItem cartItem1 = new CartItem(item1);
		CartItem cartItem2 = new CartItem(item2);
		
		cart.addCartItem(cartItem1);
		cart.addCartItem(cartItem2);
		
		assertEquals(2, cart.getCartItems().size());
	}
	
	@Test
	public void shouldBeAbleToDeleteLastAddedItem() {
		CartItem cartItem1 = new CartItem(item1);
		CartItem cartItem2 = new CartItem(item2);
		
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
	
//	@Test
//	public void shouldSubtractFromPreTaxTotalAfterDeletingLastUnitItem() {
//		cart.addItem(item1); // Unit price of $1.99
//		cart.addItem(item2); // Unit price of $1.99
//		
//		cart.deleteLastItem();
//		
//		assertEquals("1.99", cart.getPreTaxTotal());
//	}
	
	
} // End TestCart()
