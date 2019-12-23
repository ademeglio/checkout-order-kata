package com.pillartechnology.checkoutorderkata;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.pillartechnology.checkoutorderkata.interfaces.AdminRegister;

@SpringBootTest
public class TestAdminRegisterInterface {

	@Test
	public void shouldCreateInventory() {
		AdminRegisterImpl.createInventory("Inventory");
		AdminRegister.createItem("Soup","1.99",false);
		
		assertEquals("Inventory", AdminRegister.getInventory().getName());
		
		
	}
	
	
} //EndTestAdminRegisterInterface
