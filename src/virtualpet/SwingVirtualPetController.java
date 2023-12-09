package virtualpet;

import javax.swing.SwingUtilities;

/**
 * This class represents a controller for a virtual pet game. It implements the VirtualPetController
 * interface. It contains a view and a model. It uses the view to display the game and the model to
 * update the game's state.
 */
public class SwingVirtualPetController implements VirtualPetController {
  private final VirtualPetView view;
  private final VirtualPet model;

  /**
   * Constructs a SwingVirtualPetController object with the given view and model.
   *
   * @param view  the view to be used by the controller.
   * @param model the model to be used by the controller.
   */
  public SwingVirtualPetController(VirtualPetView view, VirtualPet model) {
    if (view == null) {
      throw new IllegalArgumentException("View cannot be null");
    }
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }

    this.view = view;
    this.model = model;
    view.setEventListeners(this);
  }

  @Override
  public void playGame() {
    // pause timer/decreasing of needs until user enters a name
    model.pauseTimer();
    getPetName();

    view.showGame();
    // resume timer/decreasing of needs
    model.pauseTimer();

    view.setNameDisplay("Name: " + model.getName());
    view.setUserDisplay("Welcome to your Virtual Pet!");
    view.setPetDisplay(model.getHealthState(), model.getLifeStage());

    int flag = 0; // flag to check if pet has aged up and display has changed
    while (model.isAlive()) {
      updateViewStats();

      // when pet ages up, update pet display
      if (model.getLifeStage() instanceof Adult && flag == 0) {
        SwingUtilities.invokeLater(() -> view.setPetDisplay(model.getHealthState(), model.getLifeStage()));
        flag = 1;
      }
      if (model.getLifeStage() instanceof Senior && flag == 1) {
        SwingUtilities.invokeLater(() -> view.setPetDisplay(model.getHealthState(), model.getLifeStage()));
        flag = 2;
      }
    }

    // when pet dies, update pet display and stats, and disable buttons
    if (model.getHealthState() == Health.DEAD) {
      view.setPetDisplay(model.getHealthState(), model.getLifeStage());
      view.setUserDisplay(model.getName() + " has died. Game over!");
    }

    view.setHealthDisplay("Health: " + model.getHealthState());
    view.setNeedsLevels(model.getNeedLevels());
    view.disableButtons();
  }

  @Override
  public void onActionClick(Need need) {
    try {
      model.interact(need);
      view.setPetDisplayOnAction(need, model.getLifeStage());
      view.setUserDisplay("Interacting with " + model.getName() + "...");
    } catch (IllegalArgumentException e) {
      view.setUserDisplay(need.toString() + " need is already full!");
    } catch (IllegalStateException e) {
      view.setUserDisplay("Cannot interact! " + model.getName() + " is dead!");
    }
  }

  /**
   * Updates the view's stats displays to match the model's stats. Stats include the pet's age,
   * life stage, mood, health, and need levels.
   */
  private void updateViewStats() {
    view.setAgeDisplay("Age: " + model.getAge());
    view.setLifeStageDisplay("Life Stage: " + model.getLifeStage());
    view.setMoodDisplay("Mood: " + model.getMoodState());
    view.setHealthDisplay("Health: " + model.getHealthState());
    view.setNeedsLevels(model.getNeedLevels());
  }

  @Override
  public void getPetName() {
    String name = "";
    while (!isValidName(name)) {
      name = view.getNameInput();

      if (!isValidName(name)) {
        view.displayNameError("Please enter a valid name!");
      }
    }
    model.setName(name);
  }

  /**
   * Checks if the given name is valid. A valid name is not null and contains characters.
   *
   * @param name the name to be validated.
   * @return true if the name is valid, false otherwise.
   */
  private boolean isValidName(String name) {
    return name != null && !name.trim().isEmpty();
  }

}
