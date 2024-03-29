package org.usfirst.frc.team3501.robot.autoncommandgroups;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.DriveSideways;
import org.usfirst.frc.team3501.robot.commands.driving.ResetGyro;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;
import org.usfirst.frc.team3501.robot.commands.elevator.ChangeElevatorTarget;
import org.usfirst.frc.team3501.robot.commands.elevator.MoveToTarget;
import org.usfirst.frc.team3501.robot.commands.intake.RunOuttake;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartLeftScaleLeft extends CommandGroup {

  public static final double ROBOT_LENGTH = 38.0;
  public static final double ROBOT_WIDTH = 33.0;
  public static final double SCALE = Constants.Auton.SCALE_FACTOR_TESTING;

  public static final double VERT_SWITCH_DIST = 324.0;
  public static final double HORIZ_SWITCH_DIST = 10;

  public StartLeftScaleLeft() {
    addSequential(new DriveSideways(-VERT_SWITCH_DIST, 10));
    addSequential(new ChangeElevatorTarget(Elevator.SCALE_POS));
    addSequential(new DriveForward((HORIZ_SWITCH_DIST) * SCALE, 6.5));
    addSequential(new RunOuttake());
    addSequential(new ResetGyro());
  }
}
