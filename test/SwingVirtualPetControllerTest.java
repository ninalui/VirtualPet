import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import virtualpet.GameTimerImpl;
import virtualpet.Health;
import virtualpet.Mood;
import virtualpet.Need;
import virtualpet.SwingVirtualPetController;
import virtualpet.SwingVirtualPetView;
import virtualpet.VirtualPet;
import virtualpet.VirtualPetController;
import virtualpet.VirtualPetImpl;
import virtualpet.VirtualPetView;

/**
 * JUnit test class for SwingVirtualPetController.
 */
public class SwingVirtualPetControllerTest {

  private VirtualPet model;
  private VirtualPetView view;
  private VirtualPetController controller;

  /**
   * Sets up model, view, and controller for testing.
   */
  @Before
  public void setUp() {
    model = new VirtualPetImpl("Test", new GameTimerImpl());
    view = new SwingVirtualPetView("Test");
    controller = new SwingVirtualPetController(view, model);
  }

  /**
   * Tests that the game initializes correctly.
   */
  @Test
  public void testPlayGameInitialization() {
    assertEquals("Test", model.getName());
    assertEquals(0, model.getAge());
    assertEquals(Mood.HAPPY, model.getMoodState());
    assertEquals(Health.HEALTHY, model.getHealthState());
    assertEquals(100, model.getHealth());
    HashMap<Need, Integer> needLevels = model.getNeedLevels();
    Set<Need> needs = needLevels.keySet();
    assertTrue(needs.contains(Need.HUNGER));
    assertTrue(needs.contains(Need.SOCIAL));
    assertTrue(needs.contains(Need.HYGIENE));
    assertTrue(needs.contains(Need.ENERGY));
    assertEquals(100, needLevels.get(Need.HUNGER).intValue());
    assertEquals(100, needLevels.get(Need.SOCIAL).intValue());
    assertEquals(100, needLevels.get(Need.HYGIENE).intValue());
    assertEquals(100, needLevels.get(Need.ENERGY).intValue());
  }

  /**
   * Tests that the constructor throws an exception when given a null view.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {
    controller = new SwingVirtualPetController(null, model);
  }

  /**
   * Tests that the constructor throws an exception when given a null model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    controller = new SwingVirtualPetController(view, null);
  }

  /**
   * Tests that the constructor throws an exception when given a null view and model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullViewAndModel() {
    controller = new SwingVirtualPetController(null, null);
  }

  /**
   * Tests that the onActionClick method works as expected to increase a need level upon action.
   */
  @Test
  public void testOnActionClick() {
    assertEquals(100, model.getNeedLevels().get(Need.HUNGER).intValue());
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
      assertEquals(92, model.getNeedLevels().get(Need.HUNGER).intValue());
      controller.onActionClick(Need.HUNGER);
      assertEquals(100, model.getNeedLevels().get(Need.HUNGER).intValue());
    }
  }
}
