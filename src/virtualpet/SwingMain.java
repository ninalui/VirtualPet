package virtualpet;

/**
 * This class represents the main method for the virtual pet game. It creates a new virtual pet
 * model, view, and controller, and then calls the controller's playGame method to start the game.
 */
public class SwingMain {
  /**
   * Run a Virtual Pet Game with a Swing GUI.
   *
   * @param args not used
   */
  public static void main(String[] args) {
    VirtualPet model = new VirtualPetImpl("", new GameTimerImpl());
    VirtualPetView view = new SwingVirtualPetView("Virtual Pet");
    VirtualPetController controller = new SwingVirtualPetController(view, model);
    controller.playGame();
  }
}
