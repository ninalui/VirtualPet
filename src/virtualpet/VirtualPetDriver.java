package virtualpet;

public class VirtualPetDriver {

  public static void main(String[] args) throws InterruptedException {
    // Create game timer and new pet
    GameTimer timer = new GameTimerImpl();
    VirtualPet pet = new VirtualPetImpl("Jack", timer);

    // Display initial pet stats
    System.out.println(pet);

    // Wait for 2 seconds for first need decrease
    Thread.sleep(2000);

    // Display updated pet stats
    System.out.println("After 2 seconds:");
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

    // Wait for 6 seconds for needs to decrease
    Thread.sleep(6000);
    // Neglecting the pet's needs leads to mood changes and health deterioration
    System.out.println("After 6 seconds");
    System.out.println(pet.getName() + " is " + pet.getMoodState().toString().toLowerCase() + "!\n");
    Thread.sleep(9000);
    System.out.println("After 9 seconds");
    System.out.println(pet.getName() + " is " + pet.getHealthState().toString().toLowerCase() + "!\n");

    // Interact with pet to fulfill needs to 100
    for (int i = 0; i < 11; i++) {
      pet.interact(Need.HUNGER);
    }
    for (int i = 0; i < 17; i++) {
      pet.interact(Need.SOCIAL);
    }
    for (int i = 0; i < 5; i++) {
      pet.interact(Need.HYGIENE);
    }
    for (int i = 0; i < 11; i++) {
      pet.interact(Need.ENERGY);
    }

    // Display updated stats
    System.out.println(pet);

    // Simulate game play for 3 minutes so the pet can grow up to an adult
    Thread.sleep(1000);
    for (int i = 0; i < 54; i++) {
      // Let time pass and needs decrease
      Thread.sleep(3000);
      System.out.println("\nAfter 3 seconds");
      System.out.println(pet);

      // Interact with pet to maintain health and happiness
      for (int j = 0; j < 2; j++) {
        pet.interact(Need.HUNGER);
        System.out.println("Feeding pet");
      }
      for (int j = 0; j < 3; j++) {
        pet.interact(Need.SOCIAL);
        System.out.println("Playing with pet");
      }
      for (int j = 0; j < 1; j++) {
        pet.interact(Need.HYGIENE);
        System.out.println("Cleaning pet");
      }
      for (int j = 0; j < 2; j++) {
        pet.interact(Need.ENERGY);
        System.out.println("Putting pet to sleep");
      }
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
      Thread.sleep(3000);
      System.out.println("\n3 seconds...");
    }
    if (!pet.isAlive()) {
      System.out.println(pet.getName() + " has died! Game over!");
    }
    System.out.println(pet);
  }
}
