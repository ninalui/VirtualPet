package virtualpet;

/**
 * This interface represents the life stage of a virtual pet. The life stage dictates the rate
 * at which a pet's need decreases based off time and value, and the rate at which a pet's
 * need increases based off value.
 */
public interface PetLifeStage {

  /**
   * The interval at which the pet's needs are decreasing, in milliseconds.
   */
  static long INTERVAL = 5000; // 5 seconds

  /**
   * Increases the pet's need level of the given need. Throws an exception if the current level
   * is greater than 100. Returns the new need level, or 100, whichever is less.
   *
   * @param need  the need to increase.
   * @param currentLevel  the current level of the need.
   * @return the new need level.
   * @throws IllegalArgumentException if the current level is greater than 100.
   */
  int increaseNeed(Need need, Integer currentLevel) throws IllegalArgumentException;

  /**
   * Decreases the pet's need level of the given need. Throws an exception if the current level
   * is less than 0. Returns the new need level, or 0, whichever is less.
   *
   * @param need  the need to decrease.
   * @param currentLevel  the current level of the need.
   * @return the new need level.
   * @throws IllegalArgumentException if the current level is less than 0.
   */
  int decreaseNeed(Need need, Integer currentLevel) throws IllegalArgumentException;

  /**
   * Gets the interval at which the pet's needs are decreasing.
   *
   * @return the interval at which the pet's needs are decreasing.
   */
  long getInterval();
}
