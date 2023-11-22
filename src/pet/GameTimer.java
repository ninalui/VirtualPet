package pet;

/**
 * This interface represents a timer for a virtual pet game. The timer is used to keep track of the time
 * passed in the game and to schedule a task to decrease the pet's need levels at a given interval.
 */
public interface GameTimer {

  /**
   * Schedule a task to perform at a given interval.
   * @param task     the task to perform.
   * @param interval  the interval at which to perform the task.
   */
  void scheduleTask(TimerTask task, long interval);

  /**
   * Stops the timer.
   */
  void stop();

  /**
   * Gets the amount of time that has passed since the timer was started.
   */
  long getElapsedTime();


}
