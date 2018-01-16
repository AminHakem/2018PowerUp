package org.usfirst.frc.team3501.robot.commands;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author: niyatisriram
 *          Lowers right ramp when Joystick button is pressed
 */
public class DropRightRamp extends Command {

  private Climber climber = Robot.getClimber();

  public DropRightRamp() {
    requires(climber);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Climber.extendPiston();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Climber.retractPiston();
  }

  @Override
  protected void interrupted() {
  }
}
