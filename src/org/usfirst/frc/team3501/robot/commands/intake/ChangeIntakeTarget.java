package org.usfirst.frc.team3501.robot.commands.intake;

import org.usfirst.frc.team3501.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeIntakeTarget extends Command {
  Intake intake;
  double intakeTarget;
    public ChangeIntakeTarget(double intakeTarget) {
      intake = Intake.getIntake();
      this.intakeTarget = intakeTarget;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
      intake.setIntakeTarget(this.intakeTarget);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
