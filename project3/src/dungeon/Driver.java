package dungeon;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * This class shows how to use the DungeonModelImpl class.

 * @author ZongyuWu
 *
 */
public class Driver {
  
  private static PrintStream ps = null;

  /**
   * Presents a sample run of DungeonModelImpl.

   * @param args nothing needed
   */
  public static void main(String[] args) {
    
    // Set up out put file.
    try {
      ps = new PrintStream("res/output.txt");
      System.setOut(ps);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } 
    
    ps.append("Generate dungeon.\n");
    ps.append("Is this a wrapping dungeon?\n");
    Scanner s = new Scanner(System.in);
    
    //Enter wrapping or not.
    String wrap;
    while (true) {
      ps.append("Enter wrapping or non-wrapping:\n");
      wrap = s.next();
      if (!("wrapping".equals(wrap) | "non-wrapping".equals(wrap))) {
        continue;
      }
      break;
    }
    
    //Enter row number.
    int row;
    while (true) {
      ps.append("Enter a positive number of rows:\n");
      String rowString  =  s.next();
      if (!Helper.isInt(rowString)) {
        ps.append("Please enter an integer.\n");
        continue;
      }
      row = Integer.parseInt(rowString);
      if (row <= 0) {
        ps.append("Please enter a positive integer.\n");
      }
      break;
    }

    //Enter column number.
    int col;
    while (true) {
      ps.append("Enter a positive number of rows:\n");
      String colString  =  s.next();
      if (!Helper.isInt(colString)) {
        ps.append("Please enter an integer.\n");
        continue;
      }
      col = Integer.parseInt(colString);
      if (col <= 0) {
        ps.append("Please enter a positive integer.\n");
      }
      break;
    }
    
    //Enter interconnectivity.
    int inter;
    while (true) {
      ps.append("Enter a non-negative number of interconnectivity.\n");
      String interString  =  s.next();
      if (!Helper.isInt(interString)) {
        ps.append("Please enter an integer.\n");
        continue;
      }
      inter = Integer.parseInt(interString);
      if (inter < 0) {
        ps.append("Please enter a positive integer.\n");
      }
      break;
    }
    
    // Generate dungeon.
    DungeonModel dungeon = new DungeonModelImpl();
    final String maze = dungeon.create(wrap, row, col, inter);
    dungeon.setStartEnd();
    Location start = dungeon.getStart();
    
    Location end = dungeon.getEnd();
    ps.append("A " + wrap + String.format(" dungeon of %d rows"
        + " and %d columns has been created.\n", row, col));
    ps.append("The start is at " + String.format("row %d, col "
        + "%d.\n", start.getRow(), start.getColumn()));
    ps.append("The end is at " + String.format("row %d, col %d."
        + "\n\n", end.getRow(), end.getColumn()));
    ps.append("Degree of each location is:\n\n" + maze + "\n");

    // Add treasure to 50 percent of the caves.
    dungeon.addTreasure(50);
    
    // Player enter dungeon.
    dungeon.enter();
    
    /* Iterate for each location. Report treasures at this location, 
     * connected directions pick up the treasures, report player 
     * treasures after picking, then choose a direction to move.
     */ 
    String direction;
    String[] directions = {"north", "west", "south", "east"};
    while (!dungeon.getPlayer().getLocation().equals(end)) {
      // Report treasures at current location and directions connected.
      ps.append(dungeon.playerLocation());
      
      // Player pick up treasures.
      dungeon.pick();
      
      // Player treasure after picking.
      ps.append("Player status after picking treasure at current location:\n");
      ps.append(dungeon.playerDescription());
      
      // Move to next location.
      while (true) {
        ps.append("Please enter north, west, south, or east from the available locations\n");
        direction = s.next();
        if (!Helper.isIn(directions, direction)) {
          continue;
        }
        break;
      }
      Location nextLocation = dungeon.getNextLocation(dungeon.getPlayer().getLocation(), direction);
      dungeon.move(nextLocation);
      ps.append("Player moved to new location.\n\n");
    }
    
    // Report final status.
    ps.append("Player has reached the end.\n");
    ps.append(dungeon.playerLocation());
    dungeon.pick();
    ps.append(dungeon.playerDescription());
    
    s.close();
  }

}
