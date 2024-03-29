package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExtendHook extends Command {
  Elevator elevator = Robot.getElevator();

    public ExtendHook() {
     requires(Robot.getElevator());
     }

    // Called just before this Command runs the first time
    protected void initialize() {
      System.out.println("RUN*******");
     elevator.setHookPistonOne(!elevator.hookPistonActivated);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
      elevator.setMotorValue(-0.1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
      return timeSinceInitialized()>3;
    }

    // Called once after isFinished returns true
    protected void end() {
      elevator.setTargetElavatorPos(elevator.SWITCH_POS);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
