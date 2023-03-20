import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import polynomial.EmptyImpl;
import polynomial.Polynomial;
import polynomial.PolynomialImpl;

/**
 * Test the PolynomialImpl class.

 * @author ZongyuWu
 *
 */
public class PolynomialImplTest {

  @Test
  public void testConstructor1() {
    assertEquals("0", new PolynomialImpl().toString());
    assertEquals("0", new EmptyImpl().toString());
    assertEquals("x^4 -x^3", new PolynomialImpl("x^4 -x^3").toString());
    assertEquals("7x^2 +1", new PolynomialImpl("3x^2 +4x^2 +1").toString());
    assertEquals("2", new PolynomialImpl("2").toString());
    assertEquals("0", new PolynomialImpl("").toString());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    new PolynomialImpl(-1, 0);
  }
  
  @Test
  public void testAdd() {
    Polynomial poly = new PolynomialImpl("x^2 +x^1");
    Polynomial poly1 = poly.add(new PolynomialImpl("x^4 -x^3"));
    
    assertEquals("x^4 -x^3 +x^2 +x^1", poly1.toString());
    
    Polynomial poly2 = (new PolynomialImpl("x^2 +x^1")).add(new PolynomialImpl("x^2 -x^1"));
    assertEquals("2x^2", poly2.toString());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testAddTerm1() {
    Polynomial poly = new PolynomialImpl();
    poly.addTerm(1, -1);
  }
  
  @Test
  public void testAddTerm2() {
    Polynomial poly = new PolynomialImpl(1, 2);
    poly.addTerm(2, 1);
    assertEquals("4x^1", poly.toString());
    poly.addTerm(3, 2);
    assertEquals("3x^2 +4x^1", poly.toString());
  }
  
  @Test
  public void testIsSame() {
    Polynomial poly = new PolynomialImpl("7x^2 +1");
    Polynomial poly1 = new PolynomialImpl("7x^2 +1");
    assertTrue(poly.isSame(poly1));
    Polynomial poly2 = new PolynomialImpl("4x^2 +3x^2 +1");
    Polynomial poly3 = new PolynomialImpl("5x^2 +2x^2 +1");
    assertTrue(poly2.isSame(poly3));
  }
  
  @Test
  public void testGetDegree() {
    Polynomial poly = new PolynomialImpl("7x^2 +1");
    assertEquals(2, poly.getDegree());
    Polynomial poly1 = new PolynomialImpl("1");
    assertEquals(0, poly1.getDegree());
  }
  
  @Test
  public void testGetCoefficient() {
    Polynomial poly = new PolynomialImpl("7x^2 +1");
    assertEquals(7, poly.getCoefficient(2));
    assertEquals(0, poly.getCoefficient(3));
  }
  
  @Test
  public void testEvaluate() {
    Polynomial poly = new PolynomialImpl("7x^2 +x^1");
    assertEquals(6, poly.evaluate(-1), 0.01);
    assertEquals(8, poly.evaluate(1), 0.01);
  }

}
