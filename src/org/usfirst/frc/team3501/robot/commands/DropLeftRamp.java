package org.usfirst.frc.team3501.robot.commands;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author: niyatisriram
 *          Lowers left ramp when Joystick button is pressed
 */
public class DropLeftRamp extends Command {

  private Climber climber = Robot.getClimber();

  public DropLeftRamp() {
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
