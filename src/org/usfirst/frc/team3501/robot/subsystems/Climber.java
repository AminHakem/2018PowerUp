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
  private static WPI_TalonSRX winch;
  private static Solenoid dropPiston;

  private Climber() {
    // MOTOR CONTROLLER
    winch = new WPI_TalonSRX(Constants.Climber.WINCH_SPEED);
    dropPiston = new Solenoid(Constants.Climber.PISTON_MODULE,
        Constants.Climber.RAMP_RELEASE);
  }

  public static WPI_TalonSRX getWinch() {
    return winch;
  }

  public static Climber getClimber() {
    if (climber == null) {
      climber = new Climber();
    }
    return climber;
  }

  public static void setMotorValues() {
    winch.set(ControlMode.PercentOutput, Constants.Climber.WINCH_SPEED);
  }

  public static void setMotorValues(double speed) {
    speed = MathLib.restrictToRange(speed, -1.0, 1.0);
    winch.set(ControlMode.PercentOutput, speed);
  }

  public Solenoid getDropPiston() {
    return dropPiston;
  }

  public static void extendPiston() {
    boolean on = true;
    dropPiston.set(on);
  }

  public static void retractPiston() {
    boolean off = false;
    dropPiston.set(off);

  }

  public static void stop() {
    setMotorValues(0);
  }

  @Override
  public void initDefaultCommand() {
  }

}
