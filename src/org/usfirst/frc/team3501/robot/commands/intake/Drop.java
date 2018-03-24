package org.usfirst.frc.team3501.robot.commands.intake;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drop extends Command {

  private Intake intake = Robot.getIntake();

  public Drop() {
    requires(intake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    intake.setMotorValues(intake.dropSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return timeSinceInitialized()>0.5;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    if(intake.isPistonActivated())intake.setMotorValues(-intake.intakeSpeed);
    intake.setPistonActivated(!intake.isPistonActivated());
    intake.getIntakeSolenoid().set(intake.isPistonActivated());
    intake.getIntakeSolenoidTwo().set(intake.isPistonActivated());
    intake.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
