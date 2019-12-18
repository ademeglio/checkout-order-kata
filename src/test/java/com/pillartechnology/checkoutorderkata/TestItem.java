package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestItem {

	Item item1 = new Item("Soup");
	Item item2 = new Item("Bananas");
	
	@BeforeEach
	public void setup() {
		item1.setDefaultPrice("1.99");
	}
	
	@Test
	public void shouldBeAbleToReturnWhatTheItemIs() {
		assertEquals("Soup", item1.getName());
	}
	
	@Test
	public void shouldIdentifyIsChargedByWeightIsFalseIfNotSet() {
		assertEquals(false, item1.isChargeByWeight());
	}
	
	@Test
	public void shouldIdentifyIsChargedByWeightTrueIfSetToTrue() {
		item2.setChargeByWeight(true);
		
		assertEquals(true, item2.isChargeByWeight());
	}
	
	@Test
	public void shouldAcceptPriceToChargeAsString() {
		assertEquals("1.99", item1.getDefaultPrice().toString());
	}
	
	@Test
	public void shouldIdentifyHasMarkdownFalseIfNotSet() {
		assertEquals(false, item1.hasMarkdown());
	}
	
	@Test
	public void shouldUpdateAsHavingMarkdownIfAdded() {
		Markdown markdownBy1Dollar = new Markdown("$1.00 OFF","1.00");
		
		item1.addMarkdown(markdownBy1Dollar);
		
		assertEquals(true, item1.hasMarkdown());
	}
	
	@Test
	public void canIdentifyMarkdownAfterMarkdownIsAdded() {
		Markdown markdownBy1Dollar = new Markdown("$1.00 OFF", "1.00");
		
		item1.addMarkdown(markdownBy1Dollar);
		
		assertEquals(markdownBy1Dollar, item1.getMarkdown());
	}
	
	@Test
	public void shouldIdentifyHasSpecialFalseIfNotSet() {
		assertEquals(false, item1.hasSpecial());
	}
	
	@Test
	public void shouldUpdateAsHavingSpecialIfAdded() {
		BuyNGetMatXPercentOff special1 = new BuyNGetMatXPercentOff(
				1,1,0.50);
		
		item1.addSpecial(special1);
		
		assertEquals(true, item1.hasSpecial());
	}
	
	@Test
	public void canIdentifySpecialAfterSpecialIsAdded() {
		BuyNGetMatXPercentOff special1 = new BuyNGetMatXPercentOff(
				1,1,0.50);
		
		item1.addSpecial(special1);
		
		assertEquals(special1, item1.getSpecial());
	}
	
	@Test
	public void canIdentifySpecialDescriptionAfterSpecialIsAdded() {
		BuyNGetMatXPercentOff special1 = new BuyNGetMatXPercentOff(
				1,1,0.50);
		
		item1.addSpecial(special1);
		
		assertEquals("Buy 1 item, get 1 at 50% off", item1.getSpecial().toString());
	}
	
	@Test
	public void canIdentifySpecialDescriptionAfterSpecialIsAddedThatIdentifiesXAsFree() {
		BuyNGetMatXPercentOff special1 = new BuyNGetMatXPercentOff(
				1,1,1.00);
		
		item1.addSpecial(special1);
		
		assertEquals("Buy 1 get 1 free", item1.getSpecial().toString());
	}
	
	@Test
	public void canInstantiateItemWithNameAndPrice() {
		Item item3 = new Item("Sauce", "3.99");
				
		assertAll("item3",
			() -> assertEquals("Sauce", item3.getName()),
			() -> assertEquals("3.99", item3.getDefaultPrice().toString())
		);
	}
	
} // End TestItem()
