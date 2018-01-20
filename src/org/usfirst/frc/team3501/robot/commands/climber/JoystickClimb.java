package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Climber;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickClimb extends Command {
  Climber climber = Robot.getClimber();

  public JoystickClimb() {
    requires(climber);
  }

  @Override
  protected void initialize() {
    climber.inJoystickClimb = true;
  }

  @Override
  protected void execute() {
    double leftSpeed = Robot.getOI().ps4_controller.getRawAxis(1);
    double rightSpeed = Robot.getOI().ps4_controller.getRawAxis(0);

    climber.setLeftMotorVal(leftSpeed);
    climber.setRightMotorVal(rightSpeed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    climber.inJoystickClimb = false;
  }

  @Override
  protected void interrupted() {}
}
