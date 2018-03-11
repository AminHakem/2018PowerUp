package org.usfirst.frc.team3501.robot.commands.intake;

import org.usfirst.frc.team3501.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleIntakePiston extends Command {
    Intake intake;
    public ToggleIntakePiston() {
      intake = Intake.getIntake();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
      if(intake.isPistonActivated())intake.setMotorValues(-intake.intakeSpeed);
      intake.setPistonActivated(!intake.isPistonActivated());
      intake.getIntakeSolenoid().set(intake.isPistonActivated());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
      
      }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timeSinceInitialized()>1;
    }

    // Called once after isFinished returns true
    protected void end() {
      intake.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
