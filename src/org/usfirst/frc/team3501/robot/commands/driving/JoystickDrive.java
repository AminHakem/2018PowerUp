package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.OI;
import org.usfirst.frc.team3501.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command will run throughout teleop and listens for joystick inputs to drive the driveTrain.
 * This never finishes until teleop ends. - works in conjunction with OI.java
 */
public class JoystickDrive extends Command {

  double previousThrust = 0;
  double previousTwist = 0;
  boolean fieldOriented = true;

  public JoystickDrive() {
    requires(Robot.getDriveTrain());
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {

    /**
     * Raw Axis 1 is the left joystick on the Xbox controller with the movement being from top to
     * bottom. Raw Axis 0 is the left joystick on the Xbox controller with the movement being from
     * left to right. Raw Axis 4 is the right joystick on the Xbox controller with the movement
     * being from left to right.
     * 
     */
    double ySpeed = Robot.getOI().ps4_controller.getRawAxis(1);
    // System.out.println("Getting ySpeed value: " + ySpeed);

    double xSpeed = Robot.getOI().ps4_controller.getRawAxis(0);
    // System.out.println("Getting xSpeed value: " + xSpeed);

    double rotation = Robot.getOI().ps4_controller.getRawAxis(4);
    // System.out.println("Getting rotation value: " + rotation);

    fieldOriented = OI.ps4_controller.getRawButtonPressed(Constants.OI.CONTROLLER_BUTTON_PS);

    ySpeed = (6 * previousThrust + ySpeed) / 7;
    xSpeed = (6 * previousTwist + xSpeed) / 7;

    previousThrust = ySpeed;
    previousTwist = xSpeed;

    Robot.getDriveTrain().mecanumDrive(ySpeed, -xSpeed, rotation, fieldOriented);
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
}
