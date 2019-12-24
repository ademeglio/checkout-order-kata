package com.pillartechnology.checkoutorderkata.discounts;

import java.math.BigDecimal;
import java.math.RoundingMode;
import com.pillartechnology.checkoutorderkata.entity.Item;

/**
 * BuyNGetMatXPercentOff extends {@link Special), allowing to configure
 * Buy N items and get M items at %x off.
 * 
 * @version 0.1.0
 * @see Special
 */
public class BuyNGetMatXPercentOff extends Special{

	// Constructor
	/**
	 * Class Constructor specifying number of items required to
	 * purchase, number of items to receive discount, and discount percentage
	 * off.
	 * 
	 * @param buyQtyRequirement	minimum quantity of items needed in cart
	 * 							before Special applies.
	 * @param receiveQtyItems	number of items to receive at discounted rate.
	 * @param percentOff		discount percentage entered as a whole number.
	 * 							<strong>Example:</strong> 50% is entered as
	 * 							50 or 50.0.
	 */
	public BuyNGetMatXPercentOff(int buyQtyRequirement,
			int receiveQtyItems, double percentOff) {
		super(buyQtyRequirement);
		this.receiveQtyItems = receiveQtyItems;
		this.discountPercentage = percentOff;
	}
	
	/**
	 * Returns a BigDecimal representing the total calculated
	 * discount for a given Item in the cart that is on special.
	 * 
	 * @param item 				an item object in the cart that has a special.
	 * @param itemBuyCount 		is the number of items of type item in the cart on special.
	 * @return					BigDecimal representing the total amount
	 * 							to subtract from the current cart pre-tax total.
	 */
	@Override
	public BigDecimal calculateDiscountAmount(Item item, int itemBuyCount) {
		BigDecimal discountAmount = new BigDecimal("0.00");
		int buyQtyRequirement = this.getBuyQtyRequirement();
		int itemsRemaining = itemBuyCount;
		int receiveDiscountOnQty = this.getReceiveQtyItems();
		
		if (this.getLimit() > 0) {
			itemsRemaining = this.getLimit();
		} 

		while (itemsRemaining > buyQtyRequirement) {
			BigDecimal amountToAddToDiscount = new BigDecimal("0.0");
			
			// Special Applies; adjust items remaining first
			itemsRemaining = itemsRemaining - buyQtyRequirement;
			
			// Calculate discounted unit sell price = sale price * discount percentage
			BigDecimal discountedUnitSalePrice = item.getSalePrice()
					.multiply(new BigDecimal(this.getDiscountPercentage()/100));

			// Calculate the amount to add to the total discount amount
			amountToAddToDiscount = discountedUnitSalePrice
					.multiply(new BigDecimal(receiveDiscountOnQty));
			
			/* Add the calculated amount and adjust the items remaining by items
			 * purchased at discount. 
			 */
			discountAmount = discountAmount.add(amountToAddToDiscount);
			itemsRemaining = itemsRemaining - receiveDiscountOnQty;
		} // End while
		return discountAmount.setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * Returns a BigDecimal representing the total calculated
	 * discount for a given item's weight in the cart that is
	 * on special. Total weight of all items is considered and 
	 * each iteration removes the purchased amount at regular 
	 * price and then the purchased amount at discounted price.
	 * 
	 * @param item 				The item the special is being applied to.
	 * @param itemWeightCount 	The total weight of all same items.
	 * @return					BigDecimal representing the total amount
	 * 							to subtract from the current cart pre-tax total.
	 */
	@Override
	public BigDecimal calculateDiscountAmountCBW(Item item, Double itemWeightCount) {
		BigDecimal discountAmount = new BigDecimal("0.00");
		double buyQtyRequirement = this.getBuyQtyRequirement();
		double itemsWeightRemaining = itemWeightCount;
		double receiveDiscountOnWeight = this.getReceiveQtyItems();
		
		if (this.getLimit() > 0) {
			itemsWeightRemaining = this.getLimit();
		}
		
		while(itemsWeightRemaining > buyQtyRequirement) {
			BigDecimal amountToAddToDiscount = new BigDecimal("0.0");
			
			// Special Applies, adjust remaining weight first
			itemsWeightRemaining = itemsWeightRemaining - buyQtyRequirement;

			// Calculate discounted unit price = sale price * discounted percentage
			BigDecimal discountedUnitSalePrice = item.getSalePrice()
					.multiply(new BigDecimal(this.getDiscountPercentage()/100));
			
			// Calculate the amount to add to the total discount amount
			amountToAddToDiscount = discountedUnitSalePrice
					.multiply(new BigDecimal(receiveDiscountOnWeight));
			
			/* Add the calculated amount and adjust the items remaining by items 
			 * purchased at discount.
			 */
			discountAmount = discountAmount.add(amountToAddToDiscount);
			itemsWeightRemaining = itemsWeightRemaining - receiveDiscountOnWeight;
		} // End while
		
		return discountAmount.setScale(2, RoundingMode.HALF_UP);
	}
	
	@Override
	public String toString() {
		int buyQty = this.getBuyQtyRequirement();
		double percentOff = (this.getDiscountPercentage());
		String itemOrItems = buyQty > 1
				? "items"
				: "item";
		
		String addLimit = this.getLimit() > 0
				? ", limit " + this.getLimit()
				: "";
		if (percentOff == 1.00) {
			return "Buy " + buyQty + " get " + this.getReceiveQtyItems() 
				+ " free";
		}
		
		return "Buy " + buyQty + " " + itemOrItems + ", get " 
			+ this.receiveQtyItems + " at " + percentOff
			+ "% off" + addLimit;
	}


} // End BuyNGetMatXPercentOff()
