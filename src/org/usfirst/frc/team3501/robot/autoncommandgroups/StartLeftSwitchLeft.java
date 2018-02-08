package org.usfirst.frc.team3501.robot.autoncommandgroups;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartLeftSwitchLeft extends CommandGroup {

  public static final double LEFT_TO_CENTER_OF_SWITCH_VERTICAL_DISTANCE = 160.0;
  public static final double HORIZONTAL_DISTANCE_LEFT_SIDE_TO_CENTER_OF_SWITCH = 52.0;

  public StartLeftSwitchLeft() {
    addSequential(new DriveForward(LEFT_TO_CENTER_OF_SWITCH_VERTICAL_DISTANCE
        * Constants.Auton.SCALE_FACTOR_TESTING, 15.5));
    MathLib.debugPrint("Finished command 1");
    // turn 90 degrees left
    addSequential(new TurnForAngle(90.0, Constants.Direction.RIGHT, 5.0));
    MathLib.debugPrint("Finished command 2");
    // move forward 68 inches
    addSequential(
        new DriveForward(HORIZONTAL_DISTANCE_LEFT_SIDE_TO_CENTER_OF_SWITCH
            * Constants.Auton.SCALE_FACTOR_TESTING, 6.5));
    MathLib.debugPrint("Finished command 3");
    // No elevator yet, waiting for actual elevator command, numbers are
    // hypothetical and are subject to change
    // addSequential(new ClimbDistance(5.0, 7.0));

  }
}
