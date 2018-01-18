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
    double thrust = Robot.getOI().ps4_controller.getThrottle();
    System.out.println("Getting thurst value: " + thrust);

    double twist = Robot.getOI().ps4_controller.getX();
    System.out.println("Getting twist value: " + twist);
    double rotation = Robot.getOI().ps4_controller.getZ();
    System.out.println("Getting rotation value: " + rotation);
    fieldOriented = OI.ps4_controller.getRawButtonPressed(Constants.OI.CONTROLLER_BUTTON_PS);

    thrust = (6 * previousThrust + thrust) / 7;
    twist = (6 * previousTwist + twist) / 7;

    previousThrust = thrust;
    previousTwist = twist;

    Robot.getDriveTrain().mecanumDrive(-thrust, -twist, rotation, fieldOriented);
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
