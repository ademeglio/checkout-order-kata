package com.pillartechnology.checkoutorderkata.entity;

import java.util.Collections;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public  class Inventory {

	private SortedSet<Item> items = Collections.synchronizedSortedSet(new TreeSet<Item>(Comparator.comparing(Item::getName)));
	
	// Default Constructor
	public Inventory() {};
	
	/* Getters & Setters */
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return true;
	}

	public SortedSet<Item> getItems() {
		return items;
	}
	
	
	/* Methods */
	
	public void addItem(Item item) {
		items.add(item);
	}


} // End Inventory()
