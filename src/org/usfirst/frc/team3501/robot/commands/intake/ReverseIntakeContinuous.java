package org.usfirst.frc.team3501.robot.commands.intake;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Reverses the intake until the button triggering this command is released
 *
 * pre-condition: This command must be run by a button in OI with
 * button.whileHeld(...).
 */
public class ReverseIntakeContinuous extends Command {
  private Intake intake = Robot.getIntake();

  private double previousMotorValue = 0;
  private double targetMotorValue = intake.REVERSE_SPEED;

  public ReverseIntakeContinuous() {
    requires(intake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double motorValue = (6 * previousMotorValue + targetMotorValue) / 7;
    previousMotorValue = motorValue;
    intake.setSpeed(motorValue);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    intake.stopIntake();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
