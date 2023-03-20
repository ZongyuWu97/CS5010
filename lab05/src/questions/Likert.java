package questions;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Represents the Likert type question.

 * @author ZongyuWu
 *
 */
public class Likert implements Question {
  
  private static final HashSet<String> OPTIONS = 
      new HashSet<>(Arrays.asList("1", "2", "3", "4", "5")); 
  
  private final String text;
  
  public Likert(String text) {
    this.text = text;
  }

  @Override
  public int compareTo(Question o) {
    // TODO Auto-generated method stub
    if (o instanceof Likert) {
      return this.getText().compareTo(o.getText());
    } else {
      return 1;
    }
  }

  @Override
  public String answer(String answer) {
    if (OPTIONS.contains(answer)) {
      return CORRECT;
    }
    return INCORRECT;
  }

  @Override
  public String getText() {
    return this.text;
  }
  
  @Override
  public int hashCode() {
    return this.text.hashCode();
  }
  
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Likert)) {
      return false;
    }
    if (this == o) {
      return true;
    }
    
    return this.hashCode() == o.hashCode();
  }

}
