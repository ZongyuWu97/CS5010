package util;

/**
 * Helper functions.

 * @author ZongyuWu
 *
 */
public class Helper {
  
  /**
   * Check if the string is int string.

   * @param str string
   * @return yes or not
   */
  public static boolean isInt(String str) {
    for (int i = 0; i < str.length(); i++) {
      if (!Character.isDigit(str.charAt(i))) {
        return false;
      }
    }
    return true;
  }
  
  /**
   * Check if string s is in string strs.

   * @param strs string
   * @param s string
   * @return yes or not
   */
  public static boolean isIn(String[] strs, String s) {
    for (String str : strs) {
      if (str.equals(s)) {
        return true;
      }
    }
    return false;
  }
}
