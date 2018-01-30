package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command will run throughout teleop and listens for joystick inputs to drive the driveTrain.
 * This never finishes until teleop ends. - works in conjunction with OI.java
 */
public class JoystickDrive extends Command {

  double previousThrust = 0;
  double previousTwist = 0;
  double previousRotation= 0;
  Joystick joystick;
  public JoystickDrive() {
    requires(Robot.getDriveTrain());
  }

  @Override
  protected void initialize() {
	  System.out.println("joystick Drive");
  }

  @Override
  protected void execute() {

    /**
     * Raw Axis 1 is the left joystick on the Xbox controller with the movement being from top to
     * bottom. Raw Axis 0 is the left joystick on the Xbox controller with the movement being from
     * left to right. Raw Axis 4 is the right joystick on the Xbox controller with the movement
     * being from left to right.
     *
     */
    if (!Robot.getClimber().inJoystickClimb) {
     
    	  double ySpeed = Robot.getOI().ps4_controller.getRawAxis(1);
      double xSpeed = Robot.getOI().ps4_controller.getRawAxis(0);
      double rotation = Robot.getOI().ps4_controller.getRawAxis(2);

      ySpeed = (6 * previousThrust + ySpeed) / 7;
      xSpeed = (6 * previousTwist + xSpeed) / 7;
      rotation = (5 * previousRotation + rotation) / 7;
      previousThrust = ySpeed;
      previousTwist = xSpeed;
      previousRotation = rotation;
      Robot.getDriveTrain().mecanumDrive(-xSpeed, ySpeed, rotation);
    }
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
  protected void interrupted() {}

  /***
   * Toggles the boolean fieldOriented
   */

}
