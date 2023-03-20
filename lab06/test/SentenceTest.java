

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import sentence.EmptyNode;
import sentence.PunctuationNode;
import sentence.Sentence;
import sentence.WordNode;

/**
 * Test behaviors of sentence. 

 * @author ZongyuWu
 *
 */
public class SentenceTest {
  
  private Sentence word;
  private Sentence punctuation;
  private Sentence sentence;
  private Sentence empty;
  

  /** 
   * Setup template for test.
   */
  @Before
  public void setUp() {
    word = new WordNode("word");
    punctuation = new PunctuationNode(",");
    sentence = new WordNode("This", 
        new PunctuationNode(",",
            new WordNode("is",
                new WordNode("a",
                    new WordNode("sentence",
                        new PunctuationNode(".")
                        )
                    )
                )
            )
        );
    empty = new EmptyNode();
  }

  @Test
  public void testGetNumberOfWords1() {
    assertEquals(4, sentence.getNumberOfWords());
  }
  
  @Test
  public void testGetNumberOfWords2() {
    assertEquals(0, empty.getNumberOfWords());
  }
  
  @Test
  public void testLongestWord1() {
    assertEquals("sentence", sentence.longestWord());
  }
  
  @Test
  public void testLongestWord2() {
    assertEquals("", empty.longestWord());
  }
  
  @Test
  public void testLongestWord3() {
    Sentence tmp = new WordNode("word", 
        new WordNode("abcd")
        );
    assertEquals("word", tmp.longestWord());
  }
  
  @Test
  public void testMerge1() {
    assertEquals("This, is a sentence. word", sentence.merge(word).toString());
  }
  
  @Test
  public void testMerge2() {
    assertEquals("This, is a sentence.", sentence.merge(empty).toString());
  }
  
  @Test
  public void testDuplicate1() {
    assertEquals("This, is a sentence.", sentence.duplicate().toString());
  }
  
  @Test
  public void testDuplicate2() {
    assertEquals("", empty.duplicate().toString());
  }
  
  @Test
  public void testDuplicate3() {
    assertEquals("word", word.duplicate().toString());
  }
  
  @Test
  public void testDuplicate4() {
    assertFalse(sentence.duplicate() == sentence);
  }

  @Test
  public void testToString1() {
    assertEquals("word", word.toString());
  }
  
  @Test
  public void testToString2() {
    assertEquals(",", punctuation.toString());
  }
  
  @Test
  public void testToString3() {
    assertEquals("This, is a sentence.", sentence.toString());
  }
  
  @Test
  public void testToString4() {
    assertEquals("", empty.toString());
  }
}
