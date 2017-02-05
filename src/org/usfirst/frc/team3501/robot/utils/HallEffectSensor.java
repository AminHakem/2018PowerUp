package org.usfirst.frc.team3501.robot.utils;

import edu.wpi.first.wpilibj.Counter;

public class HallEffectSensor {
  private Counter counter;

  public HallEffectSensor(int port, int bufferLength) {
    counter = new Counter(port);
    counter.setSamplesToAverage(bufferLength);
  }

  /**
   * Returns rotations per second(buffered) of hall effect sensor counter
   * 
   * @return rotations per second of hall effect counter
   */
  public double getRPS() {
    return 1.0 / counter.getPeriod();
  }

  /**
   * Get the period of the most recent count.
   * 
   * @return period of latest count in seconds
   */
  public double getCounterPeriod() {
    return counter.getPeriod();
  }

  /**
   * Returns rotations per minute(buffered) of hall effect sensor counter
   * 
   * @return rotations per minute of hall effect sensor
   */
  public double getRPM() {
    return this.getRPS() * 60;
  }

}
