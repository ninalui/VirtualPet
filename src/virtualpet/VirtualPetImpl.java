package virtualpet;

import java.util.HashMap;
import java.util.TimerTask;

/**
 * This class represents a virtual pet. It implements the VirtualPet interface. A virtual pet
 * has a name, mood state, health state, life stage, and need levels. The mood state and health
 * state are determined by the pet's need levels. The life stage is determined by the pet's age.
 * Need levels decrease over time at rates determined by the pet's life stage. Need levels increase
 * when the pet is interacted with. If one of the need levels hits 0, then the pet dies and the
 * game is over. If the pet is healthy and happy, then when the appropriate amount of time has
 * passed, the pet grows and enters a new life stage. It uses PetLifeStage for the life stage and
 * GameTimer to keep track of time and schedule the pet's needs to decrease over time.
 */
public class VirtualPetImpl implements VirtualPet {
  private String name;
  private Mood moodState;
  private Health healthState;
  private int health;
  private PetLifeStage lifeStage;
  private final HashMap<Need, Integer> needLevels;
  private final GameTimer timer;
  private TimerTask timedDecrease;
  private boolean running;

  /**
   * Creates a new virtual pet. It initializes the pet's name, mood state, health state, life stage,
   * and need levels, and schedules the pet's needs to decrease over time.
   *
   * @param name   the name of the pet.
   * @param timer  the timer used to keep track of time.
   * @throws IllegalArgumentException if either the name or the timer is null.
   */
  public VirtualPetImpl(String name, GameTimer timer) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null.");
    }
    if (timer == null) {
      throw new IllegalArgumentException("Timer cannot be null.");
    }
    this.name = name;
    this.moodState = Mood.HAPPY;
    this.healthState = Health.HEALTHY;
    this.health = 100;
    this.lifeStage = new Child();
    this.needLevels = initializeNeedLevels();
    this.timer = timer;
    this.running = true;
    decreaseNeedsOverTime();
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public long getAge() {
    return this.timer.getElapsedTime() / 60;
  }

  @Override
  public Mood getMoodState() {
    return this.moodState;
  }

  @Override
  public Health getHealthState() {
    return this.healthState;
  }

  @Override
  public int getHealth() {
    return this.health;
  }

  @Override
  public PetLifeStage getLifeStage() {
    return this.lifeStage;
  }

  @Override
  public HashMap<Need, Integer> getNeedLevels() {
    return new HashMap<>(this.needLevels);
  }

  @Override
  public void interact(Need need) throws IllegalStateException, IllegalArgumentException {
    if (this.getHealthState() == Health.DEAD) {
      throw new IllegalStateException("The pet is dead.");
    } else if (need == null || !this.needLevels.containsKey(need)) {
      throw new IllegalArgumentException("Invalid need.");
    } else if (this.needLevels.get(need) == 100) {
      throw new IllegalArgumentException("Need level is already full.");
    }
    int increasedLevel = this.lifeStage.increaseNeed(need, this.needLevels.get(need));
    this.needLevels.replace(need, increasedLevel);
    this.update();
  }

  @Override
  public void update() {
    this.updateMood();
    this.updateHealth();
    this.updateLifeStage();
  }

  @Override
  public boolean isAlive() {
    return this.healthState != Health.DEAD;
  }

  @Override
  public void pauseTimer() {
    if (running) {
      this.running = false;
    } else {
      this.running = true;
    }
  }

  @Override
  public String toString() {
    StringBuilder needs = new StringBuilder();
    needs.append(Need.HUNGER).append(": ").append(this.needLevels.get(Need.HUNGER))
        .append("\n");
    needs.append(Need.SOCIAL).append(": ").append(this.needLevels.get(Need.SOCIAL))
        .append("\n");
    needs.append(Need.HYGIENE).append(": ").append(this.needLevels.get(Need.HYGIENE))
        .append("\n");
    needs.append(Need.ENERGY).append(": ").append(this.needLevels.get(Need.ENERGY))
        .append("\n");

    return "Name: " + this.name + "\n"
        + "Age: " + this.getAge() + "\n"
        + "Mood: " + this.moodState.toString() + "\n"
        + "Health: " + this.healthState.toString() + "\n"
        + "Life Stage: " + this.lifeStage.toString() + "\n"
        + "Need Levels:\n" + needs;
  }

  /**
   * Creates a HashMap with all the pet's needs and initializes their levels to 100.
   *
   * @return a HashMap with all the needs and their corresponding levels.
   */
  private HashMap<Need, Integer> initializeNeedLevels() {
    HashMap<Need, Integer> needLevels = new HashMap<>();
    for (Need need : Need.values()) {
      needLevels.put(need, 100);
    }
    return needLevels;
  }

  /**
   * Schedules the needs of the pet to decrease over time.
   */
  private void decreaseNeedsOverTime() {
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        if (running) {
          for (Need need : needLevels.keySet()) {
            int decreasedLevel = lifeStage.decreaseNeed(need, needLevels.get(need));
            needLevels.replace(need, decreasedLevel);
          }
          update();
        }
      }
    };
    this.timer.scheduleTask(task, lifeStage.getInterval());
  }

  /**
   * Updates the mood state of the virtual pet. If any of the pet's needs drop below 75, then the
   * pet's mood changes to correspond with the need with the lowest level. If more than one of
   * the needs hits 25 or below, then the pet is sad. Otherwise, the pet is happy.
   */
  private void updateMood() {
    int numNeedsBelow25 = 0;
    Need minNeed = null;

    for (Need need : this.needLevels.keySet()) {
      if (this.needLevels.get(need) < 75) {
        if (minNeed == null || this.needLevels.get(need) < this.needLevels.get(minNeed)) {
          minNeed = need;
        }
      }
      if (this.needLevels.get(need) <= 25) {
        numNeedsBelow25++;
      }
    }

    if (numNeedsBelow25 > 1) {
      this.moodState = Mood.SAD;
    } else if (minNeed != null) {
      switch (minNeed) {
        case HUNGER:
          this.moodState = Mood.HUNGRY;
          break;
        case SOCIAL:
          this.moodState = Mood.BORED;
          break;
        case HYGIENE:
          this.moodState = Mood.DIRTY;
          break;
        case ENERGY:
          this.moodState = Mood.TIRED;
          break;
        default:
          throw new IllegalArgumentException("Invalid need.");
      }
    } else {
      this.moodState = Mood.HAPPY;
    }
  }

  /**
   * Updates the health state of the virtual pet. If any of the needs hits 0, then the pet is
   * dead. If the health of the pet is less than 50, then the pet is sick. Otherwise, the pet is
   * healthy.
   */
  private void updateHealth() {
    healthCheck();
    for (Need need : this.needLevels.keySet()) {
      if (this.needLevels.get(need) == 0) {
        this.healthState = Health.DEAD;
        this.health = 0;
        this.petDeath();
        return;
      }
    }

    if (this.health < 50) {
      this.healthState = Health.SICK;
    } else {
      this.healthState = Health.HEALTHY;
    }
  }

  /**
   * Performs a health check on the virtual pet. The health of the pet is determined by the average
   * of all the need levels, with a maximum of 100 and a minimum of 0.
   */
  private void healthCheck() {
    int totalNeedLevel = 0;
    for (int needLevel : this.needLevels.values()) {
      totalNeedLevel += needLevel;
    }
    this.health = totalNeedLevel / this.needLevels.size();
  }

  /**
   * Updates the life stage of the virtual pet. If the pet is healthy and happy, then when the
   * appropriate amount of time has passed, the pet grows and enters a new life stage. At 2 minutes,
   * the pet can become an adult, and at 5 minutes, the pet can become a senior.
   */
  private void updateLifeStage() {
    if (this.healthState != Health.HEALTHY || this.moodState != Mood.HAPPY) {
      return;
    }

    long age = this.getAge();
    if (this.lifeStage instanceof Child && age >= 2) {
      this.lifeStage = new Adult();
    } else if (this.lifeStage instanceof Adult && age >= 5) {
      this.lifeStage = new Senior();
    }
  }

  /**
   * Ends game and stops timer when pet dies.
   *
   * @throws IllegalStateException if pet is not dead.
   */
  private void petDeath() throws IllegalStateException {
    if (this.healthState != Health.DEAD) {
      throw new IllegalStateException("Pet is not dead.");
    }
    this.timer.stop();
  }
}
