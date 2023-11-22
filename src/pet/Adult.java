package pet;

public class Adult implements PetLifeStage {

  @Override
  public int increaseNeed(Need need, int currentLevel) throws IllegalArgumentException {
    return 0;
  }

  @Override
  public int decreaseNeed(Need need) throws IllegalArgumentException {
    return 0;
  }

  @Override
  public long getInterval() {
    // 3 seconds
    return 3000;
  }
}
