package org.usfirst.frc.team3501.robot.autoncommandgroups;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.Constants.Direction;
import org.usfirst.frc.team3501.robot.MathLib;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;
import org.usfirst.frc.team3501.robot.commands.intake.RunIntake;
import org.usfirst.frc.team3501.robot.commands.intake.RunOuttake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This auton command starts at the middle, goes to the pile of cubes in the
 * middle of the switch's front and intakes a power cube. Afterwards, the robot
 * travels left, and after surpassing the switch does a u-turn to the left, and
 * drops the cube to the switch
 *
 * @author Ayush Nigade
 */
public class StartLeftSwitchRight extends CommandGroup {
  private static final Direction DIRECTION_TO_SWITCH = Direction.RIGHT;
  private static final Direction DIRECTION_TO_SWITCH1 = Direction.LEFT;
  // added constants. If names sound choppy or does not make sense, please
  // change

  public static final double LEFT_WALL_TO_CUBES_VERTICAL = 98;
  public static final double LEFT_WALL_TO_RIGHT_WALL_HORIZONTAL = 84;
  public static final double RIGHT_WALL_TO_SWITCH_VERTICAL = 60;
  public static final double RIGHT_WALL_TO_SWITCH_HORIZONTAL = 42;

  public StartLeftSwitchRight() {

    // Move forward 98 in.
    addSequential(new DriveForward(
        LEFT_WALL_TO_CUBES_VERTICAL * Constants.Auton.SCALE_FACTOR_TESTING,
        10));

    MathLib.debugPrint("Finished command 1");
    // intake a cube
    addSequential(new RunIntake());
    MathLib.debugPrint("Finished command 2");
    // turn right 90 degrees
    addSequential(new TurnForAngle(90, DIRECTION_TO_SWITCH, 3));
    MathLib.debugPrint("Finished command 3");
    // drive 84 in.
    addSequential(new DriveForward(LEFT_WALL_TO_RIGHT_WALL_HORIZONTAL
        * Constants.Auton.SCALE_FACTOR_TESTING, 9.5));
    MathLib.debugPrint("Finished command 4");
    // turn left 90 degrees
    addSequential(new TurnForAngle(90, DIRECTION_TO_SWITCH1, 3));
    MathLib.debugPrint("Finished command 5");
    // drive 60 in.
    addSequential(new DriveForward(
        RIGHT_WALL_TO_SWITCH_VERTICAL * Constants.Auton.SCALE_FACTOR_TESTING,
        7.5));
    MathLib.debugPrint("Finished command 6");
    // turn 90 degrees left
    addSequential(new TurnForAngle(90, DIRECTION_TO_SWITCH1, 3));
    MathLib.debugPrint("Finished command 7");
    // drive 12 in.
    addSequential(new DriveForward(
        RIGHT_WALL_TO_SWITCH_HORIZONTAL * Constants.Auton.SCALE_FACTOR_TESTING,
        4.5));
    MathLib.debugPrint("Finished command 8");
    // elevator climb 20 in.
    // addSequential(new ClimbDistance(20, 3));
    // dump/outtake the cube onto switch
    addSequential(new RunOuttake());
  }
}
