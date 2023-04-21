package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;
import document.elements.TextElement;

/**
 * Transform the text into html format.

 * @author ZongyuWu
 *
 */
public class HtmlStringVisitor implements TextElementVisitor<String> {

  @Override
  public String visitBasicText(BasicText text) {
    return text.getText() + "\n";
  }

  @Override
  public String visitBoldText(BoldText text) {
    return "<b>" + text.getText() + "</b>\n";
  }

  @Override
  public String visitHeading(Heading text) {
    String res = "<h" + text.getLevel() + ">" + text.getText() + "</h" + text.getLevel() + ">\n";
    return res;
  }

  @Override
  public String visitHyperText(HyperText text) {
    return "<a href=" + text.getUrl() + ">" + text.getText() + "</a>\n";
  }

  @Override
  public String visitItalicText(ItalicText text) {
    return "<i>" + text.getText() + "</i>\n";
  }

  @Override
  public String visitParagraph(Paragraph text) {
    String res = "<p>";
    for (TextElement e : text.getContent()) {
      res += e.accept(this) + " ";
    }
    return res.substring(0, res.length() - 1) + "</p>\n";
  }

}
