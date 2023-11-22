package virtualpet;

public class Main {
  public static void main(String[] args) {
    VirtualPet pet = new VirtualPetImpl("Joe");

    System.out.println("Initial State");
    System.out.println(pet.needLevelsToString());

    // pause execution to allow for scheduled decrease to run
    try {
      Thread.sleep(11000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println("11 seconds");
    System.out.println(pet.needLevelsToString());

  }
}
