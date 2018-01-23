package org.usfirst.frc.team3501.robot.commands;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.OI;
import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.command.Command;

public class ToggleFieldOriented extends Command {

  private DriveTrain driveTrain = Robot.getDriveTrain();

  public ToggleFieldOriented() {
    requires(driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    boolean fieldOriented =
        OI.ps4_controller.getRawButtonPressed(Constants.OI.PS4_CONTROLLER_PORT);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.getIntake().stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

}
