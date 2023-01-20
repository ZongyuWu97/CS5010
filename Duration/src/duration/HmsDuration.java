package duration;

/**
 * Durations represented as hours, minutes, and seconds.
 */
public final class HmsDuration implements Duration {
  private final int hours;
  private final int minutes;
  private final int seconds;

  /**
   * Constructs a duration in terms of its length in hours, minutes, and
   * seconds.
   *
   * @param hours   the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @throws IllegalArgumentException if any argument is negative
   */
  public HmsDuration(int hours, int minutes, int seconds)
      throws IllegalArgumentException {
    if ((hours < 0) || (minutes < 0) || (seconds < 0)) {
      throw new IllegalArgumentException(
          "Negative durations are not supported");
    }
    int h = hours;
    int m = minutes;
    int s = seconds;
    m = m + s / 60; // carry extra seconds
    s = s % 60;
    h = h + m / 60; // carry extra minutes
    m = m % 60;

    this.hours = h;
    this.minutes = m;
    this.seconds = s;
  }

  /**
   * Constructs a duration in terms of its length in seconds.
   *
   * @param inSeconds the number of seconds (non-negative)
   * @throws IllegalArgumentException {@code inSeconds} is negative
   * @throws ArithmeticException {@code inSeconds} exceeds maximum duration allowed
   */
  public HmsDuration(long inSeconds) {
    if (inSeconds < 0) {
      throw new IllegalArgumentException("Negative duration are not supported");
    }
    if (inSeconds / 3600 > Integer.MAX_VALUE) {
      throw new ArithmeticException("Exceed maximum duration allowed");
    }
    this.hours = (int) (inSeconds / 3600);
    this.minutes = (int) (inSeconds / 60 % 60);
    this.seconds = (int) (inSeconds % 60);
  }

  @Override
  public long inSeconds() {
    return 3600L * hours + 60 * minutes + seconds;
  }
  
  @Override
  public String asHms() {
    return String.format("%d:%02d:%02d", this.hours, this.minutes, this.seconds);
  }
  
  @Override
  public Duration plus(Duration other) {
    long thisSeconds = this.inSeconds();
    long otherSeconds = other.inSeconds();
    long total = thisSeconds + otherSeconds;
    return new HmsDuration(total);
  }
  
  @Override
  public int compareTo(Duration that) {
    return Long.compare(this.inSeconds(), that.inSeconds());
  }

  @Override
  public boolean equals(Object o) {
    // Fast path for pointer equality:
    if (this == o) { // backward compatibility with default equals
      return true;
    }

    // If o isn't the right class then it can't be equal:
    if (!(o instanceof Duration)) {
      return false;
    }

    // The successful instanceof check means our cast will succeed:
    Duration that = (Duration) o;

    return this.inSeconds() == that.inSeconds();
  }

  @Override
  public int hashCode() {
    return Long.hashCode(this.inSeconds());
  } 
}
