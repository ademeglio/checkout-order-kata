package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCart {

	@Test
	public void shouldStartEmpty() {
		Cart cart = new Cart();
		assertEquals(cart.isEmpty(), true);
	}
	
} // End TestCart()
