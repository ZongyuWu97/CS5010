## Overview

This is the model and controller of a dungeon. A dungeon have caves and tunnels in it, each cave may contain treasures, arrows and monsters. A player can be added to the dungeon the move to directions that is connected. Player can pick up treasures and arrows in any location. Player can shoot the monster with specified direction and distance. Monster can kill the player.

## List of features

- The dungeon is able to represented on a 2-D grid.
- There is a path from every cave in the dungeon to every other cave in the dungeon.
- Each dungeon can be constructed with a degree of interconnectivity. 
- Not all dungeons "wrap" from one side to the other.
- One cave is randomly selected as the start and one cave is randomly selected to be the end. The path between the start and the end locations should be at least of length 5.
- Both wrapping and non-wrapping dungeons to be created with different degrees of interconnectivity.
- Have three types of treasure: diamonds, rubies, and sapphires.
- Treasure to be added to a specified percentage of caves. 
- A player to enter the dungeon at the start.
- Gives a description of the player that includes a description of what treasure the player has collected.
- Gives a description of the player's location that includes a description of treasure in the room and the possible moves (north, east, south, west) that the player can make from their current location.
- A player to move from their current location.
- A player to pick up treasure that is located in their same location.
- There is always at least one Otyugh in the dungeon located at the specially designated end cave. The actual number is specified on the command line. There is never an Otyugh at the start.
- Otyugh only occupy caves and are never found in tunnels. Their caves can also contain treasure or other items.
- They can be detected by their smell. A less pungent smell can be detected when there is a single Otyugh 2 positions from the player's current location. Detecting a more pungent smell either means that there is a single Otyugh 1 position from the player's current location or that there are multiple Otyughs within 2 positions from the player's current location
- They are adapted to eat whatever organic material that they can find, but love it when fresh meat happens into the cave in which they dwell.
- Player starts with 3 crooked arrows but can find additional arrows in the dungeon with the same frequency as treasure. Arrows and treasure can be, but are not always, found together. Furthermore, arrows can be found in both caves and tunnels.
- A player that has arrows, can attempt to slay an Otyugh by specifying a direction and distance in which to shoot their crooked arrow. Distance is defined as the number of caves (but not tunnels) that an arrow travels.
- Distances must be exact. 
- It takes 2 hits to kill an Otyugh. Players has a 50% chance of escaping the Otyugh if they enter a cave of an injured Otyugh that has been hit by a single crooked arrow.
- Controller can navigate the player through the dungeon.
- Controller can pick up treasure and/or arrows if they are found in the same location as the player.
- Controller can shoot an arrow in a given direction.



## How to run

Put the `dungeon.jar` file in the parent directory of `res/` folder. Double click the jar file then it will generate a `output.txt` file in the `res/` folder.

## Example

- Type `wrapping`/`non-wrapping`, int `row`, int `col`, int `interconnectivity`, int `percentOfTreasure`, int `numMonster` then click `Generate` to start the game. Options are seperated by space.
- Click `north`, `east`, `south`, or `west` to move. Can also type the direction then click `Move`.
- Type `direction` and `distance` seperated by space then click `shoot` to shoot.
- At each step, player and location information will be displayed on the text area.
- Click `Exit` to exit the game.

## Design changes

- Encapsulated some function of the model into the controller.

## Assumptions

- Number of rows and column should be positive.
- Interconnectivity should be non-negative.
- Percentage of treasure should be between 0 and 100.
- Distance of shooting should be non negative.

## Limitations

- Not support multi-player mode.
- Not support restart with same random seed.
- Not support command line mode.

## Citations


