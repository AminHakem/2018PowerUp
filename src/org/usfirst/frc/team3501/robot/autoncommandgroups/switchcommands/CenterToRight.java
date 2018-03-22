package org.usfirst.frc.team3501.robot.autoncommandgroups.switchcommands;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.DriveSideways;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;
import org.usfirst.frc.team3501.robot.commands.elevator.ChangeElevatorTarget;
import org.usfirst.frc.team3501.robot.commands.elevator.MoveToTarget;
import org.usfirst.frc.team3501.robot.commands.intake.RunOuttake;
import org.usfirst.frc.team3501.robot.commands.intake.Shoot;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterToRight extends CommandGroup {

  public static final double ROBOT_LENGTH = 38.0;
  public static final double SCALE = Constants.Auton.SCALE_FACTOR_TESTING;

  public static final double TO_CUBES = 93;
  public static final double HORIZ_SWITCH_DIST = 50.0;
  public static final double VERT_SWITCH_DIST = 60;

  public CenterToRight() {
    addSequential(new ChangeElevatorTarget(Elevator.SWITCH_POS));
    addSequential(new DriveForward((TO_CUBES - ROBOT_LENGTH) * SCALE, 10));
    addSequential(new DriveSideways(HORIZ_SWITCH_DIST * SCALE, 6.5));
    addSequential(new DriveForward(VERT_SWITCH_DIST * SCALE, 5));
    addSequential(new Shoot());
  }
}
