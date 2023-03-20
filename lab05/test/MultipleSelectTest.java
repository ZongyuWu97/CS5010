

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import questions.MultipleSelect;
import questions.Question;

/**
 * Test the MultipleSelect class.

 * @author ZongyuWu
 *
 */
public class MultipleSelectTest {

  private Question ms;
  
  @Before
  public void setUp() {
    this.ms = new MultipleSelect("text", "1 3", "1", "2", "3");
  }

  @Test
  public void testCorrect() {
    assertEquals(Question.CORRECT, ms.answer("1 3"));
  }

  @Test
  public void testInCorrect() {
    assertEquals(Question.INCORRECT, ms.answer("2"));
  }


}
