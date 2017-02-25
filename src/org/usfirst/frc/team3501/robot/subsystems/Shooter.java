package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;
import org.usfirst.frc.team3501.robot.utils.HallEffectSensor;
import org.usfirst.frc.team3501.robot.utils.PIDController;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
  public double wheelP = 0.00007, wheelI = 0, wheelD = 0.0008;
  private static Shooter shooter;
  private static HallEffectSensor hallEffect;
  private final CANTalon flyWheel1, flyWheel2, indexWheel;

  private PIDController wheelController;

  private static final double RPM_THRESHOLD = 10;
  private static final double DEFAULT_INDEXING_MOTOR_VALUE = 0.75;
  private static final double DEFAULT_SHOOTING_SPEED = 2800; // rpm
  private static final double SHOOTING_SPEED_INCREMENT = 50;

  private double targetShootingSpeed = DEFAULT_SHOOTING_SPEED;
  private double currentShooterMotorValue = 0;

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

  public double getRPMThreshold() {
    return RPM_THRESHOLD;
  }

  public double getShooterRPM() {
    return hallEffect.getRPM();
  }

  public void setTargetShootingSpeed(double Value) {
    targetShootingSpeed = Value;
  }

  public void decrementTargetShootingSpeed() {
    this.targetShootingSpeed -= this.SHOOTING_SPEED_INCREMENT;
  }

  public void incrementTargetShootingSpeed() {
    this.targetShootingSpeed += this.SHOOTING_SPEED_INCREMENT;
  }

  public void resetTargetShootingSpeed() {
    this.targetShootingSpeed = this.DEFAULT_SHOOTING_SPEED;
  }

  public double getTargetShootingSpeed() {
    return targetShootingSpeed;
  }

  public void reverseIndexWheel() {
    this.setIndexWheelMotorVal(-DEFAULT_INDEXING_MOTOR_VALUE);
  }

  public void runIndexWheel() {
    this.setIndexWheelMotorVal(DEFAULT_INDEXING_MOTOR_VALUE);
  }

  public double calculateShooterSpeed() {
    this.wheelController.setSetPoint(targetShootingSpeed);
    double calculatedShooterIncrement = this.wheelController
        .calcPID(this.getShooterRPM());
    currentShooterMotorValue += calculatedShooterIncrement;
    return currentShooterMotorValue;
  }

  public void initializePIDController() {
    this.wheelController = new PIDController(wheelP, wheelI, wheelD);
    this.wheelController.setDoneRange(10);
    this.wheelController.setMaxOutput(1.0);
    this.wheelController.setMinDoneCycles(3);
    this.wheelController.setSetPoint(this.targetShootingSpeed);
    this.currentShooterMotorValue = 0;
  }
}
