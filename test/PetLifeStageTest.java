import static org.junit.Assert.assertEquals;

import org.junit.Test;
import virtualpet.AbstractLifeStage;
import virtualpet.Adult;
import virtualpet.Need;
import virtualpet.PetLifeStage;
import virtualpet.Puppy;
import virtualpet.Senior;

/**
 * JUnit test class for PetLifeStage.
 */
public class PetLifeStageTest {

  /**
   * Tests getInterval method for Puppy life stage.
   */
  @Test
  public void testGetIntervalPuppy() {
    PetLifeStage puppy = new Puppy();
    assertEquals(3000, puppy.getInterval());
  }

  /**
   * Tests getInterval method for Adult life stage.
   */
  @Test
  public void testGetIntervalAdult() {
    PetLifeStage adult = new Adult();
    assertEquals(5000, adult.getInterval());
  }

  /**
   * Tests getInterval method for Senior life stage.
   */
  @Test
  public void testGetIntervalSenior() {
    PetLifeStage senior = new Senior();
    assertEquals(8000, senior.getInterval());
  }

  /**
   * Tests increaseNeed method for all needs for Puppy life stage.
   */
  @Test
  public void testIncreaseNeedPuppy() {
    int currentLevel = 50;
    PetLifeStage puppy = new Puppy();
    assertEquals(55, puppy.increaseNeed(Need.HUNGER, currentLevel));
    assertEquals(55, puppy.increaseNeed(Need.SOCIAL, currentLevel));
    assertEquals(59, puppy.increaseNeed(Need.HYGIENE, currentLevel));
    assertEquals(55, puppy.increaseNeed(Need.ENERGY, currentLevel));
  }

  /**
   * Tests increaseNeed method for all needs for Adult life stage.
   */
  @Test
  public void testIncreaseNeedAdult() {
    int currentLevel = 50;
    PetLifeStage adult = new Adult();
    assertEquals(60, adult.increaseNeed(Need.HUNGER, currentLevel));
    assertEquals(60, adult.increaseNeed(Need.SOCIAL, currentLevel));
    assertEquals(55, adult.increaseNeed(Need.HYGIENE, currentLevel));
    assertEquals(60, adult.increaseNeed(Need.ENERGY, currentLevel));
  }

  /**
   * Tests increaseNeed method for all needs for Senior life stage.
   */
  @Test
  public void testIncreaseNeedSenior() {
    int currentLevel = 50;
    PetLifeStage senior = new Senior();
    assertEquals(55, senior.increaseNeed(Need.HUNGER, currentLevel));
    assertEquals(55, senior.increaseNeed(Need.SOCIAL, currentLevel));
    assertEquals(55, senior.increaseNeed(Need.HYGIENE, currentLevel));
    assertEquals(60, senior.increaseNeed(Need.ENERGY, currentLevel));
  }

  /**
   * Tests decreaseNeed method for all needs for Puppy life stage.
   */
  @Test
  public void testDecreaseNeedPuppy() {
    int currentLevel = 50;
    PetLifeStage puppy = new Puppy();
    assertEquals(40, puppy.decreaseNeed(Need.HUNGER, currentLevel));
    assertEquals(35, puppy.decreaseNeed(Need.SOCIAL, currentLevel));
    assertEquals(41, puppy.decreaseNeed(Need.HYGIENE, currentLevel));
    assertEquals(40, puppy.decreaseNeed(Need.ENERGY, currentLevel));
  }

  /**
   * Tests decreaseNeed method for all needs for Adult life stage.
   */
  @Test
  public void testDecreaseNeedAdult() {
    int currentLevel = 50;
    PetLifeStage adult = new Adult();
    assertEquals(40, adult.decreaseNeed(Need.HUNGER, currentLevel));
    assertEquals(40, adult.decreaseNeed(Need.SOCIAL, currentLevel));
    assertEquals(45, adult.decreaseNeed(Need.HYGIENE, currentLevel));
    assertEquals(40, adult.decreaseNeed(Need.ENERGY, currentLevel));
  }

  /**
   * Tests decreaseNeed method for all needs for Senior life stage.
   */
  @Test
  public void testDecreaseNeedSenior() {
    int currentLevel = 50;
    PetLifeStage senior = new Senior();
    assertEquals(42, senior.decreaseNeed(Need.HUNGER, currentLevel));
    assertEquals(45, senior.decreaseNeed(Need.SOCIAL, currentLevel));
    assertEquals(45, senior.decreaseNeed(Need.HYGIENE, currentLevel));
    assertEquals(35, senior.decreaseNeed(Need.ENERGY, currentLevel));
  }

  /**
   * Tests increaseNeed method returns 100 if the new increased level goes above 100.
   */
  @Test
  public void testIncreaseNeedMax() {
    int currentLevel = 97;
    PetLifeStage puppy = new Puppy();
    assertEquals(100, puppy.increaseNeed(Need.HUNGER, currentLevel));
  }

  /**
   * Tests decreaseNeed method returns 0 if the new decreased level is below 0.
   */
  @Test
  public void testDecreaseNeed() {
    int currentLevel = 3;
    PetLifeStage senior = new Senior();
    assertEquals(0, senior.decreaseNeed(Need.HUNGER, currentLevel));
  }

  /**
   * Tests toString method for Puppy life stage.
   */
  @Test
  public void testToStringPuppy() {
    PetLifeStage puppy = new Puppy();
    assertEquals("Puppy", puppy.toString());
  }

  /**
   * Tests toString method for Adult life stage.
   */
  @Test
  public void testToStringAdult() {
    PetLifeStage adult = new Adult();
    assertEquals("Adult", adult.toString());
  }

  /**
   * Tests toString method for Senior life stage.
   */
  @Test
  public void testToStringSenior() {
    PetLifeStage senior = new Senior();
    assertEquals("Senior", senior.toString());
  }

  /**
   * Tests increaseNeed throws IllegalArgumentException if currentLevel is above 100.
   */
  @Test(expected = IllegalArgumentException.class)
  public void increaseNeedException() {
    int currentLevel = 101;
    PetLifeStage puppy = new Puppy();
    puppy.increaseNeed(Need.HUNGER, currentLevel);
  }

  /**
   * Tests decreaseNeed throws IllegalArgumentException if currentLevel is below 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void decreaseNeedException() {
    int currentLevel = -5;
    PetLifeStage puppy = new Puppy();
    puppy.decreaseNeed(Need.HUNGER, currentLevel);
  }

    /**
     * Tests constructor throws IllegalArgumentException if increaseRates and decreaseRates are not
     * the same length.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRatesDifferentLengthThanNeeds() {
      PetLifeStage test = new TestingLifeStage();
    }

    static class TestingLifeStage extends AbstractLifeStage {
      private static final int[] INCREASE_RATES = {1, 2, 3};
      private static final int[] DECREASE_RATES = {1, 2, 3, 4};
      private static final long INTERVAL = 1000;
      public TestingLifeStage() {
        super(INCREASE_RATES, DECREASE_RATES, INTERVAL);
      }
    }
}