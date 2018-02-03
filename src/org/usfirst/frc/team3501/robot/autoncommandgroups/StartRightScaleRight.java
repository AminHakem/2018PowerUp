package org.usfirst.frc.team3501.robot.autoncommandgroups;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.Constants.Direction;
import org.usfirst.frc.team3501.robot.MathLib;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartRightScaleRight extends CommandGroup {

  private static final Direction TURN_LEFT = Constants.Direction.LEFT;

  public static final double RIGHT_WALL_TO_SWTICH = 168.0;
  public static final double SWITCH_TO_SCALE = 68.0;

  private static final double SCALE_FACTOR_TESTING = 0.1;

  public StartRightScaleRight() {
    // move forward 168 in
    addSequential(
        new DriveForward(
            RIGHT_WALL_TO_SWTICH * Constants.Auton.SCALE_FACTOR_TESTING, 0));
    MathLib.debugPrint("Finished command 1");
    // turn 90 degrees left
    addSequential(new TurnForAngle(90.0, TURN_LEFT, 5.0));
    MathLib.debugPrint("Finished command 2");
    // move forward 68 inches
    addSequential(
        new DriveForward(SWITCH_TO_SCALE * Constants.Auton.SCALE_FACTOR_TESTING,
            0));
    MathLib.debugPrint("Finished command 3");
    // No elevator yet, waiting for actual elevator command, numbers are
    // hypothetical and are subject to change
    // addSequential(new ClimbDistance(5.0, 7.0));
  }
}
