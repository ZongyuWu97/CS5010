package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;

/**
 * Generate a simple text of the document.

 * @author ZongyuWu
 */
public class BasicStringVisitor implements TextElementVisitor<String> {

  @Override
  public String visitBasicText(BasicText text) {
    return text.getText();
  }

  @Override
  public String visitBoldText(BoldText text) {
    return this.visitBasicText(text);
  }

  @Override
  public String visitHeading(Heading text) {
    return this.visitBasicText(text);
  }

  @Override
  public String visitHyperText(HyperText text) {
    return this.visitBasicText(text);
  }

  @Override
  public String visitItalicText(ItalicText text) {
    return this.visitBasicText(text);
  }

  @Override
  public String visitParagraph(Paragraph text) {
    return this.visitBasicText(new BasicText(text.getText()));
  }

}
