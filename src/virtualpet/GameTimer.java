package virtualpet;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
  Timer timer;
  int timeInterval;
  long startTime;
  VirtualPet pet;

  public GameTimer(int timeInterval, VirtualPet pet) {
    this.startTime = System.currentTimeMillis();
    this.pet = pet;
    this.timer = new Timer();
    this.timeInterval = timeInterval;
    scheduleNeedsDecrease();
  }

  private void scheduleNeedsDecrease() {
    this.timer.schedule(new TimerTask() {
      @Override
      public void run() {
        System.out.println("Decreasing need levels");
        pet.decreaseNeedLevels();
      }
    }, 5000, this.timeInterval);
  }
  public long getElapsedTime() {
    return System.currentTimeMillis() - startTime;
  }
}
