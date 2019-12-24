package com.pillartechnology.checkoutorderkata.entity;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pillartechnology.checkoutorderkata.discounts.Markdown;
import com.pillartechnology.checkoutorderkata.discounts.Special;

/**
 * Item is the item that is being sold to the end user.
 * It stores various state information used by the application.
 * 
 * @version 0.1.0
 */
public class Item {

	Logger logger = LoggerFactory.getLogger(Item.class);
	
	private String name;
	
	/** 
	 * Determines if this is billed by weight
	 * instead of by unit.
	 * <p>The item weight is stored when
	 * adding the item to a {@link Cart}.
	 */
	private boolean isChargeByWeight;
	
	/** 
	 * Initial price of this referenced when
	 * removing a {@link Markdown}.
	 */
	private BigDecimal defaultPrice;
	
	/**
	 * Determines if this has a {@link Markdown}
	 * price applied.
	 */
	private boolean hasMarkdown;
	/** {@link Markdown applied to this item.*/
	private Markdown markdown;
	
	/**
	 * Determines if this has a {@link Special}
	 * such as <em>Buy n get m at x% off</em>. 
	 */
	private boolean hasSpecial;
	/** {@link Special} applied to this item.*/
	private Special special;
	
	/**
	 * Adjusted sale price after applied
	 * {@link Markdown}.
	 * <p><b>Default = {@link defaultPrice}</b> if
	 * there is no {@link Markdown} applied.
	 */
	private BigDecimal salePrice;

	
	/* Constructors */
	
	/**
	 * Class Constructor specifying name.
	 * 
	 * @param itemName name or description.
	 */
	public Item(String itemName) {
		this.name = itemName;
	}
	
	/**
	 * Class Constructor specifying name and default price.
	 * 
	 * @param itemName name or description.
	 * @param defaultPrice base sell price.
	 */
	public Item(String itemName, String defaultPrice) {
		this.name = itemName;
		this.defaultPrice = new BigDecimal(defaultPrice);
		this.salePrice = this.defaultPrice;
	}
	
	/**
	 * Class Constructor specifying name, default price and setting
	 * as a charge by weight item type.
	 * 
	 * @param itemName name or description.
	 * @param defaultPrice base sell price.
	 * @param isChargeByWeight true if item is charged by weight.
	 */
	public Item(String itemName, String defaultPrice, boolean isChargeByWeight) {
		this.name = itemName;
		this.defaultPrice = new BigDecimal(defaultPrice);
		this.salePrice = this.defaultPrice;
		this.isChargeByWeight = isChargeByWeight;
	}

	
	// Getters & Setters

	public String getName() {
		return name;
	}
	
	public BigDecimal getDefaultPrice() {
		return defaultPrice;
	}
	
	/**
	 * Allows setting or resetting of default price. 
	 * This will also reset the sale price.
	 * 
	 * @param defaultPrice
	 */
	public void setDefaultPrice(String defaultPrice) {
		this.defaultPrice = new BigDecimal(defaultPrice);
		this.salePrice = new BigDecimal(defaultPrice);
	}

	public boolean isChargeByWeight() {
		return isChargeByWeight;
	}

	public void setChargeByWeight(boolean isChargeByWeight) {
		this.isChargeByWeight = isChargeByWeight;	
	}

	public boolean hasMarkdown() {
		return hasMarkdown;
	}

	public Markdown getMarkdown() {
		return markdown;
	}
	
	public boolean hasSpecial() {
		return hasSpecial;
	}
	
	public Special getSpecial() {
		return special;
	}	
	
	public BigDecimal getSalePrice() {
		return salePrice;
	}

	
	/* Methods */

	/**
	 * Updates {@link Markdown} state and updates
	 * the sale price set by {@link calculateSalePrice}.
	 * <p>(<em>defaultPrice - Markdown.markdownAmount</em>).</p>
	 * 
	 * @param markdown is the {@link Markdown} to apply.
	 */
	public void addMarkdown(Markdown markdown) {
		this.hasMarkdown = true;
		this.markdown = markdown;
		this.calculateSalePrice();
	}

	/**
	 * Updates {@link Special} state.
	 * 
	 * @param special is the {@link Special} to apply.
	 */
	public void addSpecial(Special special) {
		this.hasSpecial = true;
		this.special = special;
	}
	
	/**
	 * Sale price is calculated whenever the item is created
	 * with a default price. The initial sale price is set equal
	 * to the default price unless there is a {@link Markdown}.
	 * 
	 * <p>If there is a {@link Markdown}, the sale price is calculated
	 * by subtracting the {@link Markdown.markdownAmount} from 
	 * the {@link defaultprice}.
	 * </p>
	 */
	public void calculateSalePrice() {
		if (this.getMarkdown() != null) {
			logger.info(this.getMarkdown().getDescription() 
					+ " markdown applied to default price for " + this.getName());
			salePrice = defaultPrice.subtract(this.getMarkdown().getMarkdownAmount());
		}
	}
	
	@Override
	public String toString() {
		return "Item [name=" + name + ", defaultPrice=" + defaultPrice + ", isChargeByWeight=" + isChargeByWeight
				+ ", hasMarkdown=" + hasMarkdown + ", markdown=" + markdown + ", hasSpecial=" + hasSpecial
				+ ", special=" + special + ", salePrice=" + salePrice + "]";
	}

	
} // End Item()
