package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.OI;
import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command runs the drive train motors (which runs the winch) continuously
 * at a specified speed until the button triggering it is released
 *
 * pre-condition: This command must be run by a button in OI with
 * button.whileHeld(...). The robot must be attached to the rope.
 *
 * post-condition: Drive train motors set to a specified speed.
 *
 * @author shivanighanta
 *
 */
public class RunWinchContinuous extends Command {
  Climber climber = Robot.getClimber();
  private double climbingSpeed;

  /**
   * See JavaDoc comment in class for details
   *
   * @param motorVal
   *          value range is from -1 to 1
   */
  public RunWinchContinuous() {
    requires(climber);
    climbingSpeed = climber.CLIMBER_SPEED;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double thrust = OI.leftJoystick.getY();
    climber.setMotorValues(-thrust);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    climber.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
