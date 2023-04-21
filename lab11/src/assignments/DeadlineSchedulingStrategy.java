package assignments;

import java.util.Comparator;
import java.util.List;

/**
 * Schedule by deadline.

 * @author ZongyuWu
 *
 */
public class DeadlineSchedulingStrategy implements SchedulingStrategy {

  @Override
  public String schedule(List<Assignment> assignments) {
    if (assignments == null) {
      throw new IllegalArgumentException("Null assignments.");
    }
    assignments.sort(Comparator.comparing(Assignment::getEndDate));
    return "deadline";
  }

}
