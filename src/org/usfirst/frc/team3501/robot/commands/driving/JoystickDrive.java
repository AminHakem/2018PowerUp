package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.OI;
import org.usfirst.frc.team3501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
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
        double left = OI.leftJoystick.getY();
        double right = OI.rightJoystick.getY();

        Robot.getDriveTrain().joystickDrive(left, right);
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
