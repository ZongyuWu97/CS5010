package model;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Implementation of the controller.

 * @author ZongyuWu
 *
 */
public class DungeonControllerImpl implements DungeonController {
  
  private DungeonModel model;
  private Scanner scanner;
  private PrintStream ps;
  private MyRandom random;
  
  /**
   * Constructor of the controller.

   * @param model model
   * @param s input
   * @param ps output
   */
  public DungeonControllerImpl(DungeonModel model, Scanner s, PrintStream ps) {
    this.model = model;
    this.scanner = s;
    this.ps = ps;
    this.random = new MyRandom(true);
  }

  @Override
  public void startGame(int percentage, int numMonster) {
    System.setOut(ps);
    
    ps.append("Generate dungeon.\n");
    ps.append("Is this a wrapping dungeon?\n");
    
    //Enter wrapping or not.
    String wrap;
    while (true) {
      ps.append("Enter wrapping or non-wrapping:\n");
      wrap = scanner.next();
      if (!("wrapping".equals(wrap) | "non-wrapping".equals(wrap))) {
        continue;
      }
      break;
    }
    
    //Enter row number.
    int row;
    while (true) {
      ps.append("Enter a positive number of rows:\n");
      String rowString  =  scanner.next();
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
      String colString  =  scanner.next();
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
      String interString  =  scanner.next();
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
    final String maze;
    try {
      maze = model.create(wrap, row, col, inter);
    } catch (NullPointerException e) {
      ps.append("Null model");
      return;
    }
    model.setStartEnd();
    Location start = model.getStart();
    
    Location end = model.getEnd();
    ps.append("A " + wrap + String.format(" dungeon of %d rows"
        + " and %d columns has been created.\n", row, col));
    ps.append("The start is at " + String.format("row %d, col "
        + "%d.\n", start.getRow(), start.getColumn()));
    ps.append("The end is at " + String.format("row %d, col %d."
        + "\n\n", end.getRow(), end.getColumn()));
    ps.append("Degree of each location is:\n\n" + maze + "\n");

    // Add treasure to specified percent of the caves.
    model.addTreasure(percentage);
    model.addMonster(numMonster);
    
    // Player enter dungeon.
    model.enter();

  }

  @Override
  public void move(String direction) {
    Location nextLocation = null;
    try {
      nextLocation = model.getNextLocation(model.getPlayer().getLocation(), direction);
    } catch (NullPointerException e) {
      ps.append("Invalid direction.\n");
      return;
    }
    model.move(nextLocation);
    
    if (nextLocation.getMonsterHealth() == 2) {
      model.getPlayer().setDead();
    } else if (nextLocation.getMonsterHealth() == 1) {
      if (random.nextInt(2) == 1) {
        model.getPlayer().setDead();
      }
    }
  }

  @Override
  public void pick() {
    model.pick();
  }

  @Override
  public void shoot(String direction, int distance) {
    if (model.getPlayer().getArrow() <= 0) {
      ps.append("Not enough arrow.\n");
      return;
    }
    int monsterStatus = model.shoot(direction, distance);
    if (monsterStatus == 1) {
      ps.append("Monster injured.\n");
    } else if (monsterStatus == 0) {
      ps.append("Monster dead.\n");
    } else if (monsterStatus == -1) {
      ps.append("No monster here.\n");
    }
  }

  @Override
  public void locationInformation() {
    ps.append(model.playerLocation());
  }

  @Override
  public void playeInformation() {
    ps.append(model.playerDescription());
  }

  @Override
  public boolean isWin() {
    return model.getPlayer().getLocation().equals(model.getEnd());
  }

  @Override
  public boolean isLoose() {
    return model.getPlayer().isDead();
  }

  @Override
  public boolean canShoot() {
    return model.getPlayer().getArrow() > 0;
  }

}
