package org.usfirst.frc.team3501.robot.commands;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.AirBalloon;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FillLeftAirBalloon extends Command {

  private AirBalloon airBalloon = Robot.getAirBalloon();

  public FillLeftAirBalloon() {
    requires(airBalloon);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    AirBalloon.extendPiston();
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
    AirBalloon.retractPiston();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
