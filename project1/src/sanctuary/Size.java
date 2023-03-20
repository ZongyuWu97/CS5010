package sanctuary;

/**
 * Enum class of size.

 * @author ZongyuWu
 *
 */
public enum Size {
  Large("Large"), 
  Medium("Medium"),
  Small("Small");
  
  private final String size;
  
  private Size(String size) {
    this.size = size;
  }
  
  @Override
  public String toString() {
    return this.size;
  }
}
