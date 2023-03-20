package polynomial;

/**
 * This interface represents all the operations offered by a polynomial. A
 * polynomial is defined here as a function of one variable. The polynomial is a
 * weighted sum of terms (the weights are numeric). Each term may either be an
 * integer power of that variable, or some function in that variable, but never
 * both (i.e. (log x)^2 is not allowed).
 */

public interface Polynomial {
  /**
   * Add this polynomial to another and return the result as another polynomial.
   *
   * @param other the other polynomial to be added
   * @return the resulting polynomial
   * @throws IllegalArgumentException if parameter is not the same concrete type
   *                                  as the current object.
   */
  Polynomial add(Polynomial other) throws IllegalArgumentException;

  /**
   * Add a term to this polynomial with the specified coefficient and power.
   *
   * @param coefficient the coefficient of the term to be added
   * @param power       the power of the term to be added
   * @throws IllegalArgumentException if the power is negative
   */
  void addTerm(int coefficient, int power) throws IllegalArgumentException;

  /**
   * Determines if this polynomial is the same as the parameter polynomial.

   * @param poly the polynomial to use
   * @return true if this polynomial is of the same concrete type and has the same
   *         terms as the parameter, false otherwise
   */
  boolean isSame(Polynomial poly);

  /**
   * Evaluate the value of this polynomial at the given value of the variable.
   *
   * @param x the value at which the polynomial is to be evaluated.
   * @return the value of the polynomial at x
   */
  double evaluate(double x);

  /**
   * Return the coefficient of the term with the given power.
   *
   * @param power the power whose coefficient is sought
   * @return the coefficient at the given power
   */
  int getCoefficient(int power);

  /**
   * Get the degree of this polynomial.
   *
   * @return the degree of this polynomial as a whole number
   */
  int getDegree();
}
