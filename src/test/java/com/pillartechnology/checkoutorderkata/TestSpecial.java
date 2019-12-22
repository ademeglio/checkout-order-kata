package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.pillartechnology.checkoutorderkata.discounts.BuyNForX;
import com.pillartechnology.checkoutorderkata.discounts.BuyNGetMatXPercentOff;
import com.pillartechnology.checkoutorderkata.entity.Cart;
import com.pillartechnology.checkoutorderkata.entity.CartItem;
import com.pillartechnology.checkoutorderkata.entity.Item;

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
		item.addSpecial(special1);
		int itemBuyCount = 2;
		
		BigDecimal discountAmount = special1.calculateDiscountAmount(item, itemBuyCount);
		
		assertEquals("2.00", discountAmount.toString());
	}
	
	@Test
	public void shouldReturnHalfPriceOfOneItemWithBuy1Get1At50PercentOff() {
		BuyNGetMatXPercentOff special1 = new BuyNGetMatXPercentOff(1,1,50.00);
		
		Item item = new Item("Candy", "2.00");
		item.addSpecial(special1);
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
		item.addSpecial(special1);
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
		item.addSpecial(special2);
		int itemBuyCount = 6; // Without Limit discount is $2.00; with limit 3 discount is $1.00
		
		BigDecimal discountAmount = special2.calculateDiscountAmount(item, itemBuyCount);
		
		assertEquals("1.00", discountAmount.toString());
	}
	
	@Test
	public void shouldReturnDiscountedAmountBuyNGetMOfEqualOrLesserValueForXPercentOff() {
		Cart cart = new Cart();
		// "Buy N, get M of equal or lesser value for %X off" on weighted items.
		// "Buy 2 pounds of ground beef, get 1 pound half off."
		BuyNChargeByWeightGetMatXPercentOff special3 = new BuyNChargeByWeightGetMatXPercentOff(2,1,50);
		
		Item item4 = new Item("Steak", "3.00", true);
		item4.addSpecial(special3);
		
		// TODO: New special should use a CartItem and maybe cartItems so that we loop
		// through the array and for each special of this type, add them.
		// Maybe needs a map?
		
		int itemBuyCount = 2;
		
		BigDecimal discountAmount = special3.calculateDiscountAmount(cartItems, itemBuyCount);
		
//		CartItem cartItem3 = new CartItem(item4, 2.00);
//		CartItem cartItem4 = new CartItem(item4, 1.00);
//		
//		cart.addCartItem(cartItem3); // Steak, 2#, $6.00
//		cart.addCartItem(cartItem4); // Steak, 1#, $3.00; $1.50 after special
//		cart.calculatePreTaxTotal();
		
		assertEquals("7.50", discountAmount.toString());
	
		
		
	}
	
} // End TestSpecial();
