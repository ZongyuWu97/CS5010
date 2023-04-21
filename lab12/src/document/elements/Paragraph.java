package document.elements;

import document.TextElementVisitor;
import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a paragraph in a document. This element keep tracks of all
 * of the pieces of text that make up the paragraph.
 */
public class Paragraph implements TextElement {

  /** The elements that make up this paragraph. */
  private List<BasicText> content;

  /** Creates an empty paragraph. */
  public Paragraph() {
    content = new ArrayList<>();
  }

  /**
   * Add an element of text to this paragraph.

   * @param part the element to add.
   */
  public void add(BasicText part) {
    content.add(part);
  }

  public List<BasicText> getContent() {
    return content;
  }

  @Override
  public String getText() {
    String result = "";
    for (BasicText element : content) {
      result += element.getText() + " ";
    }
    return result.trim();
  }

  @Override
  public <R> R accept(TextElementVisitor<R> visitor) {
    return visitor.visitParagraph(this);
  }
}
