package sentence;

/**
 * Represents the end of a sentence.

 * @author ZongyuWu
 *
 */
public class EmptyNode implements Sentence {

  @Override
  public int getNumberOfWords() {
    return 0;
  }

  @Override
  public String longestWord() {
    return "";
  }

  @Override
  public Sentence merge(Sentence other) {
    return other;
  }

  @Override
  public Sentence duplicate() {
    return new EmptyNode();
  }

  @Override
  public int countHelp(int acc) {
    return acc;
  }
  
  @Override
  public String toString() {
    return "";
  }
  
  @Override
  public int hashCode() {
    return "".hashCode();
  }
  
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof EmptyNode)) {
      return false;
    } else if (this == o) {
      return true;
    } else {
      return this.hashCode() == o.hashCode();
    }
  }
}
