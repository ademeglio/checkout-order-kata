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
	
	@BeforeEach
	public void setup() {
		item1.setPrice("1.99");
		item2.setPrice("1.99");
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
	public void shouldNotBeEmptyAfterAddingItem() {
		CartItem cartItem1 = new CartItem(item1);
		cart.addCartItem(cartItem1);
		
		assertEquals(false, cart.isEmpty());
	}
	
	@Test
	public void shouldBeAbleToAddMoreThanOneItem() {
		
		cart.addItem(item1);
		cart.addItem(item2);
		
		assertEquals(2, cart.getItems().size());
	}
	
	@Test
	public void shouldBeAbleToDeleteLastAddedItem() {
		
		cart.addItem(item1);
		cart.addItem(item2);
		
		cart.deleteLastItem();
		
		assertEquals(1, cart.getItems().size());
		assertEquals(false, cart.getItems().contains(item2));
	}
	
	@Test 
	public void shouldReturnPreTaxTotalAsZeroWhenEmpty() {
		assertEquals("0.00", cart.getPreTaxTotal());
	}
	
	@Test
	public void shouldReturnPositivePreTaxTotalAfterAddingUnitItems() {
		cart.addItem(item1); // Unit price of $1.99
		cart.addItem(item2); // Unit price of $1.99
		
		assertEquals("3.98", cart.getPreTaxTotal());
	}
	
	@Test
	public void shouldSubtractFromPreTaxTotalAfterDeletingLastUnitItem() {
		cart.addItem(item1); // Unit price of $1.99
		cart.addItem(item2); // Unit price of $1.99
		
		cart.deleteLastItem();
		
		assertEquals("1.99", cart.getPreTaxTotal());
	}
	
	@Test
	public void shouldMakeNegativeAdjustmentToPreTaxTotalByOne() {
		cart.addItem(item1); // Unit price of $1.99
		cart.addItem(item2); // Unit price of $1.99
		
		cart.adjustPreTaxTotal("-1.00");
		
		assertEquals("2.98", cart.getPreTaxTotal());
	}
	
	@Test
	public void shouldMakePositiveAdjustmentToPreTaxTotalByOne() {
		cart.addItem(item1); // Unit price of $1.99
		cart.addItem(item2); // Unit price of $1.99
		
		cart.adjustPreTaxTotal("1.00");
		
		assertEquals("4.98", cart.getPreTaxTotal());
	}
	
} // End TestCart()
