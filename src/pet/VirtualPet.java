package pet;

import java.util.HashMap;

/**
 * This interface represents a virtual pet game. A player can interact with the pet by feeding it,
 * playing with it, cleaning it, and putting it to sleep.
 *
 * The pet has a name, mood state, health state, life stage, and need levels.
 *
 * Need levels will decrease as time passes and the player must interact with the pet accordingly
 * to increase its need levels.
 *
 * The pet's mood will change dependent on its needs. If a need drops below 75, its mood changes
 * accordingly to display its desired interaction.
 *
 * The pet's health also changes dependent on its needs. If a need drops below 50, the pet becomes
 * ill, and if a need hits 0, the pet dies and the game ends.
 *
 * If the pet is healthy and happy, then when the appropriate amount of time has passed, the pet
 * grows and enters a new life stage.
 *
 * The pet's life stage determines how needs are increasing and decreasing.
 *
 */
public interface VirtualPet {

  /**
   * Get the name of the pet.
   * @return the name of the pet.
   */
  String getName();

  /**
   * Get the pet's current mood.
   * @return the mood of the pet.
   */
  Mood getMoodState();

  /**
   * Get the pet's current health state.
   * @return the current health state of the pet.
   */
  Health getHealthState();

  /**
   * Get the pet's current life stage.
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
   * Feed the pet. Increases the pet's hunger need level.
   */
  void feed();

  /**
   * Play with the pet. Increases the pet's social need level.
   */
  void play();

  /**
   * Clean the pet. Increases the pet's hygiene need level.
   */
  void clean();

  /**
   * Put the pet to sleep. Increases the pet's energy need level.
   */
  void sleep();

  /**
   * Updates the mood, health, and life stage of the virtual pet.
   */
  void update();

  /**
   * Ends the game when the pet dies. Throws an IllegalStateException if the pet is not dead.
   */
  void petDeath() throws IllegalStateException;
}
