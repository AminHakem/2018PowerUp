package org.usfirst.frc.team3501.robot.commands.climber;

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
 * @author shivanighanta
 *
 */

public class RunWinch extends Command {
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
  public RunWinch(double time, double motorVal) {
<<<<<<< a5147d5928f01620d8e10f2e9cdea079526d2db3
    requires(Robot.getDriveTrain());
=======
    requires(Robot.getClimber());
>>>>>>> Implement RunWinch
    this.time = time;
    this.motorVal = motorVal;
  }

  @Override
  protected void initialize() {
    timer.start();
<<<<<<< a5147d5928f01620d8e10f2e9cdea079526d2db3
=======
    Robot.getClimber().setMotorValues(motorVal, motorVal);
>>>>>>> Implement RunWinch
  }

  @Override
  protected void execute() {
    Robot.getDriveTrain().setMotorValues(motorVal, motorVal);

  }

  @Override
  protected boolean isFinished() {
    return timer.get() >= time;
  }

  @Override
  protected void end() {
<<<<<<< a5147d5928f01620d8e10f2e9cdea079526d2db3
    Robot.getDriveTrain().stop();

=======
    Robot.getClimber().stop();
>>>>>>> Implement RunWinch
  }

  @Override
  protected void interrupted() {
    end();
  }
}
