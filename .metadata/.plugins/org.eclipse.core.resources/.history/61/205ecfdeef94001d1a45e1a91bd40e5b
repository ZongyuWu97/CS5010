package weather;

/**
 * A WeatherReading represents the data that is collected at a variety of
 * weather stations. Classes that implement this interface will vary depending
 * upon the type of weather station that does the reading.
 */
public interface WeatherReading {

  // TODO: Look at the relative humidity calculation
  // - the formula has a lot of restrictions that we do not enforce
  // - IllegalArgumentException for results outside of 0-100% humidity

  /**
   * Get the temperature (in Celsius) of this reading rounded to the nearest
   * integer.
   *
   * @return the temperature
   */
  public int getTemperature();

  /**
   * Get the dew point (in Celsius) for this reading rounded to the nearest
   * integer.
   *
   * @return the dew point
   */
  public int getDewPoint();

  /**
   * Get the wind speed (in Celsius) for this reading rounded to the nearest
   * integer.
   *
   * @return the wind speed
   */
  public int getWindSpeed();

  /**
   * Get the total rain (in mm) of this reading.
   *
   * @return the total rain
   */
  public int getTotalRain();

  /**
   * Get the relative humidity (in percent) of this weather reading rounded to the
   * nearest integer.
   *
   * @return the relative humidity
   */
  public int getRelativeHumidity();

  /**
   * Get the heat index (in Celsius) for this weather reading rounded to the nearest integer.
   *
   * @return the heat index
   */
  public int getHeatIndex();

  /**
   * Get the wind chill (in Celsius) rounded to the nearest integer.
   *
   * @return the wind chill
   */
  public int getWindChill();

}
