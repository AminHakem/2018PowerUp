package org.usfirst.frc.team3501.robot.commands;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.command.Command;

/***
 * Toggles the FieldOriented boolean in JoystickDrive
 * 
 * @author rohanrodrigues
 *
 */
public class ToggleFieldOriented extends Command {

  private DriveTrain driveTrain = Robot.getDriveTrain();

  public ToggleFieldOriented() {
    requires(driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Toggle Field Oriented");
    Robot.getDriveTrain().getJoystickDrive().toggleFieldOriented();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {}

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

}
