package duration;

/**
 * Durations, with a resolution of seconds. All durations are non-negative.
 * Different {@code Duration} implementations should work together, meaning
 * that:
 *
 * <ul>
 * <li>Two durations must be equal if they have the same number of seconds.
 * <li>The hash code of a duration is the result of calling
 * {@link Long#hashCode(long)} on its length in seconds.
 * </ul>
 */
public interface Duration extends Comparable<Duration> {
  /**
   * Gets the total duration in seconds.
   *
   * @return the number of seconds (non-negative)
   */
  long inSeconds();

  /**
   * Formats this duration in the form {@code H:MM:SS} where the hours and
   * minutes are both zero-padded to two digits, but the hours are not. The
   * duration should be in canonical form, meaning that both the minutes and the
   * seconds are less than 60.
   *
   * @return this duration formatted in hours, minutes, and seconds
   */
  String asHms();

  /**
   * Returns the sum of two durations.
   *
   * @param other the duration to add to {@code this}
   * @return the sum of the durations
   */
  Duration plus(Duration other);
}
