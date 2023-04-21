package util;

/**
 * Helper functions.

 * @author ZongyuWu
 *
 */
public class Helper {
  public static boolean isInt(String str) {
    for (int i = 0; i < str.length(); i++) {
      if (!Character.isDigit(str.charAt(i))) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean isIn(String[] strs, String s) {
    for (String str : strs) {
      if (str.equals(s)) {
        return true;
      }
    }
    return false;
  }
}
