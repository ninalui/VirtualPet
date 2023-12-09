package virtualpet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * This class represents an abstract life stage of a pet. It implements the PetLifeStage interface.
 * It contains methods that increase and decrease the pet's needs according to the rates determined
 * by the life stage. It also contains a method that returns the interval of the life stage which
 * determines the frequency the pet's needs decrease.
 */
public class AbstractLifeStage implements PetLifeStage {

  protected HashMap<Need, Integer> needIncreases;
  protected HashMap<Need, Integer> needDecreases;
  protected long interval;

  /**
   * Constructs an AbstractLifeStage object with the given increase and decrease rates.
   *
   * @param increaseRates   list of increase rates.
   * @param decreaseRates   list of decrease rates.
   */
  public AbstractLifeStage(int[] increaseRates, int[]  decreaseRates) {
    ArrayList<Need> needs = initializeNeedsArray();
    this.needIncreases = initializeNeedsMap(needs, increaseRates);
    this.needDecreases = initializeNeedsMap(needs, decreaseRates);
    this.interval = INTERVAL;
  }

  @Override
  public long getInterval() {
    return this.interval;
  }

  @Override
  public int increaseNeed(Need need, Integer currentLevel) throws IllegalArgumentException {
    if (currentLevel >= 100) {
      throw new IllegalArgumentException("Need level cannot be greater than 100.");
    }
    int newLevel = currentLevel + this.needIncreases.get(need);
    return Math.min(newLevel, 100); // return new level or 100, whichever is less (cannot be >100)
  }

  @Override
  public int decreaseNeed(Need need, Integer currentLevel) throws IllegalArgumentException {
    if (currentLevel <= 0) {
      throw new IllegalArgumentException("Need level cannot be less than 0.");
    }
    int newLevel = currentLevel - this.needDecreases.get(need);
    return Math.max(newLevel, 0); // return new level or 0, whichever is greater (cannot be <0)
  }

  /**
   * Creates a HashMap with needs as keys and their corresponding rates as values.
   *
   * @param needs list of needs
   * @param rates list of rates
   * @return a HashMap with needs as keys and their corresponding rates as values.
   * @throws IllegalArgumentException if the list of needs and list of rates are not the same
   *                                  length.
   */
  protected HashMap<Need, Integer> initializeNeedsMap(ArrayList<Need> needs, int[] rates)
      throws IllegalArgumentException {
    HashMap<Need, Integer> needsMap = new HashMap<>();
    if (needs.size() != rates.length) {
      throw new IllegalArgumentException(
          "List of needs and list of rates must be the same length.");
    }
    for (int i = 0; i < needs.size(); i++) {
      needsMap.put(needs.get(i), rates[i]);
    }
    return needsMap;
  }

  /**
   * Creates an ArrayList of all the needs. The needs are based off the Need enum.
   *
   * @return an ArrayList of all the needs.
   */
  protected ArrayList<Need> initializeNeedsArray() {
    ArrayList<Need> needs = new ArrayList<>();
    Collections.addAll(needs, Need.values());
    return needs;
  }
}
