package org.usfirst.frc.team3501.robot.commands;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Ramp;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author: niyatisriram
 *          Lowers left ramp when Joystick button is pressed
 */
public class DropLeftRamp extends Command {

  private Ramp ramp = Robot.getRamp();

  public DropLeftRamp() {
    requires(ramp);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Ramp.extendPiston();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Ramp.retractPiston();
  }

  @Override
  protected void interrupted() {
  }
}
