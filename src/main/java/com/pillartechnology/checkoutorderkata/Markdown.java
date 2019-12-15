package com.pillartechnology.checkoutorderkata;

import java.math.BigDecimal;

public class Markdown {

	private String description;
	private BigDecimal markdownAmount;

	public Markdown(String description, String markdownAmount) {
		this.description = description;
		this.markdownAmount = new BigDecimal(markdownAmount);
	}

	public BigDecimal getMarkdownAmount() {
		return markdownAmount;
	}

	
} // End Markdown()
