package sentence;

/**
 * Represents a word.

 * @author ZongyuWu
 *
 */
public class WordNode extends AbstractSentence {

  public WordNode(String content, Sentence next) {
    super(content, next);
  }
  
  public WordNode(String content) {
    super(content);
  }

}
