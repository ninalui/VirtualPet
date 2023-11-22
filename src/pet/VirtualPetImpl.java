package pet;

import java.util.HashMap;
import java.util.TimerTask;

public class VirtualPetImpl implements VirtualPet {
  private final String name;
  private Mood moodState;
  private Health healthState;
  private int health;
  private PetLifeStage lifeStage;
  private final HashMap<Need, Integer> needLevels;
  private final GameTimer timer;

  public VirtualPetImpl(String name) {
    this.name = name;
    this.moodState = Mood.HAPPY;
    this.healthState = Health.HEALTHY;
    this.health = 100;
    this.lifeStage = new Puppy();
    this.needLevels = initializeNeedLevels();
    this.timer = new GameTimerImpl(this);
    decreaseNeedsOverTime();
  }

  private HashMap<Need, Integer> initializeNeedLevels() {
    HashMap<Need, Integer> needLevels = new HashMap<>();
    for (Need need : Need.values()) {
      needLevels.put(need, 100);
    }
    return needLevels;
  }

  @Override
  public String getName() {
    return this.name;
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
  public PetLifeStage getLifeStage() {
    return this.lifeStage;
  }

  @Override
  public HashMap<Need, Integer> getNeedLevels() {
    return this.needLevels;
  }

  @Override
  public void feed() {
    this.lifeStage.increaseNeed(Need.HUNGER, this.needLevels.get(Need.HUNGER));
    this.update();
  }

  @Override
  public void play() {
    this.lifeStage.increaseNeed(Need.SOCIAL, this.needLevels.get(Need.SOCIAL));
    this.update();
  }

  @Override
  public void clean() {
    this.lifeStage.increaseNeed(Need.HYGIENE, this.needLevels.get(Need.HYGIENE));
    this.update();
  }

  @Override
  public void sleep() {
    this.lifeStage.increaseNeed(Need.ENERGY, this.needLevels.get(Need.ENERGY));
    this.update();
  }

  @Override
  public void update() {
    this.updateMood();
    this.updateHealth();
    this.updateLifeStage();
  }

  @Override
  public void petDeath() throws IllegalStateException {
    if (this.healthState != Health.DEAD) {
      throw new IllegalStateException("The pet is not dead.");
    }
    this.timer.stop();
  }

  /**
   * Schedules the needs of the pet to decrease over time.
   */
  private void decreaseNeedsOverTime() {
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        for (Need need : needLevels.keySet()) {
          lifeStage.decreaseNeed(need, needLevels.get(need));
        }
        update();
      }
    };
    long interval = lifestage.getInterval();
    this.timer.scheduleTask(task, interval);
  }

  /**
   * Updates the mood state of the virtual pet. If any of the pet's needs drop below 75, then the
   * pet's mood changes to correspond the need with the lowest level. If more than one of the needs
   * drop below 25, then the pet is sad. Otherwise, the pet is happy.
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
      if (this.needLevels.get(need) < 25) {
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
   * Updates the health state of the virtual pet. If the health of the pet is 0, then the pet is
   * dead. If the health of the pet is less than 50, then the pet is sick. Otherwise, the pet is
   * healthy.
   */
  private void updateHealth() {
    healthCheck();
    if (this.health == 0) {
      this.healthState = Health.DEAD;
      this.petDeath();
    } else if (this.health < 50) {
      this.healthState = Health.SICK;
    } else {
      this.healthState = Health.HEALTHY;
    }
  }

  /**
   * Performs a health check on the virtual pet. The health of the pet is determined by the average
   * of all the need levels.
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
   * appropriate amount of time has passed, the pet grows and enters a new life stage.
   */
  private void updateLifeStage() {
    // if pet is not fully healthy and happy, then do not update life stage
    if (this.moodState != Mood.HAPPY || this.health != 100) {
      return;
    }
    // else pet is healthy and happy
    // check lifestage and age/time elapsed - update if correct
    if (this.lifeStage instanceof Puppy && this.getAge() >= 3) {
      this.lifeStage = new Adult();
    } else if (this.lifeStage instanceof Adult && this.getAge() >= 8) {
      this.lifeStage = new Senior();
    }
  }

  private long getAge() {
    return this.timer.getElapsedTime() / 60000; // time is in milliseconds, dividing by 1 minute
  }
}
