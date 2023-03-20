package bst;

/**
 * This is the implementation of a binary search tree. Specifically it implements the
 * bst.BinarySearchTree interface.

 * @author ZongyuWu
 * @param <T> Type of the data stored.
 */
public class BinarySearchTreeImpl<T extends Comparable<T>> implements BinarySearchTree<T> {
  
  private GenericTreeNode<T> root;
  
  public BinarySearchTreeImpl() {
    this.root = new GenericEmptyNode<>();
  }

  @Override
  public void add(T data) {
    this.root = this.root.add(data);
  }

  @Override
  public int size() {
    return this.root.size();
  }

  @Override
  public int height() {
    return this.root.height();
  }

  @Override
  public boolean present(T data) {
    return this.root.present(data);
  }

  @Override
  public T minimum() {
    return this.root.minimum();
  }

  @Override
  public T maximum() {
    return this.root.maximum();
  }

  @Override
  public String preOrder() {
    return "[" + this.root.preOrder() + "]";
  }

  @Override
  public String inOrder() {
    return "[" + this.root.inOrder() + "]";
  }

  @Override
  public String postOrder() {
    return "[" + this.root.postOrder() + "]";
  }
  
  @Override
  public String toString() {
    return this.inOrder();
  }

}
