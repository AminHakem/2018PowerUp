package org.usfirst.frc.team3501.robot.autoncommandgroups;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;
import org.usfirst.frc.team3501.robot.commands.elevator.MoveToTarget;
import org.usfirst.frc.team3501.robot.commands.intake.RunOuttake;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This auton command starts at the middle, goes to the pile of cubes in the middle of the switch's
 * front and intakes a power cube. Afterwards, the robot travels left, and after surpassing the
 * switch does a u-turn to the left, and drops the cube to the switch
 *
 * @author Ayush Nigade
 */
public class StartLeftSwitchRight extends CommandGroup {

  public static final double ROBOT_LENGTH = 38.0;
  public static final double SCALE = Constants.Auton.SCALE_FACTOR_TESTING;

  public static final double LEFT_WALL_TO_CUBES_VERTICAL = 84;
  public static final double LEFT_WALL_TO_RIGHT_WALL_HORIZONTAL = 225;
  public static final double RIGHT_WALL_TO_SWITCH_VERTICAL = 60;
  public static final double RIGHT_WALL_TO_SWITCH_HORIZONTAL = 42;

  public StartLeftSwitchRight() {
    // did 48 in so it leaves margin for how much distance the cube is dropping
    addSequential(new MoveToTarget(48, 5));
    addSequential(new DriveForward(LEFT_WALL_TO_CUBES_VERTICAL * SCALE, 10));
    addSequential(new TurnForAngle(90, 3));
    addSequential(new DriveForward(LEFT_WALL_TO_RIGHT_WALL_HORIZONTAL * SCALE, 9.5));
    // turn left 90 degrees
    addSequential(new TurnForAngle(-90, 3));
    // drive 60 in.
    addSequential(new DriveForward(RIGHT_WALL_TO_SWITCH_VERTICAL * SCALE, 7.5));
    // turn 90 degrees left
    addSequential(new TurnForAngle(-90, 3));
    // drive 12 in.
    addSequential(new DriveForward(RIGHT_WALL_TO_SWITCH_HORIZONTAL * SCALE, 4.5));
    addSequential(new RunOuttake());
    addSequential(new MoveToTarget(48, 5));
  }
}
