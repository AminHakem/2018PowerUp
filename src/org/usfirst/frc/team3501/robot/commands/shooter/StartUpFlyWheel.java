package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Shooter;
import org.usfirst.frc.team3501.robot.utils.PIDController;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StartUpFlyWheel extends Command {
  private Shooter shooter = Robot.getShooter();
  private PIDController wheelController;
  private double wheelP;
  private double wheelI;
  private double wheelD;
  private double motorVal;
  private double target;
  
  public StartUpFlyWheel() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(shooter);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
  }
}
