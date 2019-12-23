package com.pillartechnology.checkoutorderkata;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pillartechnology.checkoutorderkata.service.RegisterAdminService;
import com.pillartechnology.checkoutorderkata.service.RegisterService;

@SpringBootTest
public class TestRegisterService {

	private RegisterService registerService;
	private RegisterAdminService registerAdminService;
	
	@Autowired
	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}
	
	@Autowired
	public void setRegisterAdminService(RegisterAdminService registerAdminService) {
		this.registerAdminService = registerAdminService;
	}
	
	@BeforeEach
	public void setup() {
		// Create Specials, Markdown, and Items for testing 
		registerService.initiateCart();
		registerAdminService.createSpecialBuyNForX("2 For $2.00", 2, "2.00");
		
		registerAdminService.createMarkdown("$0.25 OFF", "0.25");
		
		registerAdminService.createItem("beans","2.00",false);
		registerAdminService.createItem("chile", "1.50", false);
		registerAdminService.createItem("Apple", "0.50", true);
	}
	
	@Test
	public void shouldInitiateAnCartAndReturnPreTaxTotalOfZero() {
		assertEquals("0.00", registerService.getPreTaxCartTotal(0).toString());
	}
	
	@Test
	public void shouldClearCartOfAllItems() {
		registerService.scanItem(0, "beans", 0);// $2.00
		registerService.scanItem(0, "Chile", 0);// $1.50
		
		registerService.clearCart(0);
		
		assertEquals("0.00", registerService.getPreTaxCartTotal(0).toString());
	}
	
	@Test
	public void shouldReturnUpdatedTotalAfterAddingItemsToCart() {
		registerService.clearCart(0);
		
		registerService.scanItem(0, "beans", 0);// $2.00
		registerService.scanItem(0, "Chile", 0);// $1.50
		registerService.scanItem(0, "apple", 5.0); // $2.50
		
			
		assertEquals("6.00", registerService.getPreTaxCartTotal(0).toString());
	}
	
	@Test
	public void shouldRemoveLastItemAndUpdateCartTotal() {
		registerService.clearCart(0);
		
		registerService.scanItem(0, "beans", 0);// $2.00
		registerService.scanItem(0, "Chile", 0);// $1.50
		registerService.scanItem(0, "apple", 5.0); // $2.50
		
		registerService.removeLastItemScanned(0);
		
		assertEquals("3.50", registerService.getPreTaxCartTotal(0).toString());
	}
	
	@Test
	public void shouldRemoveScannedItemAndUpdateCartTotal() {
		registerService.clearCart(0);
		
		registerService.scanItem(0, "beans", 0);// $2.00
		registerService.scanItem(0, "Chile", 0);// $1.50
		registerService.scanItem(0, "apple", 5.0); // $2.50
		
		registerService.removeScannedItem(0, "chile");
		
		assertEquals("4.50", registerService.getPreTaxCartTotal(0).toString());
	}
} // End TestRegisterService()
