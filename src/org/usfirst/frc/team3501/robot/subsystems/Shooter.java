package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;
import org.usfirst.frc.team3501.robot.utils.HallEffectSensor;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
  public double wheelP = 0.0001, wheelI = 0, wheelD = 0.0004;
  private static Shooter shooter;
  private static HallEffectSensor hallEffect;
  private final CANTalon flyWheel1, flyWheel2, indexWheel;

  private static final double DEFAULT_INDEXING_SPEED = -0.75;
  private static final double DEFAULT_SHOOTING_SPEED = 2700; // rpm
  private static final double SHOOTING_SPEED_INCREMENT = 25;

  private double currentShootingSpeed = DEFAULT_SHOOTING_SPEED;

  private Shooter() {
    flyWheel1 = new CANTalon(Constants.Shooter.FLY_WHEEL1);
    flyWheel2 = new CANTalon(Constants.Shooter.FLY_WHEEL2);
    indexWheel = new CANTalon(Constants.Shooter.INDEX_WHEEL);

    hallEffect = new HallEffectSensor(Constants.Shooter.HALL_EFFECT_PORT, 1);
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
  public void setFlyWheelMotorVal(double val) {
    val = MathLib.restrictToRange(val, 0.0, 1.0);
    flyWheel1.set(val);
    flyWheel2.set(val);
  }

  /**
   * Stops fly wheel motor.
   */
  public void stopFlyWheel() {
    flyWheel1.set(0);
    flyWheel2.set(0);
  }

  /**
   * Sets index wheel motor value to input.
   *
   * @param val
   *          motor value from -1 to 1(fastest forward)
   */
  public void setIndexWheelMotorVal(double val) {
    val = MathLib.restrictToRange(val, -1.0, 1.0);
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

  public double getShooterRPM() {
    return hallEffect.getRPM();
  }

  public void setCurrentShootingSpeed(double Value) {
    currentShootingSpeed = Value;
  }

  public void decrementCurrentShootingSpeed() {
    this.currentShootingSpeed -= this.SHOOTING_SPEED_INCREMENT;
  }

  public void incrementCurrentShootingSpeed() {
    this.currentShootingSpeed += this.SHOOTING_SPEED_INCREMENT;
  }

  public void resetCurrentShootingSpeed() {
    this.currentShootingSpeed = this.DEFAULT_SHOOTING_SPEED;
  }

  public double getCurrentShootingSpeed() {
    return currentShootingSpeed;
  }

  public void reverseIndexWheel() {
    this.setIndexWheelMotorVal(-DEFAULT_INDEXING_SPEED);
  }

  public void runIndexWheel() {
    this.setIndexWheelMotorVal(DEFAULT_INDEXING_SPEED);
  }
}
