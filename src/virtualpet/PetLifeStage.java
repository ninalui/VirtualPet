package virtualpet;

import java.util.TreeMap;

public interface PetLifeStage {


  /**
   * Increase the virtual pet's need level for the given need.
   *
   * @return
   */
  public int increaseNeed(Need need, Integer currentNeedLevel);

  /**
   * Decrease the virtual pet's need level for the given need.
   */
  public int decreaseNeed(Need need, Integer currentNeedLevel);
}
