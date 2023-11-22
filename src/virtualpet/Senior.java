package virtualpet;

import java.util.TreeMap;

public class Senior implements PetLifeStage {

  private TreeMap<Need, Integer> increaseNeedLevels;
  private TreeMap<Need, Integer> decreaseNeedLevels;

  final int upHunger = 10;
  final int upSocial = 5;
  final int upHygiene = 10;
  final int upEnergy = 10;
  final int downHunger = 10;
  final int downSocial = 10;
  final int downHygiene = 10;
  final int downEnergy = 15;

  public Senior() {this.increaseNeedLevels = initializeIncreaseNeedLevels();
    this.decreaseNeedLevels = initializeDecreaseNeedLevels();
  }

  private TreeMap<Need, Integer> initializeIncreaseNeedLevels() {
    TreeMap<Need, Integer> increaseNeedLevels = new TreeMap<Need, Integer>();
    increaseNeedLevels.put(Need.HUNGER, upHunger);
    increaseNeedLevels.put(Need.SOCIAL, upSocial);
    increaseNeedLevels.put(Need.HYGIENE, upHygiene);
    increaseNeedLevels.put(Need.ENERGY, upEnergy);
    return increaseNeedLevels;
  }

  private TreeMap<Need, Integer> initializeDecreaseNeedLevels() {
    TreeMap<Need, Integer> decreaseNeedLevels = new TreeMap<Need, Integer>();
    decreaseNeedLevels.put(Need.HUNGER, downHunger);
    decreaseNeedLevels.put(Need.SOCIAL, downSocial);
    decreaseNeedLevels.put(Need.HYGIENE, downHygiene);
    decreaseNeedLevels.put(Need.ENERGY, downEnergy);
    return decreaseNeedLevels;
  }

  @Override
  public int increaseNeed(Need need, Integer currentLevel) {
    return currentLevel + increaseNeedLevels.get(need);
  }

  @Override
  public int decreaseNeed(Need need, Integer currentLevel) {
    return currentLevel - increaseNeedLevels.get(need);
  }
}
