package com.pillartechnology.checkoutorderkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestMarkdown {

	@Test
	public void shouldReturnMarkdownAmountAsBigDecimal() {
		Markdown markdown = new Markdown("$1.00 OFF", "1.00");
		
		assertEquals("1.00", markdown.getMarkdownAmount().toString());
	}
	
	@Test
	public void shouldReturnMarkdownDescription() {
		Markdown markdown = new Markdown("$1.00 OFF", "1.00");
		
		assertEquals("$1.00 OFF", markdown.getDescription());
	}
	
	
} // End TestMarkdown()
