package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.OI;
import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command will run throughout teleop and listens for joystick inputs to
 * drive the driveTrain. This never finishes until teleop ends. - works in
 * conjunction with OI.java
 */
public class JoystickDrive extends Command {

  public JoystickDrive() {
    requires(Robot.getDriveTrain());
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    final double thrust = OI.xboxController.getY();
    final double twist = OI.xboxController.getAxis(AxisType.kZ);

    Robot.getDriveTrain().joystickDrive(-thrust, -twist);

    /*
     * double left = OI.leftJoystick.getY(); double right =
     * OI.rightJoystick.getY(); Robot.getDriveTrain().tankDrive(-left, -right);
     */
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
  }
}
