# Checkout Order Total

## Instructions

Hello and thank you for reviewing my kata exercise.

I have used Gradle to compile running tests and the application. [Reference](https://docs.gradle.org/current/userguide/java_testing.html)  
I'm guessing you know the dril, just in case...
### Reviewing Tests
1. Please go into the application folder.
2. Type the following in the command line `./gradlew test`.
3. After the tests complete, you will be given a link to a published build scan. You will need to go to the link and enter
an email address in order to activate the scan. The scan takes minimal time to populate once you activate it and Gradle will
send you an email when it is ready.

Alternatively, you can go into the `project/build/tests/test/index.html` and see a Gradle created report.

There are two main API classes that a, _cash register_ for example could access. These are located in the services sub package.

## Requirements

- [x] API to configure
  - [x] Prices
  - [x] Specials
  - [x] Markdowns
- [x] Be able to accept scanned item or item & weight through an api call (while keeping accurate current total through the process).
- [x] Be able to remove items from an order, immediately correcting the current total.

## Use Cases

- [x] You will need a way to configure the prices of scannable items prior to being scanned.
- [x] Accept a scanned item. The total should reflect an increase by the per-unit price after the scan. 
- [x] Accept a scanned item and a weight. The total should reflect an increase of the price of the item for the given weight.
- [x] Support a markdown. A marked-down item will reflect the per-unit cost less the markdown when scanned. A weighted item with a markdown will reflect that reduction in cost per unit.
- [x] Support a special in the form of "Buy N items get M at %X off." For example, "Buy 1 get 1 free" or "Buy 2 get 1 half off."
- [x] Support a special in the form of "N for $X." For example, "3 for $5.00"
- [x] Support a limit on specials, for example, "buy 2 get 1 free, limit 6" would prevent getting a third free item.
- [x] Support removing a scanned item, keeping the total correct after possibly invalidating a special.
- [x] Support "Buy N, get M of equal or lesser value for %X off" on weighted items. For example, "Buy 2 pounds of ground beef, get 1 pound half off."

## Notes

1. Scan Item
    1. If this isPriceByWeight then query the user for the weight of the object immediately after that.
    1. Check if the cart contains this item and determine a count of the item type.
    1. Adjust price if it has a markdown (hasMarkdown)
    1. Adjust price if it is on special. (hasSpecial)
