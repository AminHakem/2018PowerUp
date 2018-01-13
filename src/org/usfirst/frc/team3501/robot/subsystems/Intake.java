package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/****
 *
 * @author niyatisriram
 *
 *         TODO: do not make running intake possible unless
 *         elevator is down and ready for a cube
 *
 */

public class Intake extends Subsystem {

  private static Intake intake;
  private static WPI_TalonSRX rightIntake;
  private static WPI_TalonSRX leftIntake;

  public double intakeSpeed = 0;

  private Intake() {
    // MOTOR CONTROLLERS
    leftIntake = new WPI_TalonSRX(Constants.Intake.INTAKE_LEFT);
    rightIntake = new WPI_TalonSRX(Constants.Intake.INTAKE_RIGHT);
  }

  public static Intake getIntake() {
    if (intake == null) {
      intake = new Intake();
    }
    return intake;
  }

  public static void setMotorValues(double motorSpeed) {
    motorSpeed = MathLib.restrictToRange(motorSpeed, -1.0, 1.0);

    leftIntake.set(ControlMode.PercentOutput, motorSpeed);
    rightIntake.set(ControlMode.PercentOutput, -motorSpeed);
  }

  public WPI_TalonSRX getRight() {
    return rightIntake;
  }

  public WPI_TalonSRX getLeft() {
    return leftIntake;
  }

  public static void stop() {
    setMotorValues(0);
  }

  @Override
  protected void initDefaultCommand() {
  }

}
