import static org.junit.Assert.assertEquals;

import assignments.AlphabeticalSchedulingStrategy;
import assignments.AssignedSchedulingStrategy;
import assignments.Assignment;
import assignments.AssignmentList;
import assignments.DeadlineSchedulingStrategy;
import assignments.DifficultySchedulingStrategy;
import assignments.SchedulingStrategy;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

/** Class that tests the tasks. */
public class AssignmentTest { 
  
  private AssignmentList list;
  private Assignment cs5001;
  private Assignment cs5002;
  private Assignment cs5003;
  private Assignment cs5004;
  private SchedulingStrategy strategy;
  
  /**
   * Setup.
   */
  @Before
  public void setUp() {
    list = new AssignmentList();
    cs5001 = new Assignment("cs5001 hw1");
    cs5002 = new Assignment("cs5002 hw3");
    cs5003 = new Assignment("cs5003 lab2");
    cs5004 = new Assignment("cs5004 hw3");
    list.add(cs5001);
    list.add(cs5002);
    list.add(cs5003);
    list.add(cs5004);
    
    cs5001.setStart(1, 10, 2023);
    cs5001.setDeadline(1, 31, 2023);
    
    cs5002.setStart(2, 15, 2023);
    cs5002.setDeadline(3, 7, 2023);

    cs5003.setStart(1, 30, 2023);
    cs5003.setDeadline(2, 2, 2023);

    cs5004.setStart(2, 15, 2023);
    cs5004.setDeadline(3, 5, 2023);

  }

  /** Testing constructor and toString(). */
  @Test
  public void testConstructor() {
    LocalDate now = LocalDate.now();
    System.out.println(now);
    Assignment t1 = new Assignment("task 1");
    assertEquals("task 1, starting " + now + ", ending " + now, t1.toString());
    Assignment t2 = new Assignment("task 2");
    t2.setDeadline(3, 4, 2025);
    assertEquals("task 2, starting " + now + ", ending 2025-03-04", t2.toString());
  }
  
  @Test
  public void testAlphabetScheduling() {
    strategy = new AlphabeticalSchedulingStrategy();
    
    list.scheduleAssignments(strategy);
    String expected = "Ordered by alphabetical\n"
            + "1 -- cs5001 hw1, starting 2023-01-10, ending 2023-01-31\n"
            + "2 -- cs5002 hw3, starting 2023-02-15, ending 2023-03-07\n"
            + "3 -- cs5003 lab2, starting 2023-01-30, ending 2023-02-02\n"
            + "4 -- cs5004 hw3, starting 2023-02-15, ending 2023-03-05\n";
    assertEquals(expected, list.toString());
    
    list.scheduleAssignments(new DifficultySchedulingStrategy());
    list.scheduleAssignments(strategy);
    assertEquals(expected, list.toString());
  }
  
  @Test
  public void testAssignedScheduling() {
    strategy = new AssignedSchedulingStrategy();
    
    list.scheduleAssignments(strategy);
    String expected = "Ordered by assigned\n"
            + "1 -- cs5001 hw1, starting 2023-01-10, ending 2023-01-31\n"
            + "2 -- cs5002 hw3, starting 2023-02-15, ending 2023-03-07\n"
            + "3 -- cs5003 lab2, starting 2023-01-30, ending 2023-02-02\n"
            + "4 -- cs5004 hw3, starting 2023-02-15, ending 2023-03-05\n";
    assertEquals(expected, list.toString());
    
    list.scheduleAssignments(new DifficultySchedulingStrategy());
    list.scheduleAssignments(strategy);
    assertEquals(expected, list.toString());
  }
  
  @Test
  public void testDeadlineScheduling() {
    strategy = new DeadlineSchedulingStrategy();
    
    list.scheduleAssignments(strategy);
    String expected = "Ordered by deadline\n"
            + "1 -- cs5001 hw1, starting 2023-01-10, ending 2023-01-31\n"
            + "2 -- cs5003 lab2, starting 2023-01-30, ending 2023-02-02\n"
            + "3 -- cs5004 hw3, starting 2023-02-15, ending 2023-03-05\n"
            + "4 -- cs5002 hw3, starting 2023-02-15, ending 2023-03-07\n";
    assertEquals(expected, list.toString());
    
    list.scheduleAssignments(new DifficultySchedulingStrategy());
    list.scheduleAssignments(strategy);
    assertEquals(expected, list.toString());
  }
  
  @Test
  public void testDifficultyScheduling() {
    strategy = new DifficultySchedulingStrategy();
    
    list.scheduleAssignments(strategy);
    String expected = "Ordered by difficulty\n"
        + "1 -- cs5001 hw1, starting 2023-01-10, ending 2023-01-31\n"
        + "2 -- cs5002 hw3, starting 2023-02-15, ending 2023-03-07\n"
        + "3 -- cs5004 hw3, starting 2023-02-15, ending 2023-03-05\n"
        + "4 -- cs5003 lab2, starting 2023-01-30, ending 2023-02-02\n";

    assertEquals(expected, list.toString());
    
    list.scheduleAssignments(new AssignedSchedulingStrategy());
    list.scheduleAssignments(strategy);
    assertEquals(expected, list.toString());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalid() {
    strategy = new DifficultySchedulingStrategy();
    strategy.schedule(null);
  }
}
