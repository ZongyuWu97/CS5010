package assignments;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of task that need to be completed.
 */
public class AssignmentList {

  private List<Assignment> tasks;
  private String ordering;

  /** Default constructor. */
  public AssignmentList() {
    tasks = new ArrayList<>();
    ordering = "assigned";
  }
  
  /**
   * Reorder based on strategy.

   * @param strategy strategy
   * @throws IllegalArgumentException for null input
   */
  public void scheduleAssignments(SchedulingStrategy strategy) throws IllegalArgumentException {
    if (strategy == null) {
      throw new IllegalArgumentException("Input is null.");
    }
    if (this.tasks == null) {
      throw new IllegalArgumentException("Task is null.");
    }
    this.ordering = strategy.schedule(this.tasks);
  }

  /**
   * Add a task to the task list.

   * @param t the task
   */
  public void add(Assignment t) {
    tasks.add(t);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Ordered by ");
    sb.append(ordering);
    sb.append("\n");
    for (int i = 0; i < tasks.size(); i++) {
      sb.append(i + 1);
      sb.append(" -- ");
      sb.append(tasks.get(i));
      sb.append("\n");
    }
    return sb.toString();
  }
}
