package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command toggles the gear(low or high).
 *
 * post-condition: if the drivetrain is running at high gear, it will be made to
 * run at low gear, and vice versa
 */
public class ToggleGear extends Command {
  DriveTrain driveTrain = Robot.getDriveTrain();

  public ToggleGear() {
    requires(driveTrain);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    Value leftGearPistonValue = driveTrain.getLeftGearPistonValue();
    Value rightGearPistonValue = driveTrain.getRightGearPistonValue();
    if (leftGearPistonValue == Constants.DriveTrain.LOW_GEAR) {
      driveTrain.setHighGear();
    } else {
      driveTrain.setLowGear();
    }
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
