package virtualpet;

/**
 * This class represents the puppy life stage of a pet. It extends the AbstractLifeStage class.
 * It contains the rates at which the pet's needs increase and decrease and the interval at which
 * the pet's needs decrease. As a child, the pet's needs decrease at a faster rate and at larger
 * values to represent the fact that younger pets require more attention and care than older pets.
 */
public class Child extends AbstractLifeStage {
  private static final int[] INCREASE_RATES = {10, 15, 8, 15};
  private static final int[] DECREASE_RATES = {8, 12, 5, 10};

  public Child() {
    super(INCREASE_RATES, DECREASE_RATES);
  }

  @Override
  public String toString() {
    return "Child";
  }
}
