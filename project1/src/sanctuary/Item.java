package sanctuary;

/**
 * Item class of food name and the quantity needed.

 * @author ZongyuWu
 *
 */
public class Item {
  public Food name;
  public int quantity;
  
  public Item(Food name, int quantity) {
    this.name = name;
    this.quantity = quantity;
  }
}
