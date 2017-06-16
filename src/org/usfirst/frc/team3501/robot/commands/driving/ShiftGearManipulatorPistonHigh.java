package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftGearManipulatorPistonHigh extends Command {
  private DriveTrain driveTrain = Robot.getDriveTrain();

  public ShiftGearManipulatorPistonHigh() {
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    driveTrain.extendGearManipulatorPiston();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
