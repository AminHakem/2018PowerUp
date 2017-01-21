package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command will run the winch motor continuously until the button
 * triggering it is released. This command also makes the drive train motors run
 * because the winch is controlled by the drive train.
 *
 * pre-condition: This command must be run by a button in OI. The robot must be
 * attached to the rope.
 *
 * post-condition: Winch motor set to a specified speed.
 *
 * @param motorVal
 *          value range is from -1 to 1
 *
 * @author shivanighanta
 *
 */
public class RunWinchContinuous extends Command {
  private double motorVal;

  /**
   * See JavaDoc comment in class for details
   *
   * @param motorVal
   *          value range is from -1 to 1
   */
  public RunWinchContinuous(double motorVal) {
    requires(Robot.getDriveTrain());
    this.motorVal = motorVal;
  }

  @Override
  protected void initialize() {
    Robot.getDriveTrain().setMotorValues(motorVal, motorVal);

  }

  @Override
  protected void execute() {

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {

  }

  @Override
  protected void interrupted() {
    end();
  }
}
