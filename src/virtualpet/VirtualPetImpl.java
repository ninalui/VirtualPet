package virtualpet;

import java.util.TreeMap;

public class VirtualPetImpl implements VirtualPet {
  private String name;
  private PetLifeStage lifeStage;
  private TreeMap<Need, Integer> needLevels;
  private GameTimer timer;
  private final int timeInterval = 10000;
  private Mood moodState;
  private Health healthState;
  private int health;

  public VirtualPetImpl(String name) {
    this.name = name;
    this.needLevels = initializeNeedLevels();
    this.lifeStage = new Puppy();
    this.timer = new GameTimer(timeInterval, this);
    this.moodState = Mood.HAPPY;
    this.healthState = Health.HEALTHY;
    this.health = 100;
  }

  private TreeMap<Need, Integer> initializeNeedLevels() {
    TreeMap<Need, Integer> needsMap = new TreeMap<Need, Integer>();
    needsMap.put(Need.HUNGER, 100);
    needsMap.put(Need.SOCIAL, 100);
    needsMap.put(Need.HYGIENE, 100);
    needsMap.put(Need.ENERGY, 100);
    return needsMap;
  }

  @Override
  public void action(Need need) throws IllegalArgumentException {
    if (!needLevels.containsKey(need)) {
      throw new IllegalArgumentException("Action provided is not valid");
    }
    Integer increaseNeedLevel = this.lifeStage.increaseNeed(need, needLevels.get(need));
    needLevels.replace(need, increaseNeedLevel);

    updateMoodState();
    updateHealthState();
    updateLifeStage();
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
  public TreeMap<Need, Integer> getNeedLevels() {
    return this.needLevels;
  }

  @Override
  public void decreaseNeedLevels() {
    needLevels.replace(Need.HUNGER, lifeStage.decreaseNeed(Need.HUNGER, needLevels.get(Need.HUNGER)));
    needLevels.replace(Need.SOCIAL, lifeStage.decreaseNeed(Need.SOCIAL, needLevels.get(Need.SOCIAL)));
    needLevels.replace(Need.HYGIENE, lifeStage.decreaseNeed(Need.HYGIENE, needLevels.get(Need.HYGIENE)));
    needLevels.replace(Need.ENERGY, lifeStage.decreaseNeed(Need.ENERGY, needLevels.get(Need.ENERGY)));
  }

  private void updateHealthState() {
    if (this.health < 50) {
      this.healthState = Health.SICK;
    } else if (this.health == 0) {
      this.healthState = Health.DEAD;
    } else {
      this.healthState = Health.HEALTHY;
    }
  }

  /**
   * Update the virtual pet's mood state based on the current need levels.
   * The highest priority need with the lowest level is the one that determines the mood state.
   * Priority of needs: sleep, hunger, social, hygiene
   */
  private void updateMoodState() {
    // lowest level = mood
    needLevels.values().stream().min(Integer::compare);

    for (Need need : needLevels.keySet()) {

      if (checkNeedLow(need)) {
        if (need == Need.ENERGY) {
          this.moodState = Mood.SLEEPY;
          return;
        } else if (need == Need.HUNGER) {
          this.moodState = Mood.HUNGRY;
          return;
        } else if (need == Need.SOCIAL) {
          this.moodState = Mood.BORED;
          return;
        } else if (need == Need.HYGIENE) {
          this.moodState = Mood.SLEEPY;
          return;
        }
      }
    }
    this.moodState = Mood.HAPPY;
    return;
  }

  /**
   * Update the virtual pet to the next life stage if all needs are met and the right amount of
   * time has passed.
   */
  private void updateLifeStage() {
    // if any need is low, do not update life stage
    for (Need need : needLevels.keySet()) {
      if (checkNeedLow(need)) {
        return;
      }
    }

    // checking health is full and age/enough time has passed
    healthCheck();
    int age = getAge();
    if (this.health == 100) {
      if (this.getLifeStage() instanceof Puppy && age > 3) {
        this.lifeStage = new Adult();
      } else if (this.getLifeStage() instanceof Adult && age > 9) {
        this.lifeStage = new Senior();
      }
    }
  }

  private boolean checkNeedLow(Need need) {
    return this.needLevels.get(need) < 50;
  }

  private void healthCheck() {
    int sum = 0;
    for (Need need : needLevels.keySet()) {
      sum += needLevels.get(need);
    }
    this.health = sum/4; // integer division -- rounds down
  }

  private int getAge() {
    return (int) this.timer.getElapsedTime()/60000; // 1 minute = 1 "year"
  }

  @Override
  public String needLevelsToString() {
    String needLevelsString = "";
    for (Need need : needLevels.keySet()) {
      needLevelsString += need.toString() + ": " + needLevels.get(need) + "\n";
    }
    return needLevelsString;
  }
}
