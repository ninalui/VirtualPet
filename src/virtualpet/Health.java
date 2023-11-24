package virtualpet;

/**
 * Enum representing the possible health states of a virtual pet.
 * Options: Healthy, Sick, Dead.
 */
public enum Health {
  HEALTHY("Healthy"),
  SICK("Sick"),
  DEAD("Dead");

  private final String healthState;

  /**
   * Provide a String representation of the health state.
   *
   * @param healthState String representing the health state of a virtual pet.
   */
  Health(String healthState) {
    this.healthState = healthState;
  }

  @Override
  public String toString() {
    return this.healthState;
  }


}
