package document.elements;

import document.TextElementVisitor;

/**
 * A representation of bold text in a document.
 */
public class BoldText extends BasicText {

  /**
   * Creates text that is bolded.

   * @param text the text contained in this element
   */
  public BoldText(String text) {
    super(text);
  }
  
  @Override
  public <R> R accept(TextElementVisitor<R> visitor) {
    return visitor.visitBoldText(this);
  }
}
 