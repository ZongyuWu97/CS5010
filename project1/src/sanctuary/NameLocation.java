package sanctuary;

/**
 * Represents the name of a monkey and its location.

 * @author ZongyuWu
 *
 */
public class NameLocation implements Comparable<NameLocation> {
  public String name;
  public Housing location;
  
  public NameLocation(String name, Housing location) {
    this.name = name;
    this.location = location;
  }
  
  @Override
  public int compareTo(NameLocation o) {
    return this.name.compareTo(o.name);
  }
}
