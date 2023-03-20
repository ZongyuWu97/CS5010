

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import questions.MultipleChoice;
import questions.Question;

/**
 * Test the Multiple Choice class.

 * @author ZongyuWu
 *
 */
public class MultipleChoiceTest {

  private Question mc;
  
  @Before
  public void setUp() {
    this.mc = new MultipleChoice("text", "1", "1", "2", "3");
  }

  @Test
  public void testCorrect() {
    assertEquals(Question.CORRECT, mc.answer("1"));
  }

  @Test
  public void testInCorrect() {
    assertEquals(Question.INCORRECT, mc.answer("2"));
  }

}
