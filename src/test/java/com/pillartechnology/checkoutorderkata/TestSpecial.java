package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestSpecial {

	@Test
	public void shouldBeAbleToSetNumberOfItemsToBuy() {
		BuyNGetMatXPercentOff special1 = new BuyNGetMatXPercentOff(1,1,100.00);
		
		assertEquals(1, special1.getBuyQtyRequirement());
	}
	
	@Test
	public void shouldBeAbleToSetNumberOfItemReceivedAtDiscount() {
		BuyNGetMatXPercentOff special1 = new BuyNGetMatXPercentOff(1,1,100.00);
		
		assertEquals(1, special1.getReceiveQtyItems());
	}
} // End TestSpecial();
