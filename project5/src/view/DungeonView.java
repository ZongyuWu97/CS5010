package view;

import controller.DungeonController;

/**
 * Interface of the Dungeon view.

 * @author ZongyuWu
 *
 */
public interface DungeonView {
  
  /**
   * Refresh and update the view with latest information.
   */
  public void refresh();
  
  /**
   * Add mouse listener.

   * @param listener listener
   */
  public void addClickListener(DungeonController listener);

  /**
   * Show location and player information.

   * @param playerLocation Information
   */
  public void show(String playerLocation);


}
