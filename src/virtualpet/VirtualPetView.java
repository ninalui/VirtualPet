package virtualpet;

import java.util.HashMap;

/**
 * This interface represents the view of the virtual pet game. It contains methods to get user
 * input for the pet's name, set event listeners for the interaction buttons, and set the display
 * for the pet's name, life stage, mood, health, need levels, and image.
 */
public interface VirtualPetView {

  /**
   * Displays the game.
   */
  void showGame();

  /**
   * Gets the user input for the pet's name.
   *
   * @return the user input for the pet's name.
   */
  String getNameInput();

  /**
   * Displays an error message when the user does not enter a valid name for the pet.
   *
   * @param display the error message to display.
   */
  void displayNameError(String display);

  /**
   * Sets the event listeners for the interaction buttons.
   *
   * @param controller the controller that will handle the user's interactions with the buttons.
   */
  void setEventListeners(VirtualPetController controller);

  /**
   * Sets the display for the pet's name.
   *
   * @param display the pet's name to display.
   */
  void setNameDisplay(String display);

  /**
   * Sets the display for the pet's age.
   *
   * @param display the pet's age to display.
   */
  void setAgeDisplay(String display);

  /**
   * Sets the display for the pet's life stage.
   *
   * @param display the pet's life stage to display.
   */
  void setLifeStageDisplay(String display);

  /**
   * Sets the display for the pet's mood. The colour of the text changes based on the mood.
   *
   * @param display the pet's mood to display.
   */
  void setMoodDisplay(String display);

  /**
   * Sets the display for the pet's health. The colour of the text changes based on the health.
   *
   * @param display the pet's health to display.
   */
  void setHealthDisplay(String display);

  /**
   * Sets the display for the pet's need levels.
   *
   * @param levels the pet's need levels to display.
   */
  void setNeedsLevels(HashMap<Need, Integer> levels);

  /**
   * Sets the normal display for the pet's image.
   *
   * @param health     the pet's health, which determines the image to be displayed.
   * @param lifeStage  the pet's life stage, which determines the image to be displayed.
   */
  void setPetDisplay(Health health, PetLifeStage lifeStage);

  /**
   * Sets the display for the pet's image when an interaction button is clicked.
   *
   * @param need        the need that was interacted with.
   * @param lifeStage   the pet's life stage, which determines the image to be displayed.
   */
  void setPetDisplayOnAction(Need need, PetLifeStage lifeStage);

  /**
   * Sets the display for the user, which is used to display interaction-based messages to the user.
   *
   * @param display  the message to display to the user.
   */
  void setUserDisplay(String display);

  /**
   * Disables the interaction buttons.
   */
  void disableButtons();


}
