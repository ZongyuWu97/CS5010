

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import questions.Question;
import questions.TrueFalse;

/**
 * Test the True of False class.
 */
public class TrueFalseTest {
  
  private Question tf;
  
  @Before
  public void setUp() {
    this.tf = new TrueFalse("text", "True");
  }

  @Test
  public void testCorrect() {
    assertEquals(Question.CORRECT, tf.answer("True"));
  }

  @Test
  public void testInCorrect() {
    assertEquals(Question.INCORRECT, tf.answer("False"));
  }
}
