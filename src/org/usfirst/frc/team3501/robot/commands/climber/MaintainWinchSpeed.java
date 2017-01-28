package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
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
public class MaintainWinchSpeed extends Command {
  Timer timer;
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
  public MaintainWinchSpeed(double time, double motorVal) {
    timer = new Timer();
    requires(Robot.getDriveTrain());
    this.time = time;
    this.motorVal = motorVal;
  }

  @Override
  protected void initialize() {
    timer.start();
  }

  @Override
  protected void execute() {
    Robot.getDriveTrain().setMotorValues(Constants.Climber.MAINTAIN_MOTOR_VAL,
        Constants.Climber.MAINTAIN_MOTOR_VAL);

  }

  @Override
  protected boolean isFinished() {
    return timer.get() >= time;
  }

  @Override
  protected void end() {
    Robot.getDriveTrain().stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
