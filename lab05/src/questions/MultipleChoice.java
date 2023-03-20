package questions;


/**
 * Represents the Multiple Cholice type question.

 * @author ZongyuWu
 *
 */
public class MultipleChoice implements Question {
  
  private final String text;
  private final String correctAnswers;
  private final String[] options;
  
  /**
   * Constructor of a MultipleChoice question.

   * @param text Text of this instance.
   * @param correctAnswer The correct answer.
   * @param options The options.
   */
  public MultipleChoice(String text, String correctAnswer, String ... options) {
    this.options = options;
    if (this.options.length < 3 || this.options.length > 8) {
      throw new IllegalArgumentException("Number of options should be between 3 and 8.");
    }
    
    this.text = text;
    this.correctAnswers = correctAnswer;
  }

  @Override
  public int compareTo(Question o) {
    if (o instanceof MultipleChoice) {
      return this.getText().compareTo(o.getText());
    } else if (o instanceof MultipleSelect || o instanceof Likert) {
      return -1;
    } else {
      return 1;
    }
  }

  @Override
  public String answer(String answer) {
    if (!this.correctAnswers.equals(answer)) {
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
    if (!(o instanceof MultipleChoice)) {
      return false;
    }
    if (this == o) {
      return true;
    }
    
    return this.hashCode() == o.hashCode();
  }

}
