import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;
import virtualpet.GameTimer;
import virtualpet.GameTimerImpl;
import virtualpet.Health;
import virtualpet.Mood;
import virtualpet.Need;
import virtualpet.VirtualPet;
import virtualpet.VirtualPetImpl;

  /**
   * JUnit test class for VirtualPet.
   */
  public class VirtualPetTest {

    /**
     * Helper method to stop execution for a given number of milliseconds.
     *
     * @param millis the number of milliseconds to sleep for.
     */
    private void sleep(long millis) {
      try {
        Thread.sleep(millis);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    /**
     * Tests the constructor of the virtual pet game and that all values are initialized correctly.
     */
    @Test
    public void testConstructor() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertEquals("Test", pet.getName());
      assertEquals(Mood.HAPPY, pet.getMoodState());
      assertEquals(Health.HEALTHY, pet.getHealthState());
      assertEquals("Child", pet.getLifeStage().toString());
      assertEquals(100, pet.getNeedLevels().get(Need.HUNGER).intValue());
      assertEquals(100, pet.getNeedLevels().get(Need.SOCIAL).intValue());
      assertEquals(100, pet.getNeedLevels().get(Need.HYGIENE).intValue());
      assertEquals(100, pet.getNeedLevels().get(Need.ENERGY).intValue());
    }

    /**
     * Tests that the constructor throws an IllegalArgumentException when the name is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullName() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl(null, timer);
    }

    /**
     * Tests that the constructor throws an IllegalArgumentException when the timer is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullTimer() {
      VirtualPet pet = new VirtualPetImpl("Test", null);
    }

    /**
     * Tests that the constructor throws an IllegalArgumentException when the name is null and
     * the timer is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullNameAndTimer() {
      VirtualPet pet = new VirtualPetImpl(null, null);
    }

    /**
     * Tests toString method of the virtual pet game.
     */
    @Test
    public void testToString() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertEquals("Name: Test\nAge: 0\nMood: Happy\nHealth: Healthy\nLife Stage: Child\n"
          + "Need Levels:\n"
          + "Hunger: 100\n"
          + "Social: 100\n"
          + "Hygiene: 100\n"
          + "Energy: 100\n", pet.toString());
    }

    /**
     * Tests getName method of the virtual pet game.
     */
    @Test
    public void testGetName() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertEquals("Test", pet.getName());
    }

    /**
     * Tests getMoodState method of the virtual pet game.
     */
    @Test
    public void testGetMoodState() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertEquals(Mood.HAPPY, pet.getMoodState());
    }

    /**
     * Tests getHealthState method of the virtual pet game.
     */
    @Test
    public void testGetHealthState() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertEquals(Health.HEALTHY, pet.getHealthState());
    }

    /**
     * Tests getLifeStage method of the virtual pet game.
     */
    @Test
    public void testGetLifeStage() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertEquals("Child", pet.getLifeStage().toString());
    }

    /**
     * Tests getNeedLevels method of the virtual pet game.
     */
    @Test
    public void testGetNeedLevels() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      HashMap<Need, Integer> needMap = pet.getNeedLevels();
      Set<Need> needs = needMap.keySet();
      assertEquals(4, needs.size());
      assertTrue(needs.contains(Need.HUNGER));
      assertTrue(needs.contains(Need.SOCIAL));
      assertTrue(needs.contains(Need.HYGIENE));
      assertTrue(needs.contains(Need.ENERGY));

      Collection<Integer> needLevels = needMap.values();
      assertEquals(4, needLevels.size());
      needLevels.forEach(needLevel -> assertEquals(100, needLevel.intValue()));

      // checking needsMap cannot be mutated
      assertEquals(100, pet.getNeedLevels().get(Need.HUNGER).intValue()); // original value
      needMap.replace(Need.HUNGER, 50); // attempt to mutate
      assertEquals(100,
          pet.getNeedLevels().get(Need.HUNGER).intValue()); // unchanged after mutating
    }

    /**
     * Tests the interact method of the virtual pet game by feeding the pet.
     */
    @Test
    public void testInteractFeed() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertEquals(100, pet.getNeedLevels().get(Need.HUNGER).intValue());
      sleep(6000);
      assertEquals(92, pet.getNeedLevels().get(Need.HUNGER).intValue());
      pet.interact(Need.HUNGER);
      assertEquals(100, pet.getNeedLevels().get(Need.HUNGER).intValue());
    }

    /**
     * Tests the interact method of the virtual pet game by playing with the pet.
     */
    @Test
    public void testInteractPlay() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertEquals(100, pet.getNeedLevels().get(Need.SOCIAL).intValue());
      sleep(6000);
      assertEquals(90, pet.getNeedLevels().get(Need.SOCIAL).intValue());
      pet.interact(Need.SOCIAL);
      assertEquals(100, pet.getNeedLevels().get(Need.SOCIAL).intValue());
    }

    /**
     * Tests the interact method of the virtual pet game by cleaning the pet.
     */
    @Test
    public void testInteractClean() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertEquals(100, pet.getNeedLevels().get(Need.HYGIENE).intValue());
      sleep(6000);
      assertEquals(95, pet.getNeedLevels().get(Need.HYGIENE).intValue());
      pet.interact(Need.HYGIENE);
      assertEquals(100, pet.getNeedLevels().get(Need.HYGIENE).intValue());
    }

    /**
     * Tests the interact method of the virtual pet game by putting the pet to sleep.
     */
    @Test
    public void testInteractSleep() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertEquals(100, pet.getNeedLevels().get(Need.ENERGY).intValue());
      sleep(6000);
      assertEquals(90, pet.getNeedLevels().get(Need.ENERGY).intValue());
      pet.interact(Need.ENERGY);
      assertEquals(100, pet.getNeedLevels().get(Need.ENERGY).intValue());
    }

    /**
     * Tests the interact method throws an exception when attempting to interact with an invalid need.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInteractInvalidNeed() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertEquals(100, pet.getNeedLevels().get(Need.ENERGY).intValue());
      sleep(6000);
      pet.interact(null);
    }

    /**
     * Tests that the mood changes to a single low need correctly.
     */
    @Test
    public void testUpdateMoodSingleLowNeed() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertEquals(Mood.HAPPY, pet.getMoodState());
      sleep(20000);
      assertEquals(Mood.BORED, pet.getMoodState()); // 64
      pet.interact(Need.SOCIAL); // 79
      pet.interact(Need.SOCIAL); // 94
      assertEquals(Mood.TIRED, pet.getMoodState()); // 70
      pet.interact(Need.ENERGY); // 85
      pet.interact(Need.ENERGY); //100
      sleep(5000);
      assertEquals(Mood.HUNGRY, pet.getMoodState()); // 68
      sleep(15000);
      pet.interact(Need.HUNGER); // 54
      pet.interact(Need.HUNGER); // 64
      pet.interact(Need.HUNGER); // 74
      pet.interact(Need.SOCIAL);
      pet.interact(Need.SOCIAL);
      pet.interact(Need.ENERGY);
      assertEquals(Mood.DIRTY, pet.getMoodState());
    }

    /**
     * Test that the mood changes back to happy after the pet's needs are met.
     */
    @Test
    public void testUpdateMoodToHappy() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertEquals(Mood.HAPPY, pet.getMoodState());
      sleep(20000);
      assertEquals(Mood.BORED, pet.getMoodState());
      pet.interact(Need.SOCIAL);
      pet.interact(Need.SOCIAL);
      pet.interact(Need.SOCIAL);
      pet.interact(Need.ENERGY);
      assertEquals(Mood.HAPPY, pet.getMoodState());
    }

    /**
     * Test that the pet gets sad correctly.
     */
    @Test
    public void testUpdateMoodToSad() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertEquals(Mood.HAPPY, pet.getMoodState());
      sleep(50000);
      assertEquals(Mood.SAD, pet.getMoodState());
    }

    /**
     * Tests that the pet becomes sick correctly.
     */
    @Test
    public void testPetIllness() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertEquals(Health.HEALTHY, pet.getHealthState());
      sleep(6000);
      assertEquals(Health.HEALTHY, pet.getHealthState());
      sleep(30000);
      assertEquals(Health.SICK, pet.getHealthState());
    }

    /**
     * Tests that the pet gets sick then becomes healthy after its needs are met.
     */
    @Test
    public void testPetHealthChangesBackToHealthy() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertEquals(Health.HEALTHY, pet.getHealthState());
      sleep(35000);
      timer.stop();
      assertEquals(Health.SICK, pet.getHealthState());
      pet.interact(Need.ENERGY);
      pet.interact(Need.ENERGY);
      pet.interact(Need.ENERGY);
      pet.interact(Need.ENERGY);
      pet.interact(Need.SOCIAL);
      pet.interact(Need.SOCIAL);
      pet.interact(Need.SOCIAL);
      pet.interact(Need.SOCIAL);
      assertEquals(Health.HEALTHY, pet.getHealthState());
    }

    /**
     * Tests that life stage changes after the appropriate amount of time has passed.
     */
    @Test
    public void testLifeStageChanges() {
      GameTimer testingTimer = new TestingTimer();
      VirtualPet pet = new VirtualPetImpl("Test", testingTimer);
      assertEquals("Child", pet.getLifeStage().toString());
      pet.update();
      pet.update();
      assertEquals("Adult", pet.getLifeStage().toString());
      pet.update();
      pet.update();
      pet.update();
      assertEquals("Senior", pet.getLifeStage().toString());
    }

    /**
     * Tests that the life stage does not change if the pet is not healthy and happy.
     */
    @Test
    public void testLifeStageDoesNotChange() {
      GameTimer testingTimer = new TestingTimer();
      VirtualPet pet = new VirtualPetImpl("Test", testingTimer);
      assertEquals("Child", pet.getLifeStage().toString());
      assertEquals(Health.HEALTHY, pet.getHealthState());
      assertEquals(Mood.HAPPY, pet.getMoodState());
      sleep(55000);
      pet.update();
      pet.update();
      pet.update();
      assertEquals(Mood.HUNGRY, pet.getMoodState());
      assertEquals("Puppy", pet.getLifeStage().toString());
    }

    /**
     * Tests that the pet's need levels decrease corresponding to the life stage correctly.
     */
    @Test
    public void testDecreaseNeedsOverTimeLifeStage() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      HashMap<Need, Integer> needLevels = pet.getNeedLevels();
      for (Need need : needLevels.keySet()) {
        assertEquals(100, needLevels.get(need).intValue());
      }
      sleep(6000);
      assertEquals(92, pet.getNeedLevels().get(Need.HUNGER).intValue());
      assertEquals(90, pet.getNeedLevels().get(Need.SOCIAL).intValue());
      assertEquals(95, pet.getNeedLevels().get(Need.HYGIENE).intValue());
      assertEquals(90, pet.getNeedLevels().get(Need.ENERGY).intValue());

      sleep(5000);
      assertEquals(84, pet.getNeedLevels().get(Need.HUNGER).intValue());
      assertEquals(80, pet.getNeedLevels().get(Need.SOCIAL).intValue());
      assertEquals(90, pet.getNeedLevels().get(Need.HYGIENE).intValue());
      assertEquals(80, pet.getNeedLevels().get(Need.ENERGY).intValue());
    }

    /**
     * Tests isAlive method of the virtual pet game.
     */
    @Test
    public void testIsAlive() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertTrue(pet.isAlive());
      sleep(50000);
      assertFalse(pet.isAlive());
    }

    /**
     * Tests that the player cannot interact with the pet after it dies.
     */
    @Test(expected = IllegalStateException.class)
    public void testActionWhenDead() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      sleep(50000);
      pet.interact(Need.HUNGER);
    }

    /**
     * Tests that the player cannot perform an action when the pet's need is already full.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testActionWhenNeedFull() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertEquals(100, pet.getNeedLevels().get(Need.HUNGER).intValue());
      pet.interact(Need.HUNGER);
    }

    /**
     * Different implementation of GameTimer for testing purposes. Increments the elapsed time by 60
     * seconds every time getElapsedTime() is called (in update method of VirtualPet) to allow for
     * control over age and life stage.
     */
    static class TestingTimer implements GameTimer {
      private final Timer timer;
      private final Instant startTime;
      private long count = 0;

      public TestingTimer() {
        this.startTime = Instant.now();
        this.timer = new Timer();
      }

      @Override
      public void scheduleTask(TimerTask task, long interval) {
        this.timer.schedule(task, 2000, interval);
      }

      @Override
      public void stop() {
        this.timer.cancel();
      }

      @Override
      public long getElapsedTime() {
        count += 60;
        Instant next = this.startTime.plusSeconds(count);
        Duration duration = Duration.between(this.startTime, next);
        return duration.getSeconds();
      }
    }
  }
}
