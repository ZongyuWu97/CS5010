## Overview

The problem is to set up a battle between two players. Each player has four abilities, strength, constitution, dexterity, and charisma. Players can request for one weapon and equip headgear, potions, belt, and footwear. Each gear change one or more abilities depending on the type of gear.

After armed, two player will begin battle in turn. The one with higher charisma will start first.


## List of features

+ Add players to arena.
+ Generate gear bag for player.
+ Equip the player with gear bag.
+ Request weapon for player.
+ Generate description for player.
+ Decide if the game is a tie.
+ Players attack in turn after start battle.
+ User can rematch by type `y` and exit by type `n`.

## How to run

Put the `battle.jar` file in the parent directory of `res/` folder. Double click the jar file then it will generate a `output.txt` file in the `res/` folder.

## Example

`Main.java` -- `output.txt`:
+ Generate the model and two players.
+ Print basic information for the two players.
+ Equip players with gears and weapons.
+ Print information for armed players.
+ Decide if it's a tie.
+ Begin battle.
+ Write all informations and result of each turn to `output.txt`.

## Design changes

+ Changed `Gear` from interface to abstract class.
+ Changed `Weapon` from interface to enumeration.

## Assumptions

+ Base abilities should be positive.
+ Final abilities could be negative.
+ Non-positive damage has no effect.
+ New headgear and footwear will take place of old ones. 
+ Potions will last for the whole battle.
+ No belt can be added if not enough unit.

## Limitations

All required functions are implemented. Function of players attack in turn is implemented in `Main.java` as there was originally a controller. So this function is not implemented in the model.

## Citations


