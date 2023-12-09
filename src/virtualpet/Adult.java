package virtualpet;

/**
 * This class represents the adult life stage of a pet. It extends the AbstractLifeStage class.
 * It contains the rates at which the pet's needs increase and decrease. As an adult, the pet's
 * needs decrease at smaller values than in the child life stage to represent the fact that an
 * older pet requires less attention and care than a younger pet.
 */
public class Adult extends AbstractLifeStage {
  private static final int[] INCREASE_RATES = {8, 5, 10, 8};
  private static final int[] DECREASE_RATES = {4, 3, 5, 6};

  public Adult() {
    super(INCREASE_RATES, DECREASE_RATES);
  }

  @Override
  public String toString() {
    return "Adult";
  }
}
