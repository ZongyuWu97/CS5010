package bst;

/**
 * This class represents a non-empty node in a binary search tree.

 * @author ZongyuWu
 * @param <T> Type of the data stored.
 */
public class GenericElementNode<T extends Comparable<T>> implements GenericTreeNode<T> {
  
  private T data;
  private GenericTreeNode<T> left;
  private GenericTreeNode<T> right;

  /**
   * Constructor of a non-empty node. It has data in this node, 
   * and have two chid, left and right.

   * @param data The data stored.
   * @param left Left child.
   * @param right Right child.
   */
  public GenericElementNode(T data, GenericTreeNode<T> left, GenericTreeNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }
  
  @Override
  public GenericTreeNode<T> add(T data) {
    if (this.data.compareTo(data) < 0) {
      this.right = this.right.add(data);
    }
    if (this.data.compareTo(data) > 0) {
      this.left = this.left.add(data);
    }
    return this;
  }

  @Override
  public int size() {
    int res = this.left.size() + this.right.size() + 1;
    return res;
  }

  @Override
  public int height() {
    int res = Math.max(this.left.height(), this.right.height()) + 1;
    return res;
  }

  @Override
  public boolean present(T data) {
    if (this.data.compareTo(data) == 0) {
      return true;
    }
    return (this.left.present(data) || this.right.present(data));
  }

  @Override
  public T minimum() {
    T tmp = this.left.minimum();
    if (tmp != null) {
      return tmp;
    }
    return this.data;
  }

  @Override
  public T maximum() {
    T tmp = this.right.maximum();
    if (tmp != null) {
      return tmp;
    }
    return this.data;
  }

  @Override
  public String preOrder() {
    String res = this.data.toString();
    String left = this.left.preOrder();
    String right = this.right.preOrder();
    
    if (!(left.isEmpty())) {
      res += " " + left;
    }
    if (!(right.isEmpty())) {
      res += " " + right;
    }
    return res;
  }

  @Override
  public String inOrder() {
    String res = this.data.toString();
    String left = this.left.inOrder();
    String right = this.right.inOrder();
    
    if (!(left.isEmpty())) {
      res = left + " " + res;
    }
    if (!(right.isEmpty())) {
      res += " " + right;
    }
    return res;
  }

  @Override
  public String postOrder() {
    String res = this.data.toString();
    String left = this.left.postOrder();
    String right = this.right.postOrder();
    
    if (!(right.isEmpty())) {
      res = right + " " + res;
    }
    if (!(left.isEmpty())) {
      res = left + " " + res;
    }
    return res;
  }

}
