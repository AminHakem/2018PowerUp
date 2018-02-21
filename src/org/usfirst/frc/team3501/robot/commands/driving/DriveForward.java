package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.utils.PIDController;
import org.usfirst.frc.team3501.robot.utils.TimerUtil;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command makes the robot drive a specified distance using encoders on the robot and using a
 * feedback loop
 *
 * parameters: distance the robot will move in inches motorVal: the motor input to set the motors to
 */
public class DriveForward extends Command {
  private DriveTrain driveTrain = Robot.getDriveTrain();
  private double maxTimeOut;
  private double target;
  private Preferences prefs;
  private PIDController driveController, directionController;
  private double zeroAngle;
  private int counter=0;
  private double prevPos;
  private boolean stopped=false;
  /**
   * @param distance: a positive value will cause the robot to move forwards, and a negative value
   *        will cause the robot to move backwards
   * @param maxTimeOut: the max time this command will be allowed to run on for
   */
  public DriveForward(double distance, double maxTimeOut) {
    requires(driveTrain);
    this.maxTimeOut = maxTimeOut;
    this.target = distance;
    if (target <= 20) {
      this.driveController = new PIDController(DriveTrain.driveStraightPShort,
          DriveTrain.driveStraightIShort, DriveTrain.driveStraightDShort);
    } else
      this.driveController = new PIDController(DriveTrain.driveStraightPLong,
          DriveTrain.driveStraightILong, DriveTrain.driveStraightDLong);
    this.driveController.setDoneRange(7.0);
    this.driveController.setMaxOutput(0.5);
    this.driveController.setMinDoneCycles(10);
    this.zeroAngle = this.driveTrain.getAngle();

    this.directionController =
        new PIDController(driveTrain.driveStraightGyroP, 0, 0);
    this.directionController.setSetPoint(zeroAngle);
  }

  @Override
  protected void initialize() {
    this.driveTrain.resetEncoders();
    this.driveTrain.resetGyro();
    this.driveController.setSetPoint(this.target);
    System.out.println(this.getName() + " initialized");
    prevPos= driveTrain.getFrontBackEncoderDistance();
  }

  @Override
  protected void execute() {
    double currentPosition = driveTrain.getFrontBackEncoderDistance();
    if(counter%25==0) {
      if(currentPosition>=prevPos-1
          &&currentPosition<=prevPos+1) {
          stopped = true;
          System.out.println("STOPPED DUE TO NO MOVEMENT");
          return;
}
      prevPos = currentPosition;
      System.out.println("Counter: "+counter+" CurrentPosition: "+ currentPosition+" previousPosition");
    }
    double ySpeed = driveController.calcPID(currentPosition);
    double rVal = directionController.calcPID(driveTrain.getAngle());
    this.driveTrain.mecanumDrive(0, ySpeed, rVal);
    counter++;
  }

  @Override
  protected boolean isFinished() {
    return timeSinceInitialized() >= maxTimeOut
        || this.driveController.isDone()||stopped;
  }

  @Override
  protected void end() {
    TimerUtil.printTime("DriveForward: ");
  }

  @Override
  protected void interrupted() {
    end();
  }
}
