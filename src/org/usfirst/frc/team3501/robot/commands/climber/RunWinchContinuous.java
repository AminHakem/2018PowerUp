package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * <<<<<<< HEAD <<<<<<< 1b45bc927675060d057730fc7c7083fb9d813f66 This command
 * runs the winch motor continuously at a specified speed until the button
 * triggering it is released. This command also makes the drive train motors run
 * because the winch is controlled by the drive train. ======= This command will
 * run the winch motor continuously until the button triggering it is released.
 * This command also runs the drive train. >>>>>>> Delete StopWinch and Climber
 * subsystem, edit javadoc comments ======= <<<<<<<
 * 1b45bc927675060d057730fc7c7083fb9d813f66 This command runs the winch motor
 * continuously at a specified speed until the button triggering it is released.
 * This command also makes the drive train motors run because the winch is
 * controlled by the drive train. ======= This command will run the winch motor
 * continuously until the button triggering it is released. This command also
 * runs the drive train. >>>>>>> Delete StopWinch and Climber subsystem, edit
 * javadoc comments >>>>>>> Delete StopWinch and Climber subsystem, edit javadoc
 * comments
 *
 * pre-condition: This command must be run by a button in OI. The robot must be
 * attached to the rope.
 *
 * post-condition: Winch motor set to a specified speed.
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
