package virtualpet;

/**
 * Driver class for Virtual Pet. Demonstrates game play of virtual pet including
 * creation of new game, displaying stats, how needs decrease over time and increase upon
 * interaction, how the virtual pet grows into a new life stage after a certain amount of time
 * has passed, how needs decrease and increase differently upon entering a new life stage,
 * and dies when neglected and needs hit 0.
 */
public class VirtualPetDriver {

  /**
   * Driver for virtual pet game.
   *
   * @param args  command-line arguments
   * @throws InterruptedException from running Thread.sleep, if any threads interrupt the currently
   *                              running thread
   */
  public static void main(String[] args) throws InterruptedException {
    // Create game timer and new pet
    GameTimer timer = new GameTimerImpl();
    VirtualPet pet = new VirtualPetImpl("Jack", timer);

    // Display initial pet stats
    System.out.println(pet);

    // Wait for 5 seconds for first need decrease
    Thread.sleep(5000);

    // Display updated pet stats
    System.out.println("After 5 seconds:");
    System.out.println(pet);

    // Interact with the pet and display updated stats
    // Feed
    pet.interact(Need.HUNGER);
    System.out.println("Feeding pet");
    System.out.println(pet);

    // Play
    pet.interact(Need.SOCIAL);
    System.out.println("Playing with pet");
    System.out.println(pet);

    // Clean
    pet.interact(Need.HYGIENE);
    System.out.println("Cleaning pet");
    System.out.println(pet);

    // Sleep
    pet.interact(Need.ENERGY);
    System.out.println("Putting pet to sleep");
    System.out.println(pet);

    // Wait for 20 seconds for needs to decrease
    Thread.sleep(20000);
    // Neglecting the pet's needs leads to mood changes and health deterioration
    System.out.println("After 20 seconds");
    System.out.println(
        pet.getName() + " is " + pet.getMoodState().toString().toLowerCase() + "!\n");
    Thread.sleep(10000);
    System.out.println("After 10 seconds");
    System.out.println(
        pet.getName() + " is " + pet.getHealthState().toString().toLowerCase() + "!\n");

    // Interact with pet to fulfill needs to 100
    for (int i = 0; i < 4; i++) {
      pet.interact(Need.HUNGER);
      pet.interact(Need.SOCIAL);
      pet.interact(Need.HYGIENE);
      pet.interact(Need.ENERGY);
    }

    // Display updated stats
    System.out.println(pet);

    // Simulate game play up to 2 minutes so the pet can grow up to an adult
    Thread.sleep(1000);
    for (int i = 0; i < 17; i++) {
      // Let time pass and needs decrease
      Thread.sleep(5000);
      System.out.println("\nAfter 5 seconds");
      System.out.println(pet);

      // Interact with pet to maintain health and happiness
      pet.interact(Need.HUNGER);
      System.out.println("Feeding pet");
      pet.interact(Need.SOCIAL);
      System.out.println("Playing with pet");
      pet.interact(Need.HYGIENE);
      System.out.println("Cleaning pet");
      pet.interact(Need.ENERGY);
      System.out.println("Putting pet to sleep");

      System.out.println("\n" + pet);
    }
    System.out.println(pet.getName() + " is now an " + pet.getLifeStage().toString());

    // Increase and decrease of needs are now at different rates due to different life stage
    // Wait for 5 seconds for needs to decrease
    Thread.sleep(5000);
    System.out.println("\nAfter 5 seconds");
    System.out.println(pet);

    // Interact with pet, display updated stats
    pet.interact(Need.HUNGER);
    System.out.println("Feeding pet");
    System.out.println(pet);

    pet.interact(Need.SOCIAL);
    System.out.println("Playing with pet");
    System.out.println(pet);

    pet.interact(Need.HYGIENE);
    System.out.println("Cleaning pet");
    System.out.println(pet);

    pet.interact(Need.ENERGY);
    System.out.println("Putting pet to sleep");
    System.out.println(pet);

    // Neglecting pet leads to death and game over
    while (pet.isAlive()) {
      Thread.sleep(5000);
      System.out.println("\n5 seconds...");
    }
    if (!pet.isAlive()) {
      System.out.println(pet.getName() + " has died! Game over!");
    }
    System.out.println(pet);
  }
}
