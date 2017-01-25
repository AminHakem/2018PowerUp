package org.usfirst.frc.team3501.robot.commands.intake;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * Starts running the intake for a specific period of time that the user inputs.
 *
 * @author Meeta
 */
public class RunIntake extends Command {
  private double timeToMove;
  public Timer timer;

  /**
   *
   * @param timeToMove
   */
  public RunIntake(double timeToMove) {
    requires(Robot.getIntake());
    timer = new Timer();
    this.timeToMove = timeToMove;
  }

  @Override
  protected boolean isFinished() {
    return timer.get() >= timeToMove;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.getIntake().runIntake();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    timer.stop();
    Robot.getIntake().stopIntake();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
