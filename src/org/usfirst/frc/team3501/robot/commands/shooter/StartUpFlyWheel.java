package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Shooter;
import org.usfirst.frc.team3501.robot.utils.PIDController;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StartUpFlyWheel extends Command {
  private Shooter shooter = Robot.getShooter();
  private double maxTimeOut;

  private PIDController wheelController;
  private double wheelP;
  private double wheelI;
  private double wheelD;
  private double motorVal;
  private double target;

  public StartUpFlyWheel(double speed, double maxTimeOut) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(shooter);

    this.wheelP = this.shooter.wheelP;
    this.wheelI = this.shooter.wheelI;
    this.wheelD = this.shooter.wheelD;
    this.wheelController = new PIDController(this.wheelP, this.wheelI,
        this.wheelD);
    this.wheelController.setDoneRange(0.5);
    this.wheelController.setMaxOutput(1.0);
    this.wheelController.setMinDoneCycles(3);
    this.target = speed;
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    this.wheelController.setSetPoint(this.target);
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    double shooterSpeed = this.wheelController
        .calcPID(this.shooter.getShooterSpeed());

    this.shooter.setFlyWheelMotorVal(shooterSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    boolean isDone = this.wheelController.isDone();
    if (timeSinceInitialized() >= maxTimeOut || isDone) {
      System.out.println("time: " + timeSinceInitialized());
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  protected void end() {
    this.shooter.stopFlyWheel();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
    end();
  }
}
