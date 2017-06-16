package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command runs the winch at a specified speed and time in seconds when the
 * button triggering it is pressed. This command also makes the drive train
 * motors run because the winch is controlled by the drive train.
 *
 * pre-condition: This command is run by a button in OI. The robot must be
 * attached to the rope.
 *
 * post-condition: Winch motor set to a specified speed for a specified time.
 *
 * @param motorVal
 *          value range is from -1 to 1
 * @param time
 *          in seconds
 * @author shivanighanta
 *
 */

public class RunWinch extends Command {
  Climber climber = Robot.getClimber();

  private double time;
  private double motorVal;

  /**
   * See JavaDoc comment in class for details
   *
   * @param time
   *          time in seconds to run the winch
   * @param motorVal
   *          value range is from -1 to 1
   */
  public RunWinch() {
    requires(climber);
    this.time = time;
    this.motorVal = motorVal;
  }

  @Override
  protected void initialize() {
    climber.setCANTalonsBrakeMode(climber.COAST_MODE);
  }

  @Override
  protected void execute() {
    climber.setMotorValues(climber.CLIMBER_SPEED);
  }

  @Override
  protected boolean isFinished() {
    // return timeSinceInitialized() >= time;
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
