package virtualpet;

/**
 * Enum for the possible mood states of the virtual pet.
 * Options: Happy, Hungry, Bored, Dirty, Tired, Sad.
 */
public enum Mood {
  HAPPY("Happy"),
  HUNGRY("Hungry"),
  BORED("Bored"),
  DIRTY("Dirty"),
  TIRED("Tired"),
  SAD("Sad");

  private final String moodState;

  /**
   * Provide a String representation of the mood state.
   *
   * @param moodState String representing the mood state of a virtual pet.
   */
  Mood(String moodState) {
    this.moodState = moodState;
  }

  @Override
  public String toString() {
    return this.moodState;
  }
}
