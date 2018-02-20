package org.usfirst.frc.team3501.robot.autoncommandgroups;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.DriveSideways;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;
import org.usfirst.frc.team3501.robot.commands.elevator.ChangeElevatorTarget;
import org.usfirst.frc.team3501.robot.commands.elevator.MoveToTarget;
import org.usfirst.frc.team3501.robot.commands.intake.RunOuttake;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterToLeft extends CommandGroup {

  public static final double ROBOT_LENGTH = 38.0;
  public static final double SCALE = Constants.Auton.SCALE_FACTOR_TESTING;

  public static final double SPACE_FOR_TURNING = 5.0;
  public static final double TO_CUBES = 98.0 - SPACE_FOR_TURNING;
  public static final double HORIZ_SWITCH_DIST = 55.0;
  public static final double VERT_SWITCH_DIST = 55.0 + SPACE_FOR_TURNING;

  public CenterToLeft() {
    addSequential(new ChangeElevatorTarget(Elevator.SWITCH_POS));
    addSequential(new DriveForward((TO_CUBES - ROBOT_LENGTH) * SCALE, 10));
    addSequential(new DriveSideways(-HORIZ_SWITCH_DIST * SCALE, 6.5));
    addSequential(new DriveForward(VERT_SWITCH_DIST * SCALE, 5));
    addSequential(new RunOuttake());
  }
}
