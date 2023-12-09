package virtualpet;

/**
 * This class represents the senior life stage of a pet. It extends the AbstractLifeStage class.
 * It contains the rates at which the pet's needs increase and decrease. As a senior, the pet's
 * needs decrease at an even smaller value than the adult life stage, similar to how life slows
 * down for a senior pet.
 */
public class Senior extends AbstractLifeStage {
  private static final int[] INCREASE_RATES = {6, 10, 8, 10};
  private static final int[] DECREASE_RATES = {1, 2, 5, 8};


  public Senior() {
    super(INCREASE_RATES, DECREASE_RATES);
  }

  @Override
  public String toString() {
    return "Senior";
  }
}
