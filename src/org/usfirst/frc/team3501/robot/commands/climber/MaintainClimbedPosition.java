package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command runs the winch at a specified speed when the robot has completed
 * the climb and when the button triggering it is pressed. This command also
 * makes the drive train motors run because the winch is controlled by the drive
 * train.
 *
 * pre-condition: This command is run by a button in OI. The robot must have
 * completed climbing.
 *
 * post-condition: Winch motor set to a specified speed.
 *
 * @param motorVal
 *          value range is from -1 to 1
 *
 * @author shivanighanta
 *
 */
public class MaintainClimbedPosition extends Command {

  /**
   * See JavaDoc comment in class for details
   *
   * @param time
   *          time in seconds to run the winch
   */
  public MaintainClimbedPosition() {
    requires(Robot.getDriveTrain());
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.getDriveTrain().setMotorValues(DriveTrain.MAINTAIN_CLIMBED_POSITION,
        DriveTrain.MAINTAIN_CLIMBED_POSITION);

  }

  @Override
  protected boolean isFinished() {
    return false;
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
