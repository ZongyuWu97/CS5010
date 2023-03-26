## Overview

This is the model of a dungeon. A dungeon have caves and tunnels in it, each cave may contain treasures. A player can be added to the dungeon the move to directions that is connected. Player can pick up treasures in any cave.

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
- Aplayer to pick up treasure that is located in their same location.

## How to run

Put the `dungeon.jar` file in the parent directory of `res/` folder. Double click the jar file then it will generate a `output.txt` file in the `res/` folder.

## Example

`Driver.java` -- `wrapping.txt`, `non-wrapping`:
+ Generate a dungeon with number of rows, columns, interconnectivity, and wrapping or non-wrapping.
- Add treasures and player into the dungeon.
- Player to different locations.
- Report player description and location description at each step.
- Report final status when reached the end.
+ Write all informations and result of each move to `output.txt`.

## Design changes

+ Added a `Location` interface.

## Assumptions

+ Number of rows and column should be positive.
- Interconnectivity should be non-negative.
- Percentage of treasure should be between 0 and 100.

## Limitations

All required functions are implemented.

## Citations


