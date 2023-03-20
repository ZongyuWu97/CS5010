import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import bst.BinarySearchTree;
import bst.BinarySearchTreeImpl;
import org.junit.Test;

/**
 * This is test for the BinarySearchTreeImpl class.

 * @author ZongyuWu
 *
 */
public class BinarySearchTreeImplTest {

  @Test
  public void testConstructor() {
    assertEquals("[]", new BinarySearchTreeImpl<>().toString());
  }
  
  @Test
  public void testAdd() {
    BinarySearchTree<Integer> tmp = new BinarySearchTreeImpl<>();
    tmp.add(1);
    tmp.add(2);

    assertEquals("[1 2]", tmp.toString());
    
    tmp.add(2);
    assertEquals("[1 2]", tmp.toString());
  }
  
  @Test
  public void testSize() {
    BinarySearchTree<Integer> tmp = new BinarySearchTreeImpl<>();
    assertEquals(0, tmp.size());
    tmp.add(1);
    assertEquals(1, tmp.size());
  }
  
  @Test
  public void testPresent() {
    BinarySearchTree<Integer> tmp = new BinarySearchTreeImpl<>();
    tmp.add(1);
    assertTrue(tmp.present(1));
    assertFalse(tmp.present(2));
  }

  @Test
  public void testMinimumMaximum() {
    BinarySearchTree<Integer> tmp = new BinarySearchTreeImpl<>();
    assertEquals(null, tmp.minimum());
    assertEquals(null, tmp.maximum());
    tmp.add(1);
    tmp.add(2);
    tmp.add(0);

    assertTrue(tmp.minimum().equals(0));
    assertTrue(tmp.maximum().equals(2));
  }
  
  @Test
  public void testOrdering() {
    BinarySearchTree<Integer> tmp = new BinarySearchTreeImpl<>();
    assertEquals("[]", tmp.preOrder());
    assertEquals("[]", tmp.inOrder());
    assertEquals("[]", tmp.postOrder());

    tmp.add(1);
    tmp.add(2);
    tmp.add(0);
    tmp.add(3);
    tmp.add(-1);
    assertEquals("[1 0 -1 2 3]", tmp.preOrder());
    assertEquals("[-1 0 1 2 3]", tmp.inOrder());
    assertEquals("[-1 0 3 2 1]", tmp.postOrder());
  }
  
  @Test
  public void testHeight() {
    BinarySearchTree<Integer> tmp = new BinarySearchTreeImpl<>();
    assertEquals(0, tmp.height());
    tmp.add(1);
    tmp.add(2);
    tmp.add(0);
    tmp.add(3);
    assertEquals(3, tmp.height());
  }

}
