

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import questions.Likert;
import questions.Question;

/**
 * Test the Likert class.

 * @author ZongyuWu
 *
 */
public class LikertTest {

  private Question likert;
  
  @Before
  public void setUp() {
    this.likert = new Likert("text");
  }

  @Test
  public void testCorrect() {
    assertEquals(Question.CORRECT, likert.answer("3"));
  }

  @Test
  public void testInCorrect() {
    assertEquals(Question.INCORRECT, likert.answer("False"));
  }

}
