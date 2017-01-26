package org.usfirst.frc.team3501.robot.commands.intake;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Reverses the intake roller for a specified amount of time in seconds
 *
 * parameters: time to run intake
 */
public class ReverseIntake extends Command {
  private double timeToMove;
  public Timer timer;

  public ReverseIntake(double timeToMove) {
    requires(Robot.getIntake());
    timer = new Timer();
    this.timeToMove = timeToMove;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.getIntake().runReverseIntake();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return timer.get() >= timeToMove;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.getIntake().stopIntake();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
