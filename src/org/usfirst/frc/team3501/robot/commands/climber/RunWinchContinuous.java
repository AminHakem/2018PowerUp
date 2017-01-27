package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command runs the drive train motors (which runs the winch) continuously
 * at a specified speed until the button triggering it is released
 *
 * pre-condition: This command must be run by a button in OI. The robot must be
 * attached to the rope.
 *
 * post-condition: Drive train motors set to a specified speed.
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
