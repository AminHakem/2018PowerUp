package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author: niyatisriram
 *          Climber with motors that drive winch to move robot up to climb
 *          Ramp that lifts other robots
 */
public class Climber extends Subsystem {
  private static Climber climber;
  private static WPI_TalonSRX rightWinch;
  private static WPI_TalonSRX leftWinch;
  private static Solenoid rampPiston;

  public static final int WINCH_SPEED = 0;

  private Climber() {
    // MOTOR CONTROLLER
    rightWinch = new WPI_TalonSRX(Constants.Climber.RIGHT_WINCH);
    leftWinch = new WPI_TalonSRX(Constants.Climber.LEFT_WINCH);
    rampPiston = new Solenoid(Constants.Climber.PISTON_MODULE,
        Constants.Climber.PISTON_CHANNEL);
  }

  public static Climber getClimber() {
    if (climber == null) {
      climber = new Climber();
    }
    return climber;
  }

  public static void runAtDefaultSpeed() {
    rightWinch.set(ControlMode.PercentOutput, WINCH_SPEED);
    leftWinch.set(ControlMode.PercentOutput, WINCH_SPEED);
  }

  public static void runBothAtSpeed(double speed) {
    speed = MathLib.restrictToRange(speed, -1.0, 1.0);
    rightWinch.set(ControlMode.PercentOutput, speed);
    leftWinch.set(ControlMode.PercentOutput, speed);
  }

  public static void setRightMotorVal(double speed) {
    speed = MathLib.restrictToRange(speed, -1.0, 1.0);
    rightWinch.set(ControlMode.PercentOutput, speed);

  }

  public static void setLeftMotorVal(double speed) {
    speed = MathLib.restrictToRange(speed, -1.0, 1.0);
    leftWinch.set(ControlMode.PercentOutput, speed);

  }

  public static WPI_TalonSRX getRightWinch() {
    return rightWinch;
  }

  public static WPI_TalonSRX getLeftWinch() {
    return leftWinch;
  }

  public Solenoid getDropPiston() {
    return rampPiston;
  }

  public static void extendPiston() {
    boolean on = true;
    rampPiston.set(on);
  }

  public static void retractPiston() {
    boolean off = false;
    rampPiston.set(off);

  }

  public static void stop() {
    setRightMotorVal(0.0);
    setLeftMotorVal(0.0);
  }

  @Override
  public void initDefaultCommand() {
  }

}
