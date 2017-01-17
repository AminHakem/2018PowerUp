package org.usfirst.frc.team3501.robot.subsystems;

public class Shooter {
  private static Shooter shooter;

  private Shooter() {

  }

  public static Shooter getShooter() {
    if (shooter == null) {
      shooter = new Shooter();
    }
    return shooter;
  }

  /**
   * Stops fly wheel
   */
  public void stopFlywheel() {

  }

  public void setFlyWheelMotorVal(final double val) {

  }

  /**
   * Stops index wheel
   */
  public void stopIndexWheel() {

  }

  public void setIndexWheelMotorVal(final double val) {

  }
}
