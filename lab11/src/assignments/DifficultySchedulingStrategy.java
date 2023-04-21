package assignments;

import java.util.Comparator;
import java.util.List;

/**
 * Schedule by difficulty.

 * @author ZongyuWu
 *
 */
public class DifficultySchedulingStrategy implements SchedulingStrategy {

  @Override
  public String schedule(List<Assignment> assignments) {
    if (assignments == null) {
      throw new IllegalArgumentException("Null assignments.");
    }
    assignments.sort(Comparator.comparing(Assignment::getDifficulty).reversed());
    return "difficulty";
  }

}
