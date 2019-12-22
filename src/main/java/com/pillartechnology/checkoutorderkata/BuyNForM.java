package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BuyNForM extends Special{

	private BigDecimal itemsDiscountedPrice;

	public BuyNForM(int buyQtyRequirement, String itemsPrice) {
		super(buyQtyRequirement);
		this.itemsDiscountedPrice = new BigDecimal(itemsPrice);
	}

	@Override
	public BigDecimal calculateDiscountAmount(Item item, int itemBuyCount) {
		BigDecimal discountAmount = new BigDecimal("0.00");
		int buyQtyRequirement = this.getBuyQtyRequirement();
		int itemsRemaining = itemBuyCount;
		System.out.println("Item buy count: " + itemBuyCount);
		while (itemsRemaining >= buyQtyRequirement) {
			BigDecimal amountToAddToDiscount = new BigDecimal("0.0");
			
			// Special Applies; adjust items remaining first
			itemsRemaining = itemsRemaining - buyQtyRequirement;
			System.out.println("Items remaining is now: " + itemsRemaining);
			/* 
			 * Calculate discounted unit price.
			 * Divide total itemsPriceAmount by buyQtyRequirement
			 * Subtract that from itemSalePrice to get the difference
			 * add those differences together for the total discountAmount
			 */
			MathContext mc = new MathContext(4, RoundingMode.HALF_UP);
			BigDecimal discountUnitPriceBy = this.itemsDiscountedPrice
					.divide(new BigDecimal(buyQtyRequirement), mc);
			System.out.println("Should be 1.67: " + discountUnitPriceBy.toString());
			
			BigDecimal discountedUnitSalePrice = item.getSalePrice()
					.subtract(discountUnitPriceBy);
			System.out.println("Should be .323: " + discountedUnitSalePrice);
			
			// Calculate the amount to add to the total discount amount
			amountToAddToDiscount = discountedUnitSalePrice
					.multiply(new BigDecimal(buyQtyRequirement), mc);
			System.out.println("Should be .97: " + amountToAddToDiscount.toString());
			/* Add the calculated amount and adjust the items remaining by items
			 * purchased at discount. 
			 */
			discountAmount = discountAmount.add(amountToAddToDiscount);
			
		} // End while
		return discountAmount.setScale(2, RoundingMode.HALF_UP);
	}
	
	@Override
	public String toString() {
		int buyQty = this.getBuyQtyRequirement();
		
		return buyQty + " for $" + itemsDiscountedPrice;
	}

	
} // End BuyNForX()
