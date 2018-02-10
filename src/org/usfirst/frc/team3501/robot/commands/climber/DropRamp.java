package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Climber;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author: niyatisriram Lowers ramp when Joystick button is pressed
 */
public class DropRamp extends Command {

  private Climber climber = Robot.getClimber();

  public DropRamp() {
    requires(climber);
  }

  @Override
  protected void initialize() {}

  // @Override
  // protected void execute() {
  // Climber.runServo();
  // }

  @Override
  protected boolean isFinished() {
    return false;
  }

  // @Override
  // protected void end() {
  // Climber.stopRunningServo();
  // }

  @Override
  protected void interrupted() {}
}
