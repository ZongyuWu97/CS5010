## Overview

The problem is to construct a sanctuary which consists of isolations and enclosures. It will house primates and generate reports like each species' location, each monkey's location and the quantity needed for different kind of food.

I write a `Sanctuary` interface and a `JungleFriendsSanctuary` class to implement it. This sanctuary contains numbers of isolations and enclosures which are instance of the `JungleFriendsHousing` class. This class implements the `Housing` interface. Isolations and enclosures can house primates which are instances of the `Monkey` class.

Users need to initialize the sanctuary with n isolations and m enclosures. Given k primates, the user can put monkeys into isolation or enclosure based on his needs. 

## List of features

+ Increase number of isolation.
+ increase number of enclosure.
+ Report species and their corresponding location.
+ Look up the location of a perticular species.
+ Produce a sign to show name, sex, and favorite food for each monkey in an enclosure.
+ Produce a list of all monkey's name and their corresponding location.
+ Produce a shopping list of food and the quantity needed.


## How to run

Put the `sanctuary.jar` file in the parent directory of `res/` folder. Double click the jar file then it will generate a `output.txt` file in the `res/` folder.

## Example

`Demo.java` -- `output.txt`:
+ Generate monkeys.
+ Generate a sanctuary with 5 isolations and 16 enclosures. Size of enclosure is random.
+ Put monkeys into isolations, then pop monkeys from isolations to enclosure, until their is no room available.
+ Due to the randomness of the size of enclosure and the order of monkeys pushed, there might be space in enclosure for one species but all isolations have been occupied by another species.
+ Write the report, location of a specific species, sign of enclosure, name list, and shopping list to `output.txt`.

## Design changes

+ Write `Sanctuary` and `Housing` as interfaces as suggested.
+ Write tests for methods with no return value. Some tests like getters and void returns are not listed in the `plan.pdf`, but they are present in the tests.

## Assumptions

+ Monkey.name should not be null.
+ Monkey.weight should be positive.
+ Monkey.age should be nonnegative.
+ Enclosure.size should be positive.
+ Number of isolations and enclosures should be positive.

## Limitations

All required functions are implemented. Though some return values are lists of species and locations that need to be unziped for further usage.

For example, the return of `report` method is a `SpeciesLocation` object where `SpeciesLocation.name` is a list of species, and `SpeciesLocation.location` is another list of list of locations of that species.

## Citations


