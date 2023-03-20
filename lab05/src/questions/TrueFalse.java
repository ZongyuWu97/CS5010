package questions;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Represents the True/False type question.

 * @author ZongyuWu
 *
 */
public class TrueFalse implements Question {

  private static final HashSet<String> ANSWERS = 
      new HashSet<>(Arrays.asList("True", "False"));
  private final String text;
  private final String correctAnswer;

  /**
   * Constructor of a True/False question.

   * @param text The text of an instance.
   * @param correctAnswer The correct answer.
   */
  public TrueFalse(String text, String correctAnswer) {
    if (!ANSWERS.contains(correctAnswer)) {
      throw new IllegalArgumentException("Correct answer must be either True of False");
    }
    
    this.text = text;
    this.correctAnswer = correctAnswer;
  }
  
  @Override
  public int compareTo(Question o) {
    if (o instanceof TrueFalse) {
      return this.getText().compareTo(o.getText());
    } else {
      return -1;
    }
  }

  @Override
  public String answer(String answer) {
    if (answer.equals(this.correctAnswer)) {
      return CORRECT;
    } else {
      return INCORRECT;
    }
  }

  @Override
  public String getText() {
    return this.text;
  }
  
  @Override
  public int hashCode() {
    return this.text.hashCode() + this.correctAnswer.hashCode();
  }
  
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof TrueFalse)) {
      return false;
    }
    if (this == o) {
      return true;
    }
    
    return this.hashCode() == o.hashCode();
  }

}
