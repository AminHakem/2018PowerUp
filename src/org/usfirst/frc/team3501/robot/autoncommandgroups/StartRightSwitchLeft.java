package org.usfirst.frc.team3501.robot.autoncommandgroups;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;
import org.usfirst.frc.team3501.robot.commands.elevator.MoveToTarget;
import org.usfirst.frc.team3501.robot.commands.intake.RunOuttake;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartRightSwitchLeft extends CommandGroup {
  private static final Constants.Direction TURN_RIGHT = Constants.Direction.RIGHT;
  private static final Constants.Direction TURN_LEFT = Constants.Direction.LEFT;

  public static final double RIGHT_BACK_WALL_TO_SWITCH_CUBES = 98;
  public static final double RIGHT_WALL_TO_LEFT_WALL_HORIZONTAL = 264.0;
  public static final double LEFT_WALL_TO_LEFT_SCALE_VERTICAL = 58.8;
  public static final double LEFT_SCALE_TO_SCALE = 39;
  private static final double SCALE_FACTOR_TESTING = 0.1;

  public StartRightSwitchLeft() {
    // did 48 in so it leaves margin for how much distance the cube is dropping
    addSequential(new MoveToTarget(48, 5));
    // robot drives straight 240 in
    addSequential(new DriveForward(
        RIGHT_BACK_WALL_TO_SWITCH_CUBES * Constants.Auton.SCALE_FACTOR_TESTING, 9.5));
    MathLib.debugPrint("Finished command 1");
    // robot turns right 90 degrees
    addSequential(new TurnForAngle(-90.0, 3.0));
    MathLib.debugPrint("Finished command 2");
    // Drive straight 264 inwards
    addSequential(new DriveForward(
        RIGHT_WALL_TO_LEFT_WALL_HORIZONTAL * Constants.Auton.SCALE_FACTOR_TESTING, 24));
    MathLib.debugPrint("Finished command 3");
    // robot turns left 90 degrees
    addSequential(new TurnForAngle(-90.0, 5.0));
    MathLib.debugPrint("Finished command 4");
    // robot drives straight 58.8 in
    addSequential(new DriveForward(
        LEFT_WALL_TO_LEFT_SCALE_VERTICAL * Constants.Auton.SCALE_FACTOR_TESTING, 0));
    MathLib.debugPrint("Finished command 5");
    // robot turns left 90 degrees
    addSequential(new TurnForAngle(-90.0, 5.0));
    MathLib.debugPrint("Finished command 6");
    // robot moves forward to the scale in its final move
    addSequential(
        new DriveForward(LEFT_SCALE_TO_SCALE * Constants.Auton.SCALE_FACTOR_TESTING, 1.5));
    MathLib.debugPrint("Finished command 6");
    addSequential(new RunOuttake());
    addSequential(new MoveToTarget(48, 5));

  }
}
