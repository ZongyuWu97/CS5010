package polynomial;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Represents the first and largest term of a polynomial.

 * @author ZongyuWu
 *
 */
public class PolynomialImpl implements Polynomial {
  
  private final int degree;
  private int coef;
  private Polynomial next;
  
  /**
   * Constructor for 0.
   */
  public PolynomialImpl() {
    this.degree = 0;
    this.coef = 0;
    this.next = new EmptyImpl();
  }
  
  /**
   * Constructor for a term given the degree and coefficient.

   * @param degree degree.
   * @param coef coefficient.
   */
  public PolynomialImpl(int degree, int coef) {
    if (degree < 0) {
      throw new IllegalArgumentException("Degree must be non-negative.");
    }
    this.degree = degree;
    this.coef = coef;
    this.next = new EmptyImpl();
  }
  
  /**
   * Constructor from a string.

   * @param str a string
   */
  public PolynomialImpl(String str) {
    if (str.isEmpty()) {
      this.degree = 0;
      this.coef = 0;
      this.next = new EmptyImpl();
      return;
    }
    Scanner s = new Scanner(str);
    PolynomialImpl dummy = new PolynomialImpl();
    PolynomialImpl head = dummy;
    String curr;
    HashMap<Integer, Integer> memo = new HashMap<>();
    
    int i;
    int length;
    int deg;
    Character ch;
    
    while (s.hasNext()) {
      curr = s.next();
      length = curr.length();
      i = 0;
      while (i < length) {
        ch = curr.charAt(i);
        if ('x' == ch) {
          break;
        }
        i++;
      }
      
      if (i == length) {
        memo.put(0, memo.getOrDefault(0, 0) + Integer.parseInt(curr.substring(0, i)));
      } else if (i == 0 || curr.charAt(i - 1) == '+') {
        deg = Integer.parseInt(curr.substring(i + 2, length));
        memo.put(deg, memo.getOrDefault(deg, 0) + 1);
      } else if (curr.charAt(i - 1) == '-') {
        deg = Integer.parseInt(curr.substring(i + 2, length));
        memo.put(deg, memo.getOrDefault(deg, 0) - 1);
      } else {
        deg = Integer.parseInt(curr.substring(i + 2, length));
        memo.put(deg, memo.getOrDefault(deg, 0) + Integer.parseInt(curr.substring(0, i)));
      }
    }
    s.close();
    
    List<Integer> keys = memo.keySet().stream().sorted().collect(Collectors.toList());
    for (int j = keys.size(); j > 0; j--) {
      if (keys.get(j - 1) < 0) {
        throw new IllegalArgumentException("Negative power.");
      }
      if (memo.get(keys.get(j - 1)) == 0) {
        continue;
      }
      head.next = new PolynomialImpl(keys.get(j - 1), memo.get(keys.get(j - 1)));
      head = (PolynomialImpl) head.next;
    }
    
    PolynomialImpl res = (PolynomialImpl) dummy.next;
    this.degree = res.getDegree();
    this.coef = res.getCoefficient(res.getDegree());
    this.next = ((PolynomialImpl) res).next;
  }

  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (other instanceof EmptyImpl) {
      return this;
    }
    if (!(other instanceof PolynomialImpl)) {
      throw new IllegalArgumentException("Not a polynomial.");
    }
    
    PolynomialImpl res = new PolynomialImpl(this.toString());
    PolynomialImpl head = res;
    while (head.next instanceof PolynomialImpl) {
      head = (PolynomialImpl) head.next;
    }
    head.next = new PolynomialImpl(other.toString());
    return res;
  }

  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Negative poewr.");
    }
    PolynomialImpl head = this;
    while (head.next instanceof PolynomialImpl) {
      head = (PolynomialImpl) head.next;
    }    
    head.next = new PolynomialImpl(power, coefficient);
  } 
  
  @Override
  public boolean isSame(Polynomial poly) {
    return this.toString().equals(poly.toString());
  }

  @Override
  public double evaluate(double x) {
    double res = 0;
    Polynomial head = this;
    while (head instanceof PolynomialImpl) {
      res += ((PolynomialImpl) head).coef 
          * Math.pow(x, ((PolynomialImpl) head).degree);
      head = ((PolynomialImpl) head).next;
    }
    return res;
  }

  @Override
  public int getCoefficient(int power) {
    Polynomial head = this;
    int res = 0;
    while (head instanceof PolynomialImpl) {
      if (((PolynomialImpl) head).degree == power) {
        res += ((PolynomialImpl) head).coef;
      } 
      head = ((PolynomialImpl) head).next;
    }
    return res;
  }

  @Override
  public int getDegree() {
    Polynomial head = this;
    int res = 0;
    while (head instanceof PolynomialImpl) {
      if (((PolynomialImpl) head).degree > res) {
        res = ((PolynomialImpl) head).degree;
      } 
      head = ((PolynomialImpl) head).next;
    }
    return res;
  }
  
  @Override
  public String toString() {
    if (this.getDegree() == 0) {
      return String.format("%d", this.coef);
    }

    Polynomial head = this;
    HashMap<Integer, Integer> memo = new HashMap<>();
    while (head instanceof PolynomialImpl) {
      memo.put(((PolynomialImpl) head).degree, 
          memo.getOrDefault(((PolynomialImpl) head).degree, 0) 
          + ((PolynomialImpl) head).coef);
      head = ((PolynomialImpl) head).next;
    }

    List<Integer> keys = memo.keySet().stream().sorted().collect(Collectors.toList());
    StringBuffer res = new StringBuffer();
    for (int j = keys.size() - 1; j > -1; j--) {
      if (memo.get(keys.get(j)) == 0) {
        continue;
      }
      if (res.length() > 0) {
        res.append(" ");
        if (memo.get(keys.get(j)) > 0) {
          res.append("+");
        }
      }
      
      if (memo.get(keys.get(j)) == -1) {
        res.append("-");
      }
      
      if (keys.get(j) == 0 
          || Math.abs(memo.get(keys.get(j))) != 1) {
        res.append(String.format("%d", memo.get(keys.get(j))));
      }
      
      if (keys.get(j) > 0) {
        res.append(String.format("x^%d", keys.get(j)));
      }
    }

    return res.toString();
  }

}
