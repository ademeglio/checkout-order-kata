package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCart {
	
	Cart cart = new Cart();
	Item item1 = new Item("Soup", "1.99");
	Item item2 = new Item("Bread", "1.99");
	
	CartItem cartItem1;
	CartItem cartItem2;
	
	@BeforeEach
	public void setup() {
		cartItem1 = new CartItem(item1);
		cartItem2 = new CartItem(item2);
	}
	
	/* TESTS */
	
	@Test
	public void shouldStartEmpty() {
		assertEquals(true, cart.isEmpty());
	}
	
	@Test
	public void shouldBeAbleToAddACartItem() {
		cart.addCartItem(cartItem1);
		
		assertEquals(cartItem1, cart.getCartItem(cartItem1));
	}
	
	@Test
	public void shouldNotBeEmptyAfterAddingCartItem() {
		cart.addCartItem(cartItem1);
		
		assertEquals(false, cart.isEmpty());
	}
	
	@Test
	public void shouldBeAbleToAddMoreThanOneCartItem() {	
		cart.addCartItem(cartItem1);
		cart.addCartItem(cartItem2);
		
		assertEquals(2, cart.getCartItems().size());
	}
	
	@Test
	public void shouldBeAbleToDeleteLastAddedItem() {
		cart.addCartItem(cartItem1);
		cart.addCartItem(cartItem2);
		
		cart.deleteLastCartItem();
		
		assertEquals(1, cart.getCartItems().size());
		assertEquals(false, cart.getCartItems().contains(cartItem2));
	}
	
	@Test 
	public void shouldReturnPreTaxTotalAsZeroWhenEmpty() {
		assertEquals("0.00", cart.getPreTaxTotal());
	}
	
	@Test
	public void shouldReturnPositivePreTaxTotalAfterCalculatingAddedUnitTypeCartItems() {
		cart.addCartItem(cartItem1); // Unit price of $1.99
		cart.addCartItem(cartItem2); // Unit price of $1.99
		
		cart.calculatePreTaxTotal();
		
		assertEquals("3.98", cart.getPreTaxTotal());
	}
	
	@Test
	public void shouldReturnCorrectTotalAfterDeletingCartItem() {
		cart.addCartItem(cartItem1); // Unit price of $1.99
		cart.addCartItem(cartItem2); // Unit price of $1.99
		cart.calculatePreTaxTotal();
		
		cart.deleteLastCartItem();
		cart.calculatePreTaxTotal();
		
		assertEquals("1.99", cart.getPreTaxTotal());
	}

	@Test
	public void shouldDeleteRescannedCartItemAndDisplayCorrectPreTaxTotal() {
		cart.addCartItem(cartItem1); // Soup w/ unit price of $1.99
		cart.addCartItem(cartItem1); // Soup w/ unit price of $1.99
		cart.addCartItem(cartItem2); // Bread w/ unit price of $1.99
		cart.calculatePreTaxTotal();
		
		cart.deleteCartItem(cartItem1);
		cart.calculatePreTaxTotal();
		
		assertAll("cart",
			() -> assertEquals(2, cart.getCartItems().size()),
			() -> assertEquals("3.98", cart.getPreTaxTotal())
		);
	}
	
	@Test
	public void shouldReturnCorrectPreTaxTotalIfACartItemHasMarkdown() {
		Item item3 = new Item("Chips", "1.99");
		Markdown markdown = new Markdown("$1.00 OFF", "1.00");
		item3.addMarkdown(markdown);
		CartItem cartItem3 = new CartItem(item3);
		
		cart.addCartItem(cartItem1); // Unit price of $1.99
		cart.addCartItem(cartItem3); // Sale Price of 0.99
		cart.calculatePreTaxTotal();
		
		assertEquals("2.98", cart.getPreTaxTotal());
	}
	
	@Test
	public void shouldReturnCorrectPreTaxTotalIfACartItemIsChargeByWeight() {
		Item item4 = new Item("Steak", "3.00", true);
		
		/* TODO Application should prompt for weight if(isChargeByWeight)*/
		
		CartItem cartItem4 = new CartItem(item4, 5.00);
		
		cart.addCartItem(cartItem4);
		cart.calculatePreTaxTotal();
		
		assertEquals("15.00", cart.getPreTaxTotal());
	}
	
	@Test
	public void shouldReturnCorrectPreTaxTotalIfACartItemIsChargeByWeightAndHasMarkdown() {
		Markdown markdown = new Markdown("$1.00 OFF PER LB", "1.00");
		Item item4 = new Item("Steak", "3.00", true);
		item4.addMarkdown(markdown);
		
		CartItem cartItem4 = new CartItem(item4, 5.00);
		
		cart.addCartItem(cartItem4);
		cart.calculatePreTaxTotal();
		
		assertEquals("10.00", cart.getPreTaxTotal());
	}
	
	@Test
	public void shouldKeepCountOfEachItemTypeInTheCartThatHasSpecial() {
		BuyNGetMatXPercentOff special1 = new BuyNGetMatXPercentOff(1,1,0.50);
		
		Item item3 = new Item("Peanut Butter", "1.00");
		Item item4 = new Item("Jelly", "1.00");
		
		item3.addSpecial(special1);
		
		CartItem cartItem1a = new CartItem(item3);
		CartItem cartItem2a = new CartItem(item4);
		CartItem cartItem3a = new CartItem(item3);
		CartItem cartItem4a = new CartItem(item4);
		CartItem cartItem5a = new CartItem(item3);
		
		cart.addCartItem(cartItem1a); // item3
		cart.addCartItem(cartItem2a);
		cart.addCartItem(cartItem3a); // item3
		cart.addCartItem(cartItem4a);
		cart.addCartItem(cartItem5a); // item3
		
		assertEquals(3, cart.countItemOnSpecial(item3));
	}
	
	
	@Test public void shouldNotKeepCountOfEachItemTypeInTheCartWithoutSpecial() {
		assertEquals(0, cart.countItemOnSpecial(item1));
	}
	
	@Test
	public void shouldReturnCorrectPreTaxTotalForSpecialBuyNGetMAtXPercentOff() {
		/// Buy 1 get 1 at 50% off
		BuyNGetMatXPercentOff special1 = new BuyNGetMatXPercentOff(1,1,50);
		
		Item item5 = new Item("Taco Shells", "4.00");
		item5.addSpecial(special1);
		
		CartItem cartItem5 = new CartItem(item5);
		CartItem cartItem6 = new CartItem(item5); // Second Item Should Be discounted
		
		cart.addCartItem(cartItem5);
		cart.addCartItem(cartItem6);
		
		cart.calculatePreTaxTotal();
		
		assertEquals("6.00", cart.getPreTaxTotal());
	}
	
	@Test
	public void shouldReturnCorrectPreTaxTotalForSpecialBuy2Get1AtHalfOff() {
		// Buy 2 get 1 at 50% discount
		BuyNGetMatXPercentOff special1 = new BuyNGetMatXPercentOff(2,1,50);
		
		Item item5 = new Item("Taco Shells", "4.00");
		item5.addSpecial(special1);
		
		// Create Cart Items
		CartItem cartItem3  = new CartItem(item1);
		CartItem cartItem4  = new CartItem(item2);
		CartItem cartItem5  = new CartItem(item5);
		CartItem cartItem6  = new CartItem(item5);
		CartItem cartItem7  = new CartItem(item5);
		CartItem cartItem8  = new CartItem(item5);
		CartItem cartItem9  = new CartItem(item5);
		CartItem cartItem10 = new CartItem(item5);
		
		cart.addCartItem(cartItem1); //Soup, 1.99
		cart.addCartItem(cartItem2); //Bread, 1.99
		cart.addCartItem(cartItem3); //Soup, 1.99
		cart.addCartItem(cartItem4); //Bread, 1.99
		cart.addCartItem(cartItem5); //Taco Shells, 4.00 
		cart.addCartItem(cartItem6); //Taco Shells, 4.00
		cart.addCartItem(cartItem7); //Taco Shells, 2.00 *Discounted
		cart.addCartItem(cartItem8); //Taco Shells, 4.00
		cart.addCartItem(cartItem9); //Taco Shells, 4.00
		cart.addCartItem(cartItem10);//Taco Shells, 2.00 *Discounted
		
		cart.calculatePreTaxTotal();
		
		assertEquals("27.96", cart.getPreTaxTotal());
	}
	
	@Test
	public void shouldSupportSpecialNforXDollars() {
		// Support a special in the form of "N for $X." For example, "3 for $5.00"
		BuyNForX special2 = new BuyNForX(3,"5.00");
		
		item1.addSpecial(special2);
		
		CartItem cartItem3  = new CartItem(item1);
		CartItem cartItem4  = new CartItem(item1);
		
		cart.addCartItem(cartItem1); // Soup, 1.99
		cart.addCartItem(cartItem2); // Bread, 1.99
		cart.addCartItem(cartItem3); // Soup, 1.99
		cart.addCartItem(cartItem4); // Soup, 1.99
		
		cart.calculatePreTaxTotal();
		
		assertEquals("6.99", cart.getPreTaxTotal());
	}
	
	@Test
	public void shouldApplyLimitOnSpecialWhenApplied() {
		//"buy 2 get 1 free, limit 6" would prevent getting a third free item.
		BuyNGetMatXPercentOff special1 = new BuyNGetMatXPercentOff(2,1,100);
		
		item1.addSpecial(special1);
		special1.setLimit(6);
		
		CartItem cartItem3 = new CartItem(item1);
		CartItem cartItem4 = new CartItem(item1);
		CartItem cartItem5 = new CartItem(item1);
		CartItem cartItem6 = new CartItem(item1);
		CartItem cartItem7 = new CartItem(item1);
		CartItem cartItem8 = new CartItem(item1);
		CartItem cartItem9 = new CartItem(item1);
		
		cart.addCartItem(cartItem1); // Soup, 1.99 (1)
		cart.addCartItem(cartItem2); // Bread, 1.99
		cart.addCartItem(cartItem3); // Soup, 1.99 (2)
		cart.addCartItem(cartItem4); // Soup, 1.99 (3) * Discounted 100%
		cart.addCartItem(cartItem5); // Soup, 1.99 (4)
		cart.addCartItem(cartItem5); // Soup, 1.99 (5)
		cart.addCartItem(cartItem6); // Soup, 1.99 (6) * Discounted 100%
		cart.addCartItem(cartItem7); // Soup, 1.99 (7) 
		cart.addCartItem(cartItem8); // Soup, 1.99 (8)
		cart.addCartItem(cartItem9); // Soup, 1.99 (9) 
		
		cart.calculatePreTaxTotal();
		
		assertEquals("15.92", cart.getPreTaxTotal());	
	}
	
	@Test
	public void shouldApplyLimitOnSpecialBuyNForX() {
		// "buy 2 for $3.00, limit 4
		BuyNForX special2 = new BuyNForX(2, "3.00");
		
		item1.addSpecial(special2);
		special2.setLimit(4);
		
		CartItem cartItem3 = new CartItem(item1);
		CartItem cartItem4 = new CartItem(item1);
		CartItem cartItem5 = new CartItem(item1);
		CartItem cartItem6 = new CartItem(item1);
		CartItem cartItem7 = new CartItem(item1);
		
		cart.addCartItem(cartItem1); // Soup, 1.99 (1) *
		cart.addCartItem(cartItem2); // Bread, 1.99
		cart.addCartItem(cartItem3); // Soup, 1.99 (2) *
		cart.addCartItem(cartItem4); // Soup, 1.99 (3) *
		cart.addCartItem(cartItem5); // Soup, 1.99 (4) *
		cart.addCartItem(cartItem6); // Soup, 1.99 (5)
		cart.addCartItem(cartItem7); // Soup, 1.99 (6)
		
		cart.calculatePreTaxTotal();
		// should be 1.96 off
		assertEquals("11.97", cart.getPreTaxTotal());
	}
	
	
} // End TestCart()
