import static org.junit.Assert.assertEquals;

import java.util.TimerTask;
import org.junit.Before;
import org.junit.Test;
import virtualpet.GameTimer;
import virtualpet.GameTimerImpl;

/**
 * JUnit test class for GameTimer.
 */
public class GameTimerTest {

  StringBuilder str;
  TimerTask task;

  /**
   * Setting up a TimerTask to append "Test" to a StringBuilder every time it runs.
   */
  @Before
    public void setUp() {
    str = new StringBuilder();
    task = new TimerTask() {
      @Override
      public void run() {
        str.append("Test");
      }
    };
  }

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
   * Tests getElapsedTime method.
   */
  @Test
  public void testGetElapsedTime() {
    GameTimer timer = new GameTimerImpl();
    assertEquals(0, timer.getElapsedTime());
    sleep(3000);
    assertEquals(3, timer.getElapsedTime());
    sleep(3000);
    assertEquals(6, timer.getElapsedTime());
  }

  /**
   * Tests scheduleTask method.
   */
  @Test
  public void testScheduleTask() {
    GameTimer timer = new GameTimerImpl();
    timer.scheduleTask(task, 1000); // task runs every 1 second after a 2-second delay
    sleep(1000);
    assertEquals("", str.toString());
    sleep(1000);
    assertEquals("Test", str.toString());
    sleep(1000);
    assertEquals("TestTest", str.toString());
  }

  /**
   * Tests stop method.
   */
  @Test
  public void testStop() {
    GameTimer timer = new GameTimerImpl();
    timer.scheduleTask(task, 1000);
    sleep(1000);
    sleep(1000);
    assertEquals("Test", str.toString());
    timer.stop();
    sleep(2000);
    assertEquals("Test", str.toString());
  }
}
