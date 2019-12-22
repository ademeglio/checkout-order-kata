package com.pillartechnology.checkoutorderkata.entity;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pillartechnology.checkoutorderkata.discounts.Markdown;
import com.pillartechnology.checkoutorderkata.discounts.Special;

/**
 * Item is the item that is being sold to the end user.
 * It stores various state information used by the application.
 */
public class Item {

	Logger logger = LoggerFactory.getLogger(Item.class);
	
	private String name;
	private boolean isChargeByWeight;
	private BigDecimal defaultPrice;
	private boolean hasMarkdown;
	private Markdown markdown;
	private boolean hasSpecial;
	private Special special;
	private BigDecimal salePrice;

	/* Constructors */
	public Item(String itemName) {
		this.name = itemName;
	}
	
	public Item(String itemName, String defaultPrice) {
		this.name = itemName;
		this.defaultPrice = new BigDecimal(defaultPrice);
		this.salePrice = this.defaultPrice;
	}
	

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
	
	public void setDefaultPrice(String defaultPrice) {
		this.defaultPrice = new BigDecimal(defaultPrice);
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

	public void addMarkdown(Markdown markdown) {
		this.hasMarkdown = true;
		this.markdown = markdown;
		this.calculateSalePrice();
	}

	public void addSpecial(Special special) {
		this.hasSpecial = true;
		this.special = special;
	}
	
	/**
	 * Sale price is calculated whenever the item is created
	 * with a default price. The initial sale price is set at
	 * the default price unless there is a Markdown().
	 * 
	 * <p>If there is a Markdown(), the sale price is calculated
	 * by subtracting the markdown amount from the default price.
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
