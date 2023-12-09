package virtualpet;

/**
 * This interface represents a controller for a virtual pet game. It contains methods to play the
 * game and to interact with the pet.
 */
public interface VirtualPetController {

  /**
   * Plays the virtual pet game. It gets the pet's name from the user, initializes the pet's
   * name, and displays the pet's name, mood state, health state, and life stage. It then
   * displays the pet's need levels and allows the user to interact with the pet until the pet
   * dies. When the pet dies, it displays the pet's final health state and need levels and
   * displays a game over message.
   */
  void playGame();

  /**
   * Interacts with the pet. It increases the pet's need level of the chosen need and updates
   * the display to show the interaction. If the chosen need is already full, then it displays a
   * message saying that the need is already full. If the pet is dead, then it displays a
   * message saying that the pet is dead and cannot be interacted with.
   *
   * @param need the chosen need interaction.
   */
  void onActionClick(Need need);

  /**
   * Get the pet's name.
   */
  void getPetName();

}
