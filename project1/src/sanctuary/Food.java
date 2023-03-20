package sanctuary;

/**
 * Enum class of favorite food.

 * @author ZongyuWu
 *
 */
public enum Food {
  Eggs("Eggs"), 
  Fruits("Fruits"),
  Insects("Insects"),
  Leaves("Leaves"), 
  Nuts("Nuts"),
  Seeds("Seeds"), 
  TreeSap("TreeSap");
  
  private final String food;
  
  private Food(String food) {
    this.food = food;
  }
  
  @Override
  public String toString() {
    return this.food;
  }
  
}
