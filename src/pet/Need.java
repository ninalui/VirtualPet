package pet;

/**
 * Enum representing the possible needs of the virtual pet.
 * Options: Hunger, Social, Hygiene, Energy.
 */
public enum Need {
    HUNGER("Hunger"),
    SOCIAL("Social"),
    HYGIENE("Hygiene"),
    ENERGY("Energy");

    private String needState;

    Need(String needState) {
        this.needState = needState;
    }

    @Override
    public String toString() {
        return this.needState;
    }
}
