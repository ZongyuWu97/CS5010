package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;

/**
 * Interface for all operations needed in a visitor.

 * @author ZongyuWu
 */
public interface TextElementVisitor<R> {
  
  /**
   * Visit basic text.

   * @param text The basic text
   * @return Whatever object is returned
   */
  R visitBasicText(BasicText text);

  /**
   * Visit bold text.

   * @param text The bold text
   * @return Whatever object is returned
   */
  R visitBoldText(BoldText text);
  
  /**
   * Visit heading.

   * @param text The heading
   * @return Whatever object is returned
   */
  R visitHeading(Heading text);
  
  /**
   * Visit hyper text.

   * @param text The hyper text
   * @return Whatever object is returned
   */
  R visitHyperText(HyperText text);
  
  /**
   * Visit italic text.

   * @param text The italic text
   * @return Whatever object is returned
   */
  R visitItalicText(ItalicText text);
  
  /**
   * Visit paragraph.

   * @param text The paragraph
   * @return Whatever object is returned
   */
  R visitParagraph(Paragraph text);
  
}
