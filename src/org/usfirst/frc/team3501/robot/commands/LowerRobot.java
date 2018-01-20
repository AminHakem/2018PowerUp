package org.usfirst.frc.team3501.robot.commands;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author: niyatisriram
 *          Lowers robot using climbing winch when button is pressed
 */
public class LowerRobot extends Command {

  private Climber climber = Robot.getClimber();

  public LowerRobot() {
    requires(climber);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Climber.runBothAtSpeed(-Climber.WINCH_SPEED);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Climber.stop();
  }

  @Override
  protected void interrupted() {
  }
}
