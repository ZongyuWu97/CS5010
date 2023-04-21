package document;

import document.elements.TextElement;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a document. It contains a list of the elements of the
 * document in the order that they appear in the document. This class is
 * provided as a starting point for the Visitor lab in CS 5010.
 */
public class Document {

  private List<TextElement> content;

  /** Default constructor initializes the fields of the class. */
  public Document() {
    content = new ArrayList<>();
  }

  /**
   * Add an element to the document. Elements are added in order.

   * @param e the element to add
   */
  public void add(TextElement e) {
    content.add(e);
  }

  /**
   * Count number of words in the document.

   * @return number of words
   */
  public int countWords() {
    TextElementVisitor<Integer> v = new WordCountVisitor();
    int total = 0;
    for (TextElement e : content) {
      total += (e.accept(v)).intValue();
    }
    return total;
  }

  /**
   * Convert to text.

   * @param v visitor type
   * @return a text string
   */
  public String toText(TextElementVisitor<String> v) {
    String res = "";
    for (TextElement e : content) {
      res += e.accept(v) + " ";
    }
    if ("".equals(res)) {
      return "";
    }
    return res.substring(0, res.length() - 1);
  }
}
