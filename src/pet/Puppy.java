package pet;

public class Puppy implements PetLifeStage {
  private final int interval = 3000; // 3 seconds

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
