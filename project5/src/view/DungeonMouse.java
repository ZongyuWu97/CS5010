package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.DungeonController;

public class DungeonMouse extends MouseAdapter {

  private DungeonController listener;

  public DungeonMouse(DungeonController controller) {
    this.listener = controller;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub

  }


}
