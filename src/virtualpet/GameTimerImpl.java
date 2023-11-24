package virtualpet;

import java.time.Duration;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class represents a timer for the game. It implements the GameTimer interface.
 * It uses the java Timer class to schedule tasks and keep track of the elapsed time in seconds.
 */
public class GameTimerImpl implements GameTimer {
  private final Timer timer;
  private final Instant startTime;

  public GameTimerImpl() {
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
    Instant current = Instant.now();
    Duration duration = Duration.between(this.startTime, current);
    return duration.getSeconds();
  }
}
