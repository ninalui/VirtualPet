package pet;

public class Senior implements PetLifeStage {

  private final int interval = 8000; // 8 seconds

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
    return this.interval;
  }
}
