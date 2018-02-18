package org.usfirst.frc.team3501.robot.autoncommandgroups;

import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.DriveSideways;
import org.usfirst.frc.team3501.robot.commands.driving.TimeDriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.TimeDriveSideways;
import org.usfirst.frc.team3501.robot.commands.driving.Turn90Left;
import org.usfirst.frc.team3501.robot.commands.driving.Turn90Right;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestDrive extends CommandGroup {

  public static final double DISTANCE_TO_DRIVE_FORWARD = 5;
  public static final double DISTANCE_TO_DRIVE_SIDEWAYS = 10;
  public static final double TURNING_DISTANCE = 90;
  public static final double MAX_TIME_OUT = 5;

  public TestDrive() {
    addSequential(new DriveForward(DISTANCE_TO_DRIVE_FORWARD, MAX_TIME_OUT),
        MAX_TIME_OUT);
    addSequential(new DriveSideways(DISTANCE_TO_DRIVE_SIDEWAYS, MAX_TIME_OUT),
        MAX_TIME_OUT);

    addSequential(new Turn90Right(), MAX_TIME_OUT);
    addSequential(new Turn90Left(), MAX_TIME_OUT);

    addSequential(new TurnForAngle(50, MAX_TIME_OUT), MAX_TIME_OUT);

    addSequential(new TimeDriveForward(DISTANCE_TO_DRIVE_FORWARD, MAX_TIME_OUT),
        MAX_TIME_OUT);
    addSequential(
        new TimeDriveSideways(DISTANCE_TO_DRIVE_SIDEWAYS, MAX_TIME_OUT),
        MAX_TIME_OUT);
  }

}
