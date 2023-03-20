package sentence;

/**
 * Represents a punctuation.

 * @author ZongyuWu
 *
 */
public class PunctuationNode extends AbstractSentence {

  public PunctuationNode(String content, Sentence next) {
    super(content, next);
  }
  
  public PunctuationNode(String content) {
    super(content);
  }
  
}
