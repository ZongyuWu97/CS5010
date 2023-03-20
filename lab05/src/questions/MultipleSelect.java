package questions;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Represents the Multiple Select type question.

 * @author ZongyuWu
 *
 */
public class MultipleSelect implements Question {

  private final String text;
  private final HashSet<String> correctAnswers;
  private final String[] options;
  
  /**
   * Constructor of a MultipleSelect question.

   * @param text Text of this instance.
   * @param correctAnswers The correct answers.
   * @param options The options.
   */
  public MultipleSelect(String text, String correctAnswers, String ... options) {
    this.options = options;
    if (this.options.length < 3 || this.options.length > 8) {
      throw new IllegalArgumentException("Number of options should be between 3 and 8.");
    }
    
    this.correctAnswers = new HashSet<>(Arrays.asList(correctAnswers.split(" ", 0)));
    this.text = text;
  }
  
  @Override
  public int compareTo(Question o) {
    if (o instanceof MultipleSelect) {
      return this.getText().compareTo(o.getText());
    } else if (o instanceof Likert) {
      return -1;
    } else {
      return 1;
    }
  }

  @Override
  public String answer(String answer) {
    String[] answers = answer.split(" ", 0);
    for (String ans : answers) {
      if (!this.correctAnswers.contains(ans)) {
        return INCORRECT;
      }
    }
    if (answers.length != this.correctAnswers.size()) {
      return INCORRECT;
    }
    return CORRECT;
  }

  @Override
  public String getText() {
    return this.text;
  }
  
  @Override
  public int hashCode() {
    return this.text.hashCode() + this.correctAnswers.hashCode() + this.options.hashCode();
  }
  
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof MultipleSelect)) {
      return false;
    }
    if (this == o) {
      return true;
    }
    
    return this.hashCode() == o.hashCode();
  }

}
