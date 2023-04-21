package document.elements;

import document.TextElementVisitor;

/**
 * Representation for any of the text elements of a document.
 */
public interface TextElement {

  /**
   * Returns the text of the element.

   * @return the text
   */
  public String getText();
  
  public <R> R accept(TextElementVisitor<R> visitor);
}
