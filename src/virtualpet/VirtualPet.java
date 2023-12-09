package virtualpet;

import java.util.HashMap;
import java.util.TimerTask;

/**
 * This interface represents a virtual pet game. A player can interact with the pet by feeding it,
 * playing with it, cleaning it, and putting it to sleep. The pet has a name, age, mood state,
 * health state, life stage, and need levels. The player must interact with the pet to maintain its
 * needs and keep it happy and healthy. If a need drops below 75, its mood changes accordingly to
 * display its desired interaction. If more than one need drops below 25, the pet becomes sad. The
 * pet's health is calculated as the average between all its needs and if it drops below 50, the pet
 * becomes ill. If a need hits 0, the pet dies and the game ends. If the pet is healthy and happy,
 * then when the appropriate amount of time has passed, the pet grows and enters a new life stage.
 * The pet's life stage determines how needs are increasing and decreasing, and needs decrease
 * over time.
 */
public interface VirtualPet {

  /**
   * Set the name of the pet.
   *
   * @param name  the name of the pet.
   */
  void setName(String name);

  /**
   * Get the name of the pet.
   *
   * @return the name of the pet.
   */
  String getName();

  /**
   * Get the pet's age, calculated by the time elapsed since the pet was created. A minute
   * correlates to a "year" in the pet's life.
   *
   * @return the age of the pet.
   */
  long getAge();

  /**
   * Get the pet's current mood.
   *
   * @return the mood of the pet.
   */
  Mood getMoodState();

  /**
   * Get the pet's current health state.
   *
   * @return the current health state of the pet.
   */
  Health getHealthState();

  /**
   * Get the pet's current health level.
   *
   * @return the pet's current health level.
   */
  int getHealth();

  /**
   * Get the pet's current life stage.
   *
   * @return the current life stage of the pet.
   */
  PetLifeStage getLifeStage();

  /**
   * Get the pet's current need levels.
   *
   * @return the current need levels of the pet.
   */
  HashMap<Need, Integer> getNeedLevels();

  /**
   * Interact with the pet (feed, play, clean, or put to sleep). Throws an IllegalStateException
   * if the pet is dead and an IllegalArgumentException if the need is invalid or already full.
   *
   * @param need  the need corresponding with the desired interaction.
   */
  void interact(Need need) throws IllegalStateException, IllegalArgumentException;

  /**
   * Updates the mood, health, and life stage of the virtual pet.
   */
  void update();

  /**
   * Checks if the pet is alive.
   *
   * @return true if the pet is alive, false otherwise.
   */
  boolean isAlive();

  /**
   * Pauses the timed task, or resumes if already paused.
   */
  void pauseTimer();

  /**
   * Returns a string representation of the pet. Provides the pet's name, its current mood and
   * health states, and its need levels.
   *
   * @return a string representation of the pet.
  */
  String toString();

}
