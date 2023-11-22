package virtualpet;

import java.util.Map;
import java.util.TreeMap;

/**
 * Interface representing a virtual pet. The virtual pet can be fed, played with, cleaned, and put
 * to sleep. The virtual pet has a name, mood state, health state, life stage, and need levels.
 */
public interface VirtualPet {

  /**
   * Perform an action with the virtual pet.
   */
  public void action(Need need);

  /**
   * Get the virtual pet's name
   */
  public String getName();

  /**
   * Get the virtual pet's current mood state.
   */
  public Mood getMoodState();

  /**
   * Get the virtual pet's current health state.
   */
  public Health getHealthState();

  /**
   * Get the virtual pet's current life stage.
   */
  public PetLifeStage getLifeStage();

  /**
   * Get the virtual pet's current need levels.
   */
  public TreeMap getNeedLevels();

  public void decreaseNeedLevels();

  public String needLevelsToString();

}
