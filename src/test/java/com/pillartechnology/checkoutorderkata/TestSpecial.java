package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

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
	
	@Test
	public void shouldBeAbleToSetDiscountAmount() {
		BuyNGetMatXPercentOff special1 = new BuyNGetMatXPercentOff(1,1,100.00);
		
		assertEquals(100.00, special1.getDiscountPercentage());
	}
	
	@Test
	public void shouldReturnFullPriceOfOneItemWithBuy1Get1At100PercentOff() {
		BuyNGetMatXPercentOff special1 = new BuyNGetMatXPercentOff(1,1,100.00);
		
		Item item = new Item("Candy", "2.00");
		int itemBuyCount = 2;
		
		BigDecimal discountAmount = special1.calculateDiscountAmount(item, itemBuyCount);
		
		assertEquals("2.00", discountAmount.toString());
	}
	
	@Test
	public void shouldReturnHalfPriceOfOneItemWithBuy1Get1At50PercentOff() {
		BuyNGetMatXPercentOff special1 = new BuyNGetMatXPercentOff(1,1,50.00);
		
		Item item = new Item("Candy", "2.00");
		int itemBuyCount = 2;
		
		BigDecimal discountAmount = special1.calculateDiscountAmount(item, itemBuyCount);
		
		assertEquals("1.00", discountAmount.toString());
	}
	
	@Test
	public void shouldBeAbleToSetLimitOnSpecials() {
		BuyNGetMatXPercentOff special1 = new BuyNGetMatXPercentOff(1,1,100.00);
		
		// Set limit to 2
		special1.setLimit(2);
		
		Item item = new Item("Candy", "2.00");
		int itemBuyCount = 4; // Without Limit discount is $4.00; with limit 2 discount is $2.00
		
		BigDecimal discountAmount = special1.calculateDiscountAmount(item, itemBuyCount);
		
		assertEquals("2.00", discountAmount.toString());
	}
	
	@Test
	public void shouldBeAbleToSetLimitOnSpecialBuyNForX() {
		BuyNForX special2 = new BuyNForX(3, "5.00");
		
		// Set limit to 3
		special2.setLimit(3);
		
		Item item = new Item("Candy", "2.00");
		int itemBuyCount = 6; // Without Limit discount is $2.00; with limit 3 discount is $1.00
		
		BigDecimal discountAmount = special2.calculateDiscountAmount(item, itemBuyCount);
		
		assertEquals("1.00", discountAmount.toString());
	}
	
} // End TestSpecial();
