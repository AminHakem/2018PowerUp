package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command shifts the gear to high
 *
 * Author: Rohan Rodrigues
 */
public class ShiftDriveHighGear extends Command {
  DriveTrain driveTrain = Robot.getDriveTrain();

  public ShiftDriveHighGear() {
    requires(driveTrain);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    driveTrain.setHighGear();
    System.out.println("Current Value is: " + driveTrain.getLeftMotorVal());
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
