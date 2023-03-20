package polynomial;

/**
 * Represents the end of a polynomail.

 * @author ZongyuWu
 *
 */
public class EmptyImpl implements Polynomial {
  
  public EmptyImpl() {
  }

  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    return other;
  }

  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    throw new IllegalArgumentException("Cannot add to empty polynomial.");
  }

  @Override
  public boolean isSame(Polynomial poly) {
    return poly instanceof EmptyImpl;
  }

  @Override
  public double evaluate(double x) {
    return 0;
  }

  @Override
  public int getCoefficient(int power) {
    return 0;
  }

  @Override
  public int getDegree() {
    return 0;
  }
  
  @Override
  public String toString() {
    return "0";
  }

}
