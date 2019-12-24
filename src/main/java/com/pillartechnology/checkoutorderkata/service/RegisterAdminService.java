package com.pillartechnology.checkoutorderkata.service;

import java.util.Collection;
import java.util.HashMap;

import com.pillartechnology.checkoutorderkata.discounts.Markdown;
import com.pillartechnology.checkoutorderkata.discounts.Special;
import com.pillartechnology.checkoutorderkata.entity.Item;

/**
 * Interface for administration of:
 * <ul>
 *   <li>Inventory</li>
 *   <li>Specials</li>
 *   <li>Markdowns</li>
 * </ul>
 * 
 * @version 0.1.0
 * @see Item
 * @see Special
 * @see Markdown
 */
public interface RegisterAdminService {

	HashMap<String, Item> INVENTORY = new HashMap<String, Item>();
	HashMap<String, Special> SPECIALS = new HashMap<String, Special>();
	HashMap<String, Markdown> MARKDOWNS = new HashMap<String, Markdown>();

	/**
	 * Creates a new {@link Item} using the item name, item price, and if <code>true</code>
	 * setting item as charge by weight.
	 * 
	 * @param itemName			name of the item being created.
	 * @param itemPrice			default price of the item.
	 * @param isChargeByWeight	<code>true</code> if the item will need to be scaled
	 * 							when completing the {@link CartItem} creation.
	 */
	void createItem(String itemName, String itemPrice, boolean isChargeByWeight);

	/**
	 * Gets item from <code>INVENTORY</code>. Used whenever we need to verify that an item
	 * exists in inventory. Returns the item by using the <code>itemName</code> as the key
	 * in the map.
	 * 
	 * @param itemName			name of the item to search for.
	 * @return					{@link Item}
	 */
	Item getItem(String itemName);

	/**
	 * Creates a new {@link Special} of type {@link BuyNForX} using the special name, minimum
	 * quantity of items required to buy, and the price amount for the group.
	 * 
	 * @param specialName		identifying name of the special.
	 * @param buyQtyRequirement	minimum quantity of items needed in cart
	 * 							before Special applies. 
	 * @param discountPrice 	price to return for group of items purchased.
	 */
	void createSpecialBuyNForX(String specialName, int buyQtyRequirement, String discountPrice);

	/**
	 * Creates a new {@link Special} of type {@link BuyNGetMatXPercentOff} using the special name, minimum
	 * quantity of items required to buy, number of items to receive at discounted percentage 
	 * and the discount percentage.
	 * 
	 * @param specialName		identifying name of the special.
	 * @param buyQtyRequirement	minimum quantity of items needed in cart
	 * 							before Special applies. 
	 * @param receiveQtyItems	number of items to receive at discounted rate.
	 * @param percentOff		discount percentage entered as a whole number.
	 * 							<strong>Example:</strong> 50% is entered as
	 * 							50 or 50.0.
	 */
	void createSpecialBuyNGetMAtXPercentOff(String specialName, int buyQtyRequirement, 
			int receiveQtyItems, double percentOff); 
	
	/**
	 * Gets special from <code>SPECIALS</code> using name of the special 
	 * as a key for the object map.
	 * 
	 * @param specialName		name of the special to search for.
	 * @return					{@link Special}
	 * @see 					Special
	 * @see						BuyNGetMatXPercentOff
	 * @see						BuyNForX
	 */
	Special getSpecial(String specialName);

	/**
	 * Creates a new {@link Markdown} using a mark down description and price.
	 * 
	 * @param description		description of the markdown for reference.
	 * @param markdownAmount	represents the amount to subtract from the default price.
	 */
	void createMarkdown(String description, String markdownAmount);

	/**
	 * Gets mark-down from <code>MARKDOWNS</code> using the name of the mark-down
	 * as a key for the object map.
	 * 
	 * @param markdownDescription	name of the mark-down to search for.
	 * @return						{@link Markdown}
	 * @see 						Markdown
	 */
	Markdown getMarkdown(String markdownDescription);

	/**
	 * Gets the inventory collection for referencing items within 
	 * the collection.
	 * 
	 * @return						Collection of Items within <code>INVENTORY</code>.
	 */
	Collection<Item> getInventory();

	/**
	 * Gets the collection of Specials for referencing the specials stored.
	 * 
	 * @return					Collection of Specials which can be any type of 
	 * 							{@link Special} such as,{@link BuyNGetMatXPercentOff} 
	 * 							or {@link BuyNForX}.
	 */
	Collection<? extends Special> getSpecials();

	/**
	 * Gets the collection of Markdowns for referencing the markdowns available for
	 * use.
	 * 
	 * @return				Collection of Markdowns.
	 */
	Collection<Markdown> getMarkdowns();

	/**
	 * Updates {@link Item}'s default price or adds {@link Markdown} or 
	 * assigns {@link Special}. <code>itemName</code> is required.
	 * 
	 * @param itemName				name of item to search for.
	 * @param newDefaultPrice		assignment of new default price.
	 * @param markdownDescription	name of mark-down to search for and apply.
	 * @param specialName			name of special to search for and apply.
	 */
	void updateItem(String itemName, String newDefaultPrice, String markdownDescription
			, String specialName);

	/**
	 * Adds a limit to {@limit Special} using the <code>specialName</code>
	 * to search for the special and uses <code>limitQty</code> to assign
	 * the limit quantity.
	 * 
	 * @param specialName			name of special to search for.
	 * @param limitQty				amount to set limit to
	 */
	void addLimitToSpecial(String specialName, int limitQty);


} // End RegisterAdminService()
