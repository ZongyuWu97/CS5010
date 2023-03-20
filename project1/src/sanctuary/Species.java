package sanctuary;

/**
 * Species enum class.

 * @author ZongyuWu
 *
 */
public enum Species {
  Drill("Drill"), 
  Guereza("Guereza"), 
  Howler("Howler"), 
  Mangabey("Mangabey"), 
  Saki("Saki"), 
  Spider("Spider"), 
  Squirrel("Squirrel"), 
  Tamarin("Tamarin");
  
  private final String specie; 

  private Species(String s) {
    this.specie = s;
  }
  
  @Override
  public String toString() {
    return this.specie;
  }
}
