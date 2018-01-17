package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.OI;
import org.usfirst.frc.team3501.robot.Robot;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command will run throughout teleop and listens for joystick inputs to drive the driveTrain.
 * This never finishes until teleop ends. - works in conjunction with OI.java
 */
public class JoystickDrive extends Command {

  double previousThrust = 0;
  double previousTwist = 0;

  public JoystickDrive() {
    requires(Robot.getDriveTrain());
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    double thrust = OI.ps4_controller.getY();
    double twist = OI.ps4_controller.getAxis(AxisType.kZ);
    double rotation = OI.ps4_controller.getZ();
    double gyroAngle = OI.ps4_controller.getDirectionDegrees();

    thrust = (6 * previousThrust + thrust) / 7;
    twist = (6 * previousTwist + twist) / 7;

    previousThrust = thrust;
    previousTwist = twist;

    Robot.getDriveTrain().mecanumDrive(-thrust, -twist, rotation, gyroAngle);
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
