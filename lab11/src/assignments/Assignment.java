package assignments;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * A class that represents an assignment for class. Assignments are compared by
 * their descriptions.
 */
public class Assignment implements Comparable<Assignment> {

  private static int totalCount;

  private final int number;
  private final String description;
  private LocalDate start;
  private LocalDate end;

  /* Initializing the static variables in the static block. */
  static {
    totalCount = 0;
  }

  /**
   * Constructor for a task that is due now.

   * @param description the description
   * @throws IllegalArgumentException if the description is invalid.
   */
  public Assignment(String description) throws IllegalArgumentException {
    if (description == null || "".equals(description)) {
      throw new IllegalArgumentException("invalid description for an assignment");
    }
    this.description = description;
    this.start = LocalDate.now();
    this.end = LocalDate.now();
    this.number = Assignment.totalCount++;
  }

  /**
   * Getting the total number of assignments that have been created.

   * @return the total number of assignment
   */
  public static int getTotalCount() {
    return totalCount;
  }

  /**
   * Get the description of this assignment.

   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Get the starting date for this assignment.

   * @return the starting date
   */
  public LocalDate getStartDate() {
    return start;
  }

  /**
   * Get the ending date for this assignment.

   * @return the ending date
   */
  public LocalDate getEndDate() {
    return end;
  }

  /**
   * Get the number for this assignment.

   * @return the number
   */
  public int getNumber() {
    return number;
  }

  /**
   * Sets the start date.

   * @param month the month
   * @param day   the day
   * @param year  the year
   */
  public void setStart(int month, int day, int year) {
    LocalDate start = LocalDate.of(year, month, day);
    if (start.compareTo(end) > 0) {
      throw new IllegalArgumentException("Start date is after the deadline.");
    }
    this.start = start;
  }

  /**
   * Sets the due date.

   * @param month the month
   * @param day   the day
   * @param year  the year
   */
  public void setDeadline(int month, int day, int year) {
    LocalDate end = LocalDate.of(year, month, day);
    if (start.compareTo(end) > 0) {
      throw new IllegalArgumentException("Deadline is before assignment start date.");
    }
    this.end = end;
  }

  /**
   * Get the difficulty of this assignment as determined by the number of days
   * there to work on it.

   * @return the difficulty
   */
  public int getDifficulty() {
    return Period.between(start, end).getDays();
  }

  @Override
  public int compareTo(Assignment o) {
    return description.compareTo(o.description);
  }

  @Override
  public String toString() {
    return String.format("%s, starting %s, ending %s", this.description, this.getStartDate(),
        this.getEndDate());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Assignment)) {
      return false;
    }
    Assignment other = (Assignment) o;
    return this.description.equals(other.description) && this.start.equals(other.start)
        && this.end.equals(other.end);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.description, this.start, this.end);
  }
}
