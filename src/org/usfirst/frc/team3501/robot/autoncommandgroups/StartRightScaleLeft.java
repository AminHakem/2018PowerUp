package org.usfirst.frc.team3501.robot.autoncommandgroups;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartRightScaleLeft extends CommandGroup {
  private static final Constants.Direction TURN_RIGHT = Constants.Direction.RIGHT;
  private static final Constants.Direction TURN_LEFT = Constants.Direction.LEFT;

  public static final double RIGHT_WALL_TO_MIDWAYofSWITCHSCALE = 240;
  public static final double RIGHT_MIDWAYofSWITCHSCALE_TO_LEFT_MIDWAYofSWITCHSCALE = 264.0;
  public static final double LEFT_MIDWAYofSWITCHSCALE_TO_SCALE = 58.8;
  private static final double SCALE_FACTOR_TESTING = 0.1;

  public StartRightScaleLeft() {
    // robot drives straight 240 in
    addSequential(new DriveForward(
        RIGHT_WALL_TO_MIDWAYofSWITCHSCALE
            * Constants.Auton.SCALE_FACTOR_TESTING,
        0));
    MathLib.debugPrint("Finished command 1");
    // robot turns right 90 degrees
    addSequential(new TurnForAngle(90.0, TURN_LEFT, 5.0));
    MathLib.debugPrint("Finished command 2");
    // Drive straight 264 inwards
    addSequential(new DriveForward(
        RIGHT_MIDWAYofSWITCHSCALE_TO_LEFT_MIDWAYofSWITCHSCALE
            * Constants.Auton.SCALE_FACTOR_TESTING,
        0));
    MathLib.debugPrint("Finished command 3");
    // robot turns left 90 degrees
    addSequential(new TurnForAngle(90.0, TURN_LEFT, 5.0));
    MathLib.debugPrint("Finished command 4");
    // robot drives straight 58.8 in
    addSequential(new DriveForward(
        LEFT_MIDWAYofSWITCHSCALE_TO_SCALE
            * Constants.Auton.SCALE_FACTOR_TESTING,
        0));
    MathLib.debugPrint("Finished command 5");
    // robot turns left 90 degrees
    addSequential(new TurnForAngle(90.0, TURN_LEFT, 5.0));
    MathLib.debugPrint("Finished command 6");
    // robot drops cube in switch
    // addSequential(new ClimbDistance(5.0, 7.0));
    // Doubles in the command are hypothetical, subject to change
  }
}
