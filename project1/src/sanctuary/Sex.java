package sanctuary;

/**
 * Enum class of sex.

 * @author ZongyuWu
 *
 */
public enum Sex {
  Male("Male"),
  Female("Female");
  
  private final String sex;
  
  private Sex(String sex) {
    this.sex = sex;
  }
  
  @Override
  public String toString() {
    return this.sex;
  }
}
