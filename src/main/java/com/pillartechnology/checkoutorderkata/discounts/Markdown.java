package com.pillartechnology.checkoutorderkata.discounts;

import java.math.BigDecimal;

/**
 * Markdown represents a price mark down assigned to an {@link Item).
 * 
 * @version 0.1.0
 * @see Item
 */
public class Markdown {

	/** Description of the markdown for reference */
	private String description;
	
	/** Represents the amount to subtract from the default price.*/
	private BigDecimal markdownAmount;

	public String getDescription() {
		return description;
	}
	
	// Constructor
	/**
	 * Class Constructor specifying the name and markdown amount.
	 * 
	 * @param description		this describes the markdown and is used as a reference
	 * 							to call the markdown. <strong>Example:</strong> $1.00 OFF
	 * @param markdownAmount	the amount to reduce the <code>Item.defaultPrice</code> by.
	 */
	public Markdown(String description, String markdownAmount) {
		this.description = description;
		this.markdownAmount = new BigDecimal(markdownAmount);
	}

	public BigDecimal getMarkdownAmount() {
		return markdownAmount;
	}

	
} // End Markdown()
