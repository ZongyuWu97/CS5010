package permutations;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Interface for a reverse iterator.
 *
 * @param <T> the type of element that is being iterated over.
 */
public interface BackAndForthIterator<T> extends Iterator<T> {
  /**
   * Returns the previous element in the sequence and moves the cursor position
   * backwards. This method may be called repeatedly to iterate backwards through
   * the sequence.

   * @return the previous element in the sequence
   * @throws NoSuchElementException if there is no previous element in the sequence
   */
  T previous() throws NoSuchElementException;

  /**
   * Returns true if this iterator has more elements when traversing the sequence
   * in the reverse direction.

   * @return true if the sequence has more elements when traversing the sequence
   *         in the reverse direction
   */
  boolean hasPrevious();
}
