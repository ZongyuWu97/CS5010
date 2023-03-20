package battle;

/**
 * Helper functions for classes of Question.

 * @author ZongyuWu
 *
 */
public class Helper {
  protected static boolean isInt(String str) {
    for (int i = 0; i < str.length(); i++) {
      if (!Character.isDigit(str.charAt(i))) {
        return false;
      }
    }
    return true;
  }
  
  protected static boolean isIn(String[] strs, String s) {
    for (String str : strs) {
      if (str.equals(s)) {
        return true;
      }
    }
    return false;
  }

}
