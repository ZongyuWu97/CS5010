package controller;


import javax.swing.JOptionPane;
import model.DungeonModel;
import util.Location;
import util.Monster;
import util.MyRandom;
import view.DungeonView;

/**
 * Implementation of the controller.

 * @author ZongyuWu
 *
 */
public class DungeonControllerImpl implements DungeonController {
  
  private DungeonModel model;
  private DungeonView view;
  private MyRandom random;
  private Location[][] prevLocations;
  private Location prevStart;
  private Location prevEnd;
  
  /**
   * Constructor of the controller.

   * @param model model
   * @param view view
   */
  public DungeonControllerImpl(DungeonModel model, DungeonView view) {
    this.model = model;
    this.view = view;
    this.random = new MyRandom(true);
  }
  
  @Override
  public void playGame() {
    this.view.addClickListener(this);
    this.view.refresh();
  }

  @Override
  public void startGame(String wrap, int row, int col,
      int interconnectivity, int percentage, int numMonster) {
    this.view.addClickListener(this);

    // Generate dungeon.
    model.create(wrap, row, col, interconnectivity);
    model.setStartEnd();

    prevLocations = model.getBoard();
    prevStart = model.getStart();
    prevEnd = model.getEnd();

    // Add treasure to specified percent of the caves.
    model.addTreasure(percentage);
    model.addMonster(numMonster);

    // Player enter dungeon.
    model.enter();
    
    view.refresh();
    
    view.show(model.playerLocation() + model.playerDescription());

  }
  
  @Override
  public void restart() {
    model.setBoard(prevLocations);
    model.setStartEnd(prevStart.getIndex(), prevEnd.getIndex());
    model.enter();
    view.refresh();
  }

  @Override
  public void move(String direction) {
    Location nextLocation = null;
    try {
      nextLocation = model.getNextLocation(model.getPlayer().getLocation(), direction);
    } catch (NullPointerException e) {
      JOptionPane.showMessageDialog(null, "Invalid direction.\n");
      return;
    }
    model.move(nextLocation);
    
    for (Monster m : nextLocation.getMonsters()) {
      if (m.getHealth() == 2) {
        model.getPlayer().setDead();
      } else if (m.getHealth() == 1) {
        if (random.nextInt(2) == 1) {
          model.getPlayer().setDead();
        }
      }
    }
    
    if (this.isLoose()) {
      view.show("Player is dead.\n" + model.playerDescription());
    } else if (this.isWin()) {
      view.show("Player win.\n" + model.playerDescription());
    }
    
    view.refresh();
    view.show(model.playerLocation() + model.playerDescription());
  }

  @Override
  public void pick() {
    model.pick();
    view.show(model.playerLocation() + model.playerDescription());
  }

  @Override
  public void shoot(String direction, int distance) {
    if (model.getPlayer().getArrow() <= 0) {
      JOptionPane.showMessageDialog(null, "Not enough arrow.\n");
      return;
    }
    int monsterStatus = model.shoot(direction, distance);
    if (monsterStatus == 1) {
      JOptionPane.showMessageDialog(null, "Hitted a monster.\n");
    } else if (monsterStatus == -1) {
      JOptionPane.showMessageDialog(null, "No monster here.\n");
    }
    
    view.show(model.playerLocation() + model.playerDescription());

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
