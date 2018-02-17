package org.usfirst.frc.team3501.robot.autoncommandgroups;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;
import org.usfirst.frc.team3501.robot.commands.elevator.MoveToTarget;
import org.usfirst.frc.team3501.robot.commands.intake.RunOuttake;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterToLeft extends CommandGroup {

  public static final double MIDDLE_TO_CUBES_BEFORE_SWITCH = 98.0;
  public static final double MIDDLE_TO_CENTER_OF_SWITCH_HORIZONTAL_DISTANCE = 55.0;
  public static final double VERTICAL_DISTANCE_TO_CENTER_OF_SWITCH = 42.0;

  public CenterToLeft() {
    // did 48 in so it leaves margin for how much distance the cube is dropping
    addSequential(new MoveToTarget(48, 5));
    // move forward 90 in
    addSequential(
        new DriveForward(MIDDLE_TO_CUBES_BEFORE_SWITCH * Constants.Auton.SCALE_FACTOR_TESTING, 10));
    // turn 90 degrees left
    addSequential(new TurnForAngle(-90.0, 3.0));
    // move forward 60 inches
    addSequential(new DriveForward(
        MIDDLE_TO_CENTER_OF_SWITCH_HORIZONTAL_DISTANCE * Constants.Auton.SCALE_FACTOR_TESTING,
        6.5));
    // turn 90 degrees right
    addSequential(new TurnForAngle(90.0, 3.0));
    // move forward 42 inches
    addSequential(new DriveForward(
        VERTICAL_DISTANCE_TO_CENTER_OF_SWITCH * Constants.Auton.SCALE_FACTOR_TESTING, 5));
    addSequential(new RunOuttake());
    addSequential(new MoveToTarget(48, 5));

  }
}
