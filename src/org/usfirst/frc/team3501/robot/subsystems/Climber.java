package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author: niyatisriram Climber with motors that drive winch to move robot up to climb Ramp that
 *          lifts other robots
 */
public class Climber extends Subsystem {
  private static Climber climber;
  private static WPI_TalonSRX rightWinch;
  private static WPI_TalonSRX leftWinch;
  private static Solenoid rampSolenoid;

  private static boolean on = true, off = false;

  public static final int WINCH_SPEED = 0;
  private static final double SERVO_ANGLE = 0;

  private Climber() {
    // MOTOR CONTROLLER
    rightWinch = new WPI_TalonSRX(Constants.Climber.RIGHT_WINCH);
    leftWinch = new WPI_TalonSRX(Constants.Climber.LEFT_WINCH);
    rightWinch.setNeutralMode(NeutralMode.Brake);
    leftWinch.setNeutralMode(NeutralMode.Brake);

    rampSolenoid = new Solenoid(Constants.Climber.PISTON_CHANNEL);
  }

  public static Climber getClimber() {
    if (climber == null) {
      climber = new Climber();
    }
    return climber;
  }

  public static void setMotorValues(double motorVal) {
    rightWinch.set(ControlMode.PercentOutput, motorVal);
    leftWinch.set(ControlMode.PercentOutput, motorVal);
  }

  public static void extendPiston() {
    rampSolenoid.set(true);
  }

  public static void retractPiston() {
    rampSolenoid.set(false);
  }

  public static void stop() {
    setMotorValues(0);
  }

  @Override
  public void initDefaultCommand() {}
}
