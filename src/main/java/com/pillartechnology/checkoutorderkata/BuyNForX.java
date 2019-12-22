package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BuyNForX extends Special{

	private BigDecimal itemsDiscountedPrice;

	public BuyNForX(int buyQtyRequirement, String itemsPrice) {
		super(buyQtyRequirement);
		this.itemsDiscountedPrice = new BigDecimal(itemsPrice);
	}

	@Override
	public BigDecimal calculateDiscountAmount(Item item, int itemBuyCount) {
		BigDecimal discountAmount = new BigDecimal("0.00");
		int buyQtyRequirement = this.getBuyQtyRequirement();
		int itemsRemaining = itemBuyCount;

		if (this.getLimit() > 0) {
			itemsRemaining = this.getLimit();
		}

		while (itemsRemaining >= buyQtyRequirement) {
			BigDecimal amountToAddToDiscount = new BigDecimal("0.0");
			
			// Special Applies; adjust items remaining first
			itemsRemaining = itemsRemaining - buyQtyRequirement;

			/* 
			 * Calculate discounted unit price.
			 * Divide total itemsPriceAmount by buyQtyRequirement
			 * Subtract that from itemSalePrice to get the difference
			 * add those differences together for the total discountAmount
			 */
			MathContext mc = new MathContext(4, RoundingMode.HALF_UP);
			BigDecimal discountUnitPriceBy = this.itemsDiscountedPrice
					.divide(new BigDecimal(buyQtyRequirement), mc);
			
			BigDecimal discountedUnitSalePrice = item.getSalePrice()
					.subtract(discountUnitPriceBy);
			
			// Calculate the amount to add to the total discount amount
			amountToAddToDiscount = discountedUnitSalePrice
					.multiply(new BigDecimal(buyQtyRequirement), mc);

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
		String addLimit = this.getLimit() > 0
				? ", limit " + this.getLimit()
				: "";
		
		return buyQty + " for $" + itemsDiscountedPrice + addLimit;
	}

	
} // End BuyNForX()
