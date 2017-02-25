package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BrakeCANTalons extends Command {
  private DriveTrain driveTrain = Robot.getDriveTrain();

  public BrakeCANTalons() {
    requires(driveTrain);
  }

  @Override
  protected void initialize() {
    driveTrain.setCANTalonsBrakeMode(driveTrain.DRIVE_BRAKE_MODE);
  }

  @Override
  protected void execute() {
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
