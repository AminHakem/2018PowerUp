
package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Shooter;
import org.usfirst.frc.team3501.robot.utils.PIDController;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command runs the fly wheel at a specific speed using a PID Controller
 * for accuracy for a given time. The fly wheel is intended to shoot balls fed
 * by the intake wheel.
 *
 * @author Shaina & Chris
 */
public class RunFlyWheel extends Command {
  private Shooter shooter = Robot.getShooter();
  private double maxTimeOut;

  private PIDController wheelController;
  private double wheelP;
  private double wheelI;
  private double wheelD;
  private double target;
  private double shooterSpeed = 0;

  public RunFlyWheel(double maxTimeOut) {

    this.wheelP = this.shooter.wheelP;
    this.wheelI = this.shooter.wheelI;
    this.wheelD = this.shooter.wheelD;
    this.wheelController = new PIDController(this.wheelP, this.wheelI,
        this.wheelD);
    this.wheelController.setDoneRange(0.5);
    this.wheelController.setMaxOutput(1.0);
    this.wheelController.setMinDoneCycles(3);
    this.target = this.shooter.getCurrentShootingSpeed();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.wheelController.setSetPoint(this.target);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double calculatedShooterIncrement = this.wheelController
        .calcPID(this.shooter.getShooterRPM());
    shooterSpeed += calculatedShooterIncrement;
    this.shooter.setFlyWheelMotorVal(shooterSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return timeSinceInitialized() >= maxTimeOut;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    this.shooter.stopFlyWheel();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
