package virtualpet;

/**
 * This class represents the adult life stage of a pet. It extends the AbstractLifeStage class.
 * It contains the rates at which the pet's needs increase and decrease and the interval at which
 * the pet's needs decrease. As an adult, the pet's needs decrease at a slower rate and at smaller
 * values, and increase at higher values than the previous puppy life stage, similar to how an
 * older dog requires less attention and care than a puppy.
 */
public class Adult extends AbstractLifeStage {
  private static final int INTERVAL = 5000; // 5 seconds
  private static final int[] INCREASE_RATES = {10, 10, 5, 10};
  private static final int[] DECREASE_RATES = {10, 10, 5, 10};

  public Adult() {
    super(INCREASE_RATES, DECREASE_RATES, INTERVAL);
  }

  @Override
  public String toString() {
    return "Adult";
  }
}
