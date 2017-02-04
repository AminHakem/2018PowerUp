package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
  public double wheelP = 0.005, wheelI = 0.001, wheelD = -0.003;
  private static Shooter shooter;
  private final CANTalon flyWheel, indexWheel;

  public static final double DEFAULT_INDEXING_SPEED = 0;
  public static final double DEFAULT_SHOOTING_SPEED = 0;
  public static double CURRENT_SHOOTING_SPEED;

  public static final double SHOOTING_SPEED_INCREMENT = 0;

  private Shooter() {
    flyWheel = new CANTalon(Constants.Shooter.FLY_WHEEL);
    indexWheel = new CANTalon(Constants.Shooter.INDEX_WHEEL);

  }

  /**
   * Returns shooter object
   *
   * @return Shooter object
   */
  public static Shooter getShooter() {
    if (shooter == null) {
      shooter = new Shooter();
    }
    return shooter;
  }

  /**
   * Sets fly wheel motor value to input.
   *
   * @param val
   *          motor value from -1 to 1(fastest forward)
   */
  public void setFlyWheelMotorVal(final double val) {
    flyWheel.set(val);
  }

  /**
   * Stops fly wheel motor.
   */
  public void stopFlyWheel() {
    flyWheel.set(0);
  }

  /**
   * Sets index wheel motor value to input.
   *
   * @param val
   *          motor value from -1 to 1(fastest forward)
   */
  public void setIndexWheelMotorVal(final double val) {
    indexWheel.set(val);
  }

  /**
   * Stops index wheel motor.
   */
  public void stopIndexWheel() {
    indexWheel.set(0);
  }

  @Override
  protected void initDefaultCommand() {

  }

  public double getShooterSpeed() {
    return 1.0;
  }
}
