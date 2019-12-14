package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestInventory {

	/* TESTS */
	
	@Test
	public void shouldStartEmpty() {
		Inventory inventory = new Inventory();
		
		assertEquals(true, inventory.isEmpty());
	}
} // End TestInventory()
