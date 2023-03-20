package bst;

/**
 * This class represents an empty node in a binary search tree.

 * @author ZongyuWu
 * @param <T> Type of the data stored.
 */
public class GenericEmptyNode<T extends Comparable<T>> implements GenericTreeNode<T> {

  @Override
  public GenericTreeNode<T> add(T data) {
    return new GenericElementNode<T>(data, this, this);
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public int height() {
    return 0;
  }

  @Override
  public boolean present(T data) {
    return false;
  }

  @Override
  public T minimum() {
    return null;
  }

  @Override
  public T maximum() {
    return null;
  }

  @Override
  public String preOrder() {
    return "";
  }

  @Override
  public String inOrder() {
    return "";
  }

  @Override
  public String postOrder() {
    return "";
  }

}
