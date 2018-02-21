package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.command.Command;

/***
 * Toggles the FieldOriented boolean in JoystickDrive
 *
 * @author rohanrodrigues
 *
 */
public class ResetGyro extends Command {

  private DriveTrain driveTrain = Robot.getDriveTrain();

  public ResetGyro() {}

  @Override
  protected void initialize() {
    driveTrain.resetGyro();
  }

  @Override
  protected void execute() {}

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {}

  @Override
  protected void interrupted() {}
}
