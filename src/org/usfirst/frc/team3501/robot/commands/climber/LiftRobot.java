package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author: niyatisriram
 *          Lifts robot using climbing winch when button is pressed
 */
public class LiftRobot extends Command {

  private Climber climber = Robot.getClimber();

  public LiftRobot() {
    requires(climber);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Climber.runAtDefaultSpeed("forward");
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
