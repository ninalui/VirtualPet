package virtualpet;

/**
 * Enum representing the possible needs of the virtual pet.
 * Options: Hunger, Social, Hygiene, Energy.
 */
public enum Need {
  HUNGER("Hunger"),
  SOCIAL("Social"),
  HYGIENE("Hygiene"),
  ENERGY("Energy");

  private final String needState;

  /**
   * Provide a String representation of the need state.
   *
   * @param needState String representing the need state of a virtual pet.
   */
  Need(String needState) {
    this.needState = needState;
  }

  @Override
  public String toString() {
    return this.needState;
  }
}
