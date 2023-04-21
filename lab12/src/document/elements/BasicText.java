package document.elements;

import document.TextElementVisitor;

/**
 * The default element of any document is text.
 */
public class BasicText implements TextElement {

  private String text;

  /**
   * Constructor for the document element.

   * @param text the content of the document element.
   */
  public BasicText(String text) {
    this.text = text;
  }

  @Override
  public String getText() {
    return text;
  }

  @Override
  public <R> R accept(TextElementVisitor<R> visitor) {
    return visitor.visitBasicText(this);
  }
}
