package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pillartechnology.checkoutorderkata.service.RegisterAdminService;

@SpringBootTest
public class TestRegisterAdminServiceInterface {

	private RegisterAdminService registerAdminService;
	
	@Autowired
	public void setRegisterAdminService(RegisterAdminService registerAdminService) {
		this.registerAdminService = registerAdminService;
	}
	
	@Test
	public void shouldCreateItem() {
		registerAdminService.createItem("soup","1.99",false);
		
		assertEquals("soup", registerAdminService.getItem("Soup").getName());
	}
	
	
} //EndTestAdminRegisterInterface
