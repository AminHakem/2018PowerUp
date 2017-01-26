package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.MathLib;
import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.utils.PIDController;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command makes the robot drive a specified distance using encoders on the
 * robot and using a feedback loop
 *
 * parameters: distance the robot will move in inches motorVal: the motor input
 * to set the motors to
 */
public class DriveDistance extends Command {
  private DriveTrain driveTrain = Robot.getDriveTrain();
  private double maxTimeOut;
  private PIDController driveController;
  private PIDController gyroController;
  private Preferences prefs;

  private double target;
  private double gyroP;
  private double gyroI;
  private double gyroD;

  private double driveP;
  private double driveI;
  private double driveD;

  public DriveDistance(double distance, double motorVal) {
    requires(driveTrain);
    this.driveP = driveTrain.driveP;
    this.driveI = driveTrain.driveI;
    this.driveD = driveTrain.driveD;
    this.gyroP = driveTrain.defaultGyroP;
    this.gyroI = driveTrain.defaultGyroI;
    this.gyroD = driveTrain.defaultGyroD;
    this.driveController = new PIDController(this.driveP, this.driveI,
        this.driveD);
    this.driveController.setDoneRange(0.5);
    this.driveController.setMaxOutput(1.0);
    this.driveController.setMinDoneCycles(5);

    this.gyroController = new PIDController(this.gyroP, this.gyroI, this.gyroD);
    this.gyroController.setDoneRange(1);
    this.gyroController.setMinDoneCycles(5);
  }

  @Override
  protected void initialize() {
    this.driveTrain.resetEncoders();
    this.driveTrain.resetGyro();
    this.driveController.setSetPoint(this.target);
    this.gyroController.setSetPoint(this.driveTrain.getZeroAngle());
  }

  @Override
  protected void execute() {
    double xVal = 0;
    double yVal = this.driveController
        .calcPID(this.driveTrain.getAvgEncoderDistance());

    if (this.driveTrain.getAngle() - this.driveTrain.getZeroAngle() < 30) {
      xVal = -this.gyroController
          .calcPID(this.driveTrain.getAngle() - this.driveTrain.getZeroAngle());
    }
    // System.out.println("turn: " + xVal);
    double leftDrive = MathLib.calcLeftTankDrive(-xVal, yVal);
    double rightDrive = MathLib.calcRightTankDrive(xVal, -yVal);

    this.driveTrain.setMotorValues(leftDrive, rightDrive);

    System.out.println(driveTrain.getAvgEncoderDistance());
    // System.out.println("motorval: " + yVal);
  }

  @Override
  protected boolean isFinished() {
    boolean isDone = this.driveController.isDone();
    if (timeSinceInitialized() >= maxTimeOut || isDone)
      System.out.println("time: " + timeSinceInitialized());
    return timeSinceInitialized() >= maxTimeOut || isDone;
  }

  @Override
  protected void end() {
    driveTrain.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
