package com.pillartechnology.checkoutorderkata.discounts;

import java.math.BigDecimal;

public class Markdown {

	private String description;
	private BigDecimal markdownAmount;

	public String getDescription() {
		return description;
	}
	
	public Markdown(String description, String markdownAmount) {
		this.description = description;
		this.markdownAmount = new BigDecimal(markdownAmount);
	}

	public BigDecimal getMarkdownAmount() {
		return markdownAmount;
	}



	
} // End Markdown()
