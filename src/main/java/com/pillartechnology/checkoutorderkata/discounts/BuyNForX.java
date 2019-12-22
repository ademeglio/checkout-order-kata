package com.pillartechnology.checkoutorderkata.discounts;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import com.pillartechnology.checkoutorderkata.entity.Item;

/**
 * BuyNForX extends Special() allowing to configure
 * Buy N items for $x dollars.
 *
 */
public class BuyNForX extends Special{

	private BigDecimal itemsDiscountedPrice;

	// Constructor
	public BuyNForX(int buyQtyRequirement, String itemsPrice) {
		super(buyQtyRequirement);
		this.itemsDiscountedPrice = new BigDecimal(itemsPrice);
	}

	/**
	 * Returns a BigDecimal representing the total calculated
	 * discount for a given Item in the cart that is on special.
	 * @param item an item object in the cart that has a special.
	 * @param itemBuyCount is the number of items of type item in the cart on special.
	 * @return The total calculated decimal amount as a Big Decimal.
	 */
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
