package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleGearManipulatorPiston extends Command {
  private DriveTrain driveTrain = Robot.getDriveTrain();

  public ToggleGearManipulatorPiston() {
  }

  @Override
  protected void initialize() {
    Value gearPistonValue = driveTrain.getGearManipulatorPistonValue();
    if (gearPistonValue == Constants.DriveTrain.REVERSE_PISTON_VALUE) {
      driveTrain.extendGearManipulatorPiston();
    } else {
      driveTrain.retractGearManipulatorPiston();
    }
  }

  @Override
  protected void execute() {
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
