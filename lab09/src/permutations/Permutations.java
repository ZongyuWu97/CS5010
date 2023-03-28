package permutations;

import java.util.NoSuchElementException;

/**
 * This class implements the interface BackAndForthIterator with type String.

 * @author ZongyuWu
 *
 */
public class Permutations implements BackAndForthIterator<String> {
  
  private final String base;
  private int currentLength;
  private int[] pointer;
  
  /**
   * Constructor with base string and start length.

   * @param base The string to work at
   * @param startLength start length
   * @throws IllegalArgumentException For invalid input
   */
  public Permutations(String base, int startLength) throws IllegalArgumentException {
    String alphabet = "[abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ]*";
    if (base == null || !base.matches(alphabet)) { 
      throw new IllegalArgumentException("Invalid string.");
    }
    if (startLength > base.length() || startLength < 1) {
      throw new IllegalArgumentException("Starting point of range.");
    }
    this.base = base;
    this.currentLength = startLength;
    this.pointer = new int[this.base.length()];
    for (int i = 0; i < this.pointer.length; i++) {
      this.pointer[i] = i;
    }
  }
  
  /**
   * Constructor that only takes the base string.

   * @param base The string to work at
   * @throws IllegalArgumentException For invalid input
   */
  public Permutations(String base) throws IllegalArgumentException {
    String alphabet = "[abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ]*";
    if (base == null || !base.matches(alphabet)) { 
      throw new IllegalArgumentException("Invalid string.");
    }
    this.base = base;  
    this.currentLength = 1;
    this.pointer = new int[this.base.length()];
    for (int i = 0; i < this.pointer.length; i++) {
      this.pointer[i] = i;
    }
  }

  @Override
  public boolean hasNext() {
    return this.currentLength <= this.base.length();
  }

  @Override
  public String next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException("No next available.");
    }
    
    String result = "";
    
    for (int i = 0; i < this.currentLength; i++) {
      result += this.base.charAt(this.pointer[i]);
    }
    
    this.changeNext();

    return result;
  }
  
  /**
   * Update the class to point to the next item.
   */
  private void changeNext() {
    int index = this.currentLength - 1;
    
    if (this.pointer[index] < this.base.length() - 1) {
      this.pointer[index]++;
    } else {
      boolean doneWithLength = true;
      int maxValue = this.base.length() - 1;
      for (int i = this.currentLength - 1; i >= 0; i--) {
        if (this.pointer[i] != maxValue) {
          doneWithLength = false;
        }
        maxValue--;
      }
      
      if (doneWithLength) {
        
        this.currentLength++;
        if (this.currentLength <= this.base.length()) {
          for (int i = 0; i < this.currentLength; i++) {
            this.pointer[i] = i;
          }
        }
      } else {
        int currInd = index;
        int endValue = this.base.length() - 1;
        while (currInd > 0) {
          if (this.pointer[currInd] == endValue) {
            currInd--;
            endValue--;
          } else {
            break;
          }
        }
        
        this.pointer[currInd]++;
        for (int i = currInd + 1; i <= index; i++) {
          this.pointer[i] = this.pointer[i - 1] + 1;
        }
      }
      
    }
  }

  @Override
  public String previous() throws NoSuchElementException {
    if (!this.hasPrevious()) {
      throw new NoSuchElementException("No previous available.");
    }
    String result = "";
    this.changePrev();

    for (int i = 0; i < this.currentLength; i++) {
      result += this.base.charAt(this.pointer[i]);
    }
    
    return result;
  }
  
  /**
   * Update the class to point to the previous item.
   */
  private void changePrev() {
    int index = this.currentLength - 1;
    
    if (this.pointer[index] > index) {
      this.pointer[index]--;
    } else {
      boolean doneWithLength = true;
      for (int i = this.currentLength - 1; i >= 0; i--) {
        if (this.pointer[i] != i) {
          doneWithLength = false;
        }
      }
      
      if (doneWithLength) {
        
        this.currentLength--;
        if (this.currentLength > 0) {
          for (int i = 0; i < this.currentLength; i++) {
            this.pointer[i] = this.base.length() - this.currentLength + i;
          }
        }
      } else {
        int currInd = 0;
        int endValue = 0;
        
        while (currInd < this.currentLength) {
          if (this.pointer[currInd] == endValue) {
            currInd++;
            endValue++;
          } else {
            break;
          }
        }
        
        this.pointer[currInd]--;
        for (int i = currInd + 1; i <= this.currentLength - 1; i++) {
          this.pointer[i] = this.base.length() - (this.currentLength - currInd) + i;
        }
      }
      
    }
    
  }

  @Override
  public boolean hasPrevious() {
    return (this.currentLength > 1) || (this.currentLength == 1 && this.pointer[0] > 0);
  }
  
  @Override
  public String toString() {
    return this.base + ", " + Integer.toString(this.currentLength);
  }

  
}
