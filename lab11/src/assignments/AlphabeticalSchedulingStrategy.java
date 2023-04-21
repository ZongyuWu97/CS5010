package assignments;

import java.util.Comparator;
import java.util.List;

/**
 * Schedule by alphabetic order.

 * @author ZongyuWu
 *
 */
public class AlphabeticalSchedulingStrategy implements SchedulingStrategy {

  @Override
  public String schedule(List<Assignment> assignments) {
    if (assignments == null) {
      throw new IllegalArgumentException("Null assignments.");
    }
    assignments.sort(Comparator.comparing(Assignment::toString));
    return "alphabetical";
  }

}
