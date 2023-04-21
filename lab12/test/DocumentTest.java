import static org.junit.Assert.assertEquals;

import document.BasicStringVisitor;
import document.Document;
import document.HtmlStringVisitor;
import document.MarkdownStringVisitor;
import document.TextElementVisitor;
import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the document class.

 * @author ZongyuWu
 *
 */
public class DocumentTest {
  
  private BasicText basic;
  private BoldText bold;
  private Heading heading1;
  private Heading heading2;
  private HyperText hyper;
  private ItalicText italic;
  private Paragraph paragraph;
  private Document document;
  
  /**
   * Set up some text.
   */
  @Before
  public void setUp() {
    basic = new BasicText("Basic example.");
    bold = new BoldText("Bold example.");
    heading1 = new Heading("Heading example.", 1);
    heading2 = new Heading("Another heading.", 2);
    hyper = new HyperText("Hyper example.", "northeaster.edu");
    italic = new ItalicText("Italic example.");
    
    paragraph = new Paragraph();
    paragraph.add(basic);
    paragraph.add(bold);
    paragraph.add(heading1);
    paragraph.add(heading2);
    paragraph.add(hyper);
    paragraph.add(italic);
    
    document = new Document();
    
  }

  @Test
  public void testWordCountVisitor() {
    document.add(basic);
    assertEquals(2, document.countWords());
    document.add(bold);
    assertEquals(4, document.countWords());
    document.add(heading1);
    assertEquals(6, document.countWords());
    document.add(heading2);
    assertEquals(8, document.countWords());
    document.add(hyper);
    assertEquals(10, document.countWords());
    document.add(italic);
    assertEquals(12, document.countWords());
    document.add(paragraph);
    assertEquals(24, document.countWords());
  }
  
  @Test
  public void testTextElementVisitor() {
    String expected = "";
    TextElementVisitor<String> v = new BasicStringVisitor();
    
    document.add(basic);
    expected += basic.getText();
    assertEquals(expected, document.toText(v));
    
    document.add(bold);
    expected += " " + bold.getText();
    assertEquals(expected, document.toText(v));

    document.add(heading1);
    expected += " " + heading1.getText();
    assertEquals(expected, document.toText(v));

    document.add(heading2);
    expected += " " + heading2.getText();
    assertEquals(expected, document.toText(v));

    document.add(hyper);
    expected += " " + hyper.getText();
    assertEquals(expected, document.toText(v));
    
    document.add(italic);
    expected += " " + italic.getText();
    assertEquals(expected, document.toText(v));

    document.add(paragraph);
    expected += " " + paragraph.getText();
    assertEquals(expected, document.toText(v));

  }
  
  @Test
  public void testHtmlVisitor() {
    String expected = "";
    TextElementVisitor<String> v = new HtmlStringVisitor();
    
    document.add(basic);
    expected += basic.getText() + "\n";
    assertEquals(expected, document.toText(v));
    
    document.add(bold);
    expected += " <b>" + bold.getText() + "</b>\n";
    assertEquals(expected, document.toText(v));

    document.add(heading1);
    expected += " <h1>" + heading1.getText() + "</h1>\n";
    assertEquals(expected, document.toText(v));

    document.add(heading2);
    expected += " <h2>" + heading2.getText() + "</h2>\n";
    assertEquals(expected, document.toText(v));

    document.add(hyper);
    expected += " <a href=" + hyper.getUrl() + ">" + hyper.getText() + "</a>\n";
    assertEquals(expected, document.toText(v));

    document.add(italic);
    expected += " <i>" + italic.getText() + "</i>\n";
    assertEquals(expected, document.toText(v));

    document.add(paragraph);
    expected += " <p>" + expected + "</p>\n";
    assertEquals(expected, document.toText(v));

  }
  
  @Test
  public void testMarkDownVisitor() {
    String expected = "";
    TextElementVisitor<String> v = new MarkdownStringVisitor();
    
    document.add(basic);
    expected += basic.getText() + "\n";
    assertEquals(expected, document.toText(v));
    
    document.add(bold);
    expected += " **" + bold.getText() + "**\n";
    assertEquals(expected, document.toText(v));

    document.add(heading1);
    expected += " #";
    expected += heading1.getText().trim() + "\n";
    assertEquals(expected, document.toText(v));

    document.add(heading2);
    expected += " ##" + heading2.getText() + "\n";
    assertEquals(expected, document.toText(v));

    document.add(hyper);
    expected += " [" + hyper.getText() + "](" + hyper.getUrl() + ")\n";
    assertEquals(expected, document.toText(v));

    document.add(italic);
    expected += " *" + italic.getText() + "*\n";
    assertEquals(expected, document.toText(v));

    document.add(paragraph);
    expected += " " + expected + "\n";
    assertEquals(expected, document.toText(v));

  }

}
