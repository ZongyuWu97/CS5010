import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import org.junit.Test;
import questions.Likert;
import questions.MultipleChoice;
import questions.MultipleSelect;
import questions.Question;
import questions.TrueFalse;

/**
 * Test the ordering of different classes.

 * @author ZongyuWu
 *
 */
public class OrderingTest {
  
  @Test
  public void testOrdering() {
    Question likert = new Likert("Likert");
    Question mc = new MultipleChoice("MultiChoice", "1", "1", "2", "3");
    Question ms = new MultipleSelect("MultiSelect", "1 3", "1", "2", "3");
    Question tf = new TrueFalse("TrueFalse", "True");

    Question[] q = {likert, mc, ms, tf};
    Question[] qsort = {tf, mc, ms, likert};
    Arrays.sort(q);
    
    assertTrue(qsort.equals(q));
  }

}
