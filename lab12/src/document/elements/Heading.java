package document.elements;

import document.TextElementVisitor;

/**
 * Class tha represents a heading in a document.
 */
public class Heading extends BasicText {

  /** What level of heading this is. */
  private final int level;

  /**
   * Creates header text.

   * @param text  the text contained in this element
   * @param level the level for this element
   */

  public Heading(String text, int level) {
    super(text);
    this.level = level;
  }

  /**
   * Accessor for the level.

   * @return the level
   */
  public int getLevel() {
    return level;
  }
  
  @Override
  public <R> R accept(TextElementVisitor<R> visitor) {
    return visitor.visitHeading(this);
  }
}
