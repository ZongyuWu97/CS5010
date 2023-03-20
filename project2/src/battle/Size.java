package battle;

/**
 * Size of a belt.

 * @author ZongyuWu
 *
 */
public enum Size {
  Small("Small"), Medium("Medium"), Large("Large");
  
  String size;
  
  Size(String size) {
    this.size = size;
  }
  
  @Override
  public String toString() {
    return this.size;
  }

}
