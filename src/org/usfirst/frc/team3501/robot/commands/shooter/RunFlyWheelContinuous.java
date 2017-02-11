package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Shooter;
import org.usfirst.frc.team3501.robot.utils.PIDController;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command runs the fly wheel continuously at a set speed using a PID
 * Controller when OI button managing fly wheel is pressed. The command will run
 * the fly wheel motor until the button triggering it is released.
 *
 * Should only be run from the operator interface.
 *
 * pre-condition: This command must be run by a button in OI, with
 * button.whileHeld(...).
 *
 * @author Shaina & Chris
 */
public class RunFlyWheelContinuous extends Command {
  private Shooter shooter = Robot.getShooter();

  private PIDController wheelController;
  private double wheelP;
  private double wheelI;
  private double wheelD;
  private double target;

  public RunFlyWheelContinuous() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    this.wheelP = this.shooter.wheelP;
    this.wheelI = this.shooter.wheelI;
    this.wheelD = this.shooter.wheelD;
    this.wheelController = new PIDController(this.wheelP, this.wheelI,
        this.wheelD);
    this.wheelController.setDoneRange(0.5);
    this.wheelController.setMaxOutput(1.0);
    this.wheelController.setMinDoneCycles(3);
    this.target = this.shooter.CURRENT_SHOOTING_SPEED;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.wheelController.setSetPoint(this.target);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double shooterSpeed = this.wheelController
        .calcPID(this.shooter.getShooterRPM());

    this.shooter.setFlyWheelMotorVal(shooterSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
