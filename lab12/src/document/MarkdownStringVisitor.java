package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;
import document.elements.TextElement;

/**
 * Transfer the text into mark down format.

 * @author ZongyuWu
 *
 */
public class MarkdownStringVisitor implements TextElementVisitor<String> {

  @Override
  public String visitBasicText(BasicText text) {
    return text.getText() + "\n";
  }

  @Override
  public String visitBoldText(BoldText text) {
    return "**" + text.getText() + "**\n";
  }

  @Override
  public String visitHeading(Heading text) {
    String res = "";
    for (int i = 0; i < text.getLevel(); i++) {
      res += "#";
    }
    res += text.getText() + "\n";
    return res;
  }

  @Override
  public String visitHyperText(HyperText text) {
    return "[" + text.getText() + "](" + text.getUrl() + ")\n";
  }

  @Override
  public String visitItalicText(ItalicText text) {
    return "*" + text.getText() + "*\n";
  }

  @Override
  public String visitParagraph(Paragraph text) {
    String res = "";
    for (TextElement e : text.getContent()) {
      res += e.accept(this) + " ";
    }
    return res.substring(0, res.length() - 1) + "\n";
  }
  

}
