package model;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * This class shows how to use the DungeonModelImpl class.

 * @author ZongyuWu
 *
 */
public class Driver {
  
  private static DungeonModel model;
  private static Scanner s;
  private static PrintStream ps;
  private static DungeonController controller;

  /**
   * Presents a sample run of DungeonModelImpl.

   * @param args nothing needed
   */
  public static void main(String[] args) {
    // Set up.
    model = new DungeonModelImpl();
    s = new Scanner(System.in);
    // Set up out put file.
    try {
      ps = new PrintStream("res/output.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } 
    controller = new DungeonControllerImpl(model, s, ps);
    
    // Start game with 50 percent of treasure and 1 monsters.
    // Other properties will be set in command line.
    controller.startGame(50, 1);
    
    
    /* Iterate for each location. Report treasures at this location, 
     * connected directions pick up the treasures, report player 
     * treasures after picking, then choose a direction to move.
     */ 
    String direction;
    String[] directions = {"north", "west", "south", "east"};
    while (!controller.isWin() & !controller.isLoose()) {
      // Quit?
      ps.append("Enter q to quit, other to continue.\n");
      if ("q".equals(s.next())) {
        break;
      }
      
      // location information.
      controller.locationInformation();
      
      // Player pick up treasures and arrow. Player information.
      controller.pick();
      controller.playeInformation();

      // Shoot or not.
      while (true) {
        ps.append("Want to shoot? y/n\n");
        String yesOrNo = s.next();
        if ("y".equals(yesOrNo)) {
          ps.append("Please enter direction from north, west, south, "
              + "or east, and positive distance.\n");
          direction = s.next();
          if (!Helper.isIn(directions, direction)) {
            ps.append("illegal direction");
            continue;
          }
          String distance = s.next();
          if (!Helper.isInt(distance)) {
            ps.append("Not an integer");
            continue;
          }
          int distanceInt = Integer.parseInt(distance);
          if (distanceInt <= 0) {
            ps.append("Invalid integer.");
            continue;
          }
          if (!controller.canShoot()) {
            ps.append("Not enough arrow.");
            break;
          }
          controller.shoot(direction, distanceInt);
        } else if ("n".equals(yesOrNo)) {
          break;
        } else {
          ps.append("Input is not y or n.");
          continue;
        }
      }
      
      // Move to next location.
      while (true) {
        ps.append("Please enter north, west, south, or east from the available locations\n");
        direction = s.next();
        if (!Helper.isIn(directions, direction)) {
          continue;
        }
        controller.move(direction);
        ps.append("Player moved to new location.\n\n");
        break;
      }
      
    }

    if (controller.isLoose()) {
      ps.append("Player is dead.\n");
      controller.playeInformation();
    } else if (controller.isWin()) {
      ps.append("Player win.\n");
      controller.playeInformation();
    }
    

    s.close();
  }

}
