package bst;

/**
 * This interface represents a generic node in a binary search tree. 

 * @author ZongyuWu
 * @param <T> Type of the data stored.
 */
public interface GenericTreeNode<T extends Comparable<T>> {

  /**
   * Add data to the binary search tree. This is ignored if the data item is
   * already present

   * @param data the data to be added
   * @return The root node after adding the data.
   */
  public GenericTreeNode<T> add(T data);

  /**
   * Return the size of the tree (i.e., the number of elements in this tree).

   * @return The number of elements in this tree
   */
  public int size();

  /**
   * Return the height of the tree..

   * @return the height of the tree
   */
  public int height();

  /**
   * Find if this data is present in the binary search tree.

   * @param data the data to be searched
   * @return true if the data is present, false otherwise
   */
  public boolean present(T data);

  /**
   * Determine and return the minimum data in the tree as defined by its ordering.

   * @return the minimum data if it exists, null otherwise
   */
  public T minimum();

  /**
   * Determine and return the maximum data in the tree as defined by its ordering.

   * @return the maximum data if it exists, null otherwise
   */
  public T maximum();

  /**
   * Returns a string that present all the data in the tree, sorted in ascending
   * order. The string is formatted as [d1 d2 ... dn]

   * @return a string containing the preorder traveral
   */
  public String preOrder();

  /**
   * Returns a string that present all the data in the tree in pre-order. sorted
   * in ascending order. The string is formatted as [d1 d2 ... dn]

   * @return a string containing the inorder traversal
   */
  public String inOrder();

  /**
   * Returns a string that present all the data in the tree, sorted in ascending
   * order. The string is formatted as [d1 d2 ... dn]

   * @return a string containing the postorder traversal
   */
  public String postOrder();
}
