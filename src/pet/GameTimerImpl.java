package pet;

import java.util.Timer;

public class GameTimerImpl implements GameTimer {
  private final VirtualPet pet;
  private final Timer timer;
  private final long startTime;


  public GameTimerImpl(VirtualPet pet) {
    this.pet = pet;
    this.startTime = System.currentTimeMillis();
    this.timer = new Timer();
  }

  @Override
  public void scheduleNeedsDecrease() {


  }

  @Override
  public void stop() {
    this.timer.cancel();
  }

  @Override
  public long getElapsedTime() {
    return System.currentTimeMillis() - startTime;
  }
}
