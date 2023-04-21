package assignments;

import java.util.List;

/**
 * Defines scheduling strategy.

 * @author ZongyuWu
 *
 */
public interface SchedulingStrategy {

  String schedule(List<Assignment> assignments);
  
}
