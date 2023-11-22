package pet;

/**
 * This interface represents the life stage of a virtual pet. The life stage dictates the rate
 * at which a pet's need decreases and increases.
 */
public interface PetLifeStage {

  /**
   * Increases the pet's need level of the given need.
   *
   * @param need  the need to increase.
   * @param currentLevel  the current level of the need.
   * @return the new need level.
   * @throws IllegalArgumentException if the current level is greater than 100.
   */
  int increaseNeed(Need need, int currentLevel) throws IllegalArgumentException;

  /**
   * Decreases the pet's need level of the given need.
   *
   * @param need  the need to decrease.
   * @param currentLevel  the current level of the need.
   * @return the new need level.
   * @throws IllegalArgumentException if the current level is less than 0.
   */
  int decreaseNeed(Need need. int currentLevel) throws IllegalArgumentException;

  /**
   * Gets the interval at which the pet's needs are decreasing.
   *
   * @return the interval at which the pet's needs are decreasing.
   */
  long getInterval();
}
