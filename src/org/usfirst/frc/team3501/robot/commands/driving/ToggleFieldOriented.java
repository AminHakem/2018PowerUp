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
public class ToggleFieldOriented extends Command {

  private DriveTrain driveTrain = Robot.getDriveTrain();

  public ToggleFieldOriented() {
    requires(driveTrain);
  }

  @Override
  protected void initialize() {
    Robot.getDriveTrain().toggleFieldOriented();
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
  protected void interrupted() {
    end();
  }
}
