# Checkout Order Total

## Instructions

Hello and thank you for reviewing my kata exercise.

I have used Gradle to configure running the tests. [Reference](https://docs.gradle.org/current/userguide/java_testing.html)

_TODO ADD INSTRUCTIONS BEFORE SENDING IN FOR REVIEW_

## Requirements

- [ ] API to configure
  - [ ] Prices
  - [ ] Specials
  - [ ] Markdowns
- [ ] Be able to accept scanned item or item & weight through an api call (while keeping accurate current total through the process).
- [ ] Be able to remove items from an order, immediately correcting the current total.

## Use Cases

- [ ] You will need a way to configure the prices of scannable items prior to being scanned.
- [x] Accept a scanned item. The total should reflect an increase by the per-unit price after the scan. 
- [x] Accept a scanned item and a weight. The total should reflect an increase of the price of the item for the given weight.
- [x] Support a markdown. A marked-down item will reflect the per-unit cost less the markdown when scanned. A weighted item with a markdown will reflect that reduction in cost per unit.
- [x] Support a special in the form of "Buy N items get M at %X off." For example, "Buy 1 get 1 free" or "Buy 2 get 1 half off."
- [ ] Support a special in the form of "N for $X." For example, "3 for $5.00"
- [ ] Support a limit on specials, for example, "buy 2 get 1 free, limit 6" would prevent getting a third free item.
- [ ] Support removing a scanned item, keeping the total correct after possibly invalidating a special.
- [ ] Support "Buy N, get M of equal or lesser value for %X off" on weighted items. For example, "Buy 2 pounds of ground beef, get 1 pound half off."

## Notes

1. Scan Item
    1. If this isPriceByWeight then query the user for the weight of the object immediately after that.
    1. Check if the cart contains this item and determine a count of the item type.
    1. Adjust price if it has a markdown (hasMarkdown)
    1. Adjust price if it is on special. (hasSpecial)
