package org.usfirst.frc.team3501.robot.commands.intake;

import org.usfirst.frc.team3501.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZeroIntake extends Command {
    Intake intake;
    public ZeroIntake() {
       intake = Intake.getIntake();
       requires(intake);
       }

    // Called just before this Command runs the first time
    protected void initialize() {
      intake.setAngleMotorValue(-0.45);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
      System.out.println("RESET************");
      intake.resetEncoder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
      end();
    }
}
