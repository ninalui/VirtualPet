package pet;


/**
 * Enum representing the possible health states of the virtual pet.
 * Options: Healthy, Sick, Dead.
 */
public enum Health {
    HEALTHY("Healthy"),
    SICK("Sick"),
    DEAD("Dead");

    private String healthState;

    Health(String healthState) {
        this.healthState = healthState;
    }

    @Override
    public String toString() {
        return this.healthState;
    }



}
