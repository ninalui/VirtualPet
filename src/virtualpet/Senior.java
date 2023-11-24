package virtualpet;

/**
 * This class represents the senior life stage of a pet. It extends the AbstractLifeStage class.
 * It contains the rates at which the pet's needs increase and decrease and the interval at which
 * the pet's needs decrease. As a senior, the pet's needs decrease at a slower rate and at smaller
 * values, similar to how life slows down for a senior dog. The pet's needs increase at smaller
 * values as well to correspond with the smaller decrease rates.
 */
public class Senior extends AbstractLifeStage {
  private static final int INTERVAL = 8000; // 8 seconds
  private static final int[] INCREASE_RATES = {5, 5, 5, 10};
  private static final int[] DECREASE_RATES = {8, 5, 5, 15};


  public Senior() {
    super(INCREASE_RATES, DECREASE_RATES, INTERVAL);
  }

  @Override
  public String toString() {
    return "Senior";
  }
}
