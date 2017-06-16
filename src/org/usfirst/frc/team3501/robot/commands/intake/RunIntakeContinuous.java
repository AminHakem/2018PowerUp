package org.usfirst.frc.team3501.robot.commands.intake;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/***
 *
 * Runs the intake continuously when button is pressed, and when button is not
 * pressed does not run.
 *
 * Intended to be run inside a .whileHeld() call on a button in OI
 *
 * @author Meeta
 *
 */
public class RunIntakeContinuous extends Command {
  private Intake intake = Robot.getIntake();

  private double previousMotorValue = 0;
  private double targetMotorValue = intake.INTAKE_SPEED;

  public RunIntakeContinuous() {
    requires(intake);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double motorValue = (6 * previousMotorValue + targetMotorValue) / 7;
    previousMotorValue = motorValue;
    intake.setSpeed(motorValue);
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
