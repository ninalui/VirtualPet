import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import org.junit.Test;
import pet.GameTimerImpl;
import pet.Health;
import pet.Mood;
import pet.Need;
import pet.VirtualPet;
import pet.VirtualPetImpl;
import pet.GameTimer;

/**
 * JUnit test class for the virtual pet game.
 */
public class testVirtualPet {

  /**
   * Helper method to stop execution for a given number of milliseconds to allow for scheduled
   * needs decrease to run.
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
    assertEquals("Puppy", pet.getLifeStage().toString());
    assertEquals(100, pet.getNeedLevels().get(Need.HUNGER).intValue());
    assertEquals(100, pet.getNeedLevels().get(Need.SOCIAL).intValue());
    assertEquals(100, pet.getNeedLevels().get(Need.HYGIENE).intValue());
    assertEquals(100, pet.getNeedLevels().get(Need.ENERGY).intValue());
  }


  /**
   * Tests the interact method of the virtual pet game by feeding the pet.
   */
    @Test
    public void testInteractFeed() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertEquals(100, pet.getNeedLevels().get(Need.HUNGER).intValue());
      sleep(3000);
      pet.interact(Need.HUNGER);
      assertEquals(95, pet.getNeedLevels().get(Need.HUNGER).intValue());
    }

  /**
   * Tests the interact method of the virtual pet game by playing with the pet.
   */
  @Test
  public void testInteractPlay() {
    GameTimer timer = new GameTimerImpl();
    VirtualPet pet = new VirtualPetImpl("Test", timer);
    assertEquals(100, pet.getNeedLevels().get(Need.SOCIAL).intValue());
    sleep(3000);
    pet.interact(Need.SOCIAL);
    assertEquals(90, pet.getNeedLevels().get(Need.SOCIAL).intValue());
  }

  /**
   * Tests the interact method of the virtual pet game by cleaning the pet.
   */
  @Test
    public void testInteractClean() {
    GameTimer timer = new GameTimerImpl();
        VirtualPet pet = new VirtualPetImpl("Test", timer);
        assertEquals(100, pet.getNeedLevels().get(Need.HYGIENE).intValue());
        sleep(3000);
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
        sleep(3000);
        pet.interact(Need.ENERGY);
        assertEquals(92, pet.getNeedLevels().get(Need.ENERGY).intValue());
    }

  /**
   * Tests that the mood changes to a single low need correctly.
   */
  @Test
    public void testUpdateMoodSingleLowNeed() {
    GameTimer timer = new GameTimerImpl();
    VirtualPet pet = new VirtualPetImpl("Test", timer);
    assertEquals(Mood.HAPPY, pet.getMoodState());
    sleep(6000);
    assertEquals(Mood.TIRED, pet.getMoodState());
    pet.interact(Need.ENERGY);
    assertEquals(Mood.BORED, pet.getMoodState());
    pet.interact(Need.SOCIAL);
    pet.interact(Need.SOCIAL);
    sleep(4000);
    pet.interact(Need.ENERGY);
    pet.interact(Need.ENERGY);
    pet.interact(Need.SOCIAL);
    pet.interact(Need.SOCIAL);
    assertEquals(Mood.HUNGRY, pet.getMoodState());
    pet.interact(Need.HUNGER);
    assertEquals(Mood.DIRTY, pet.getMoodState());
    }

  /**
   * Test that the pet becomes happy after its need is met.
   */
  @Test
  public void testMoodChangesBackToHappy() {
    GameTimer timer = new GameTimerImpl();
    VirtualPet pet = new VirtualPetImpl("Test", timer);
    assertEquals(Mood.HAPPY, pet.getMoodState());
    sleep(6000);
    timer.stop();
    HashMap<Need, Integer> needs = pet.getNeedLevels();
    assertEquals(64, needs.get(Need.ENERGY).intValue());
    assertEquals(Mood.TIRED, pet.getMoodState());
    pet.interact(Need.ENERGY);
    assertEquals(Mood.BORED, pet.getMoodState());
    pet.interact(Need.SOCIAL);
    pet.interact(Need.ENERGY);
    assertEquals(Mood.HAPPY, pet.getMoodState());
  }

  /**
   * Test that the pet becomes sad correctly.
   */
  @Test
    public void testUpdateMoodSad() {
    GameTimer timer = new GameTimerImpl();
        VirtualPet pet = new VirtualPetImpl("Test", timer);
        assertEquals(Mood.HAPPY, pet.getMoodState());
        sleep(6000);
        assertEquals(Mood.TIRED, pet.getMoodState());
        sleep(10000);
        assertEquals(Mood.SAD, pet.getMoodState());
    }

  /**
   * Test that the pet becomes sick correctly.
   */
  @Test
    public void testPetIllness() {
    GameTimer timer = new GameTimerImpl();
        VirtualPet pet = new VirtualPetImpl("Test", timer);
        assertEquals(Health.HEALTHY, pet.getHealthState());
        sleep(6000);
        assertEquals(Health.HEALTHY, pet.getHealthState());
        sleep(10000);
        assertEquals(Health.SICK, pet.getHealthState());
    }

  /**
   * Test that the pet becomes healthy after its needs are met.
   */
    @Test
    public void testPetHealthChangesBackToHealthy() {
      GameTimer timer = new GameTimerImpl();
      VirtualPet pet = new VirtualPetImpl("Test", timer);
      assertEquals(Health.HEALTHY, pet.getHealthState());
      sleep(15000);
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
   * Test that the pet's need levels decrease as scheduled correctly.
   */
  @Test
    public void testDecreaseNeedsOverTime() {
    GameTimer timer = new GameTimerImpl();
    VirtualPet pet = new VirtualPetImpl("Test", timer);
    assertEquals(100, pet.getNeedLevels().get(Need.HUNGER).intValue());
    sleep(3000);
    assertEquals(90, pet.getNeedLevels().get(Need.HUNGER).intValue());
    assertEquals(85, pet.getNeedLevels().get(Need.SOCIAL).intValue());
    assertEquals(91, pet.getNeedLevels().get(Need.HYGIENE).intValue());
    assertEquals(82, pet.getNeedLevels().get(Need.ENERGY).intValue());
    sleep(3000);
    assertEquals(80, pet.getNeedLevels().get(Need.HUNGER).intValue());
    assertEquals(70, pet.getNeedLevels().get(Need.SOCIAL).intValue());
    assertEquals(82, pet.getNeedLevels().get(Need.HYGIENE).intValue());
    assertEquals(64, pet.getNeedLevels().get(Need.ENERGY).intValue());
}

  /**
   * Test that life stage changes after the appropriate amount of time has passed.
   */
  @Test
  public void testLifeStageChanges() {
    GameTimer testingTimer = new TestingTimer();
    VirtualPet pet = new VirtualPetImpl("Test", testingTimer);
    assertEquals("Puppy", pet.getLifeStage().toString());
    pet.update();
    pet.update();
    pet.update();
    assertEquals("Adult", pet.getLifeStage().toString());
  }


  /**
   * Test that the life stage does not change if the pet is not healthy and happy.
   */
  //TO DO
    @Test
    public void testLifeStageDoesNotChange() {
      GameTimer testingTimer = new TestingTimer();
        VirtualPet pet = new VirtualPetImpl("Test", testingTimer);
        assertEquals("Puppy", pet.getLifeStage().toString());
        assertEquals(Health.HEALTHY, pet.getHealthState());
        assertEquals(Mood.HAPPY, pet.getMoodState());
        sleep(6000);
        pet.update();
        pet.update();
        pet.update();
        assertEquals(Mood.TIRED, pet.getMoodState());
        assertEquals("Puppy", pet.getLifeStage().toString());
    }

  /**
   * Helper method to keep the pet alive during puppy stage.
   */
  private void keepPuppyAlive(VirtualPet pet) {
    for (int i = 0; i < 6; i++) {
      pet.interact(Need.HUNGER);
    }

    for (int i = 0; i < 11; i++) {
      pet.interact(Need.SOCIAL);
    }

    for (int i = 0; i < 6; i++) {
      pet.interact(Need.HYGIENE);
    }

    for (int i = 0; i < 10; i++) {
      pet.interact(Need.ENERGY);
    }
  }

  /**
   * Test that the pet's need levels decrease corresponding to the life stage correctly.
   */
  //TO DO
  @Test
  public void testDecreaseNeedsOverTimeLifeStage() {
    GameTimer timer = new GameTimerImpl();
    VirtualPet pet = new VirtualPetImpl("Test", timer);
    HashMap<Need, Integer> needLevels = pet.getNeedLevels();
    for (Need need : needLevels.keySet()) {
      assertEquals(100, needLevels.get(need).intValue());
    }
    try {
      Thread.sleep(4000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    for (Need need : needLevels.keySet()) {
      assertEquals(85, needLevels.get(need).intValue());
    }
    try {
      Thread.sleep(3000); // wait 3 seconds for scheduled decrease to run
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    for (Need need : needLevels.keySet()) {
      assertEquals(70, needLevels.get(need).intValue());
    }

  }

  /**
   * Test that the pet dies when its health reaches 0.
   */
  @Test
    public void testPetDeath() {
    GameTimer timer = new GameTimerImpl();
        VirtualPet pet = new VirtualPetImpl("Test", timer);
        assertEquals(Health.HEALTHY, pet.getHealthState());
        sleep(18000);
        assertEquals(Health.DEAD, pet.getHealthState());
    }

  /**
   * Test that the player cannot interact with the pet after it dies.
   */
    @Test (expected = IllegalStateException.class)
        public void testActionWhenDead() {
        GameTimer timer = new GameTimerImpl();
            VirtualPet pet = new VirtualPetImpl("Test", timer);
            assertEquals(Health.HEALTHY, pet.getHealthState());
            sleep(18000);
            assertEquals(Health.DEAD, pet.getHealthState());
            pet.interact(Need.HUNGER);
        }

  /**
   * Test that petDeath method throws exception when pet is not dead.
   */
  @Test (expected = IllegalStateException.class)
    public void testPetDeathWhenNotDead() {
    GameTimer timer = new GameTimerImpl();
    VirtualPet pet = new VirtualPetImpl("Test", timer);
    assertEquals(Health.HEALTHY, pet.getHealthState());
    pet.petDeath();
    }
  /**
   * Test that the player cannot perform an action when the pet's need is already full.
   */
  @Test (expected = IllegalArgumentException.class)
    public void testActionWhenNeedFull() {
    GameTimer timer = new GameTimerImpl();
        VirtualPet pet = new VirtualPetImpl("Test", timer);
        assertEquals(100, pet.getNeedLevels().get(Need.HUNGER).intValue());
        pet.interact(Need.HUNGER);
    }

    class TestingTimer implements GameTimer {
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
