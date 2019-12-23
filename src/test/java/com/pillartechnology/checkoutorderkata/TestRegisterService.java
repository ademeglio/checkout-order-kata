package com.pillartechnology.checkoutorderkata;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pillartechnology.checkoutorderkata.service.RegisterService;

@SpringBootTest
public class TestRegisterService {

	private RegisterService registerService;
	
	@Autowired
	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}
	
	@Test
	public void shouldInitiateAnCartAndReturnPreTaxTotalOfZero() {
		registerService.initiateCart();
		
		assertEquals("0.00", registerService.getPreTaxCartTotal(0).toString());
	}
	
	
} // End TestRegisterService()
