package virtualpet;

/**
 * This class represents the puppy life stage of a pet. It extends the AbstractLifeStage class.
 * It contains the rates at which the pet's needs increase and decrease and the interval at which
 * the pet's needs decrease. As a puppy, the pet's needs decrease at a faster rate and at larger
 * values, and increase at smaller values to represent the fact that puppies require more attention
 * and care than older dogs.
 */
public class Puppy extends AbstractLifeStage {
  private static final int INTERVAL = 3000; // 3 seconds
  private static final int[] INCREASE_RATES = {5, 5, 9, 5};
  private static final int[] DECREASE_RATES = {10, 15, 9, 10};

  public Puppy() {
    super(INCREASE_RATES, DECREASE_RATES, INTERVAL);
  }

  @Override
  public String toString() {
    return "Puppy";
  }
}
