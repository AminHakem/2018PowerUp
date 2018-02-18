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
public class CenterToRight extends CommandGroup {

  public static final double ROBOT_LENGTH = 38.0;
  public static final double SCALE = Constants.Auton.SCALE_FACTOR_TESTING;

  public static final double TO_CUBES = 98;
  public static final double TO_RIGHT_WALL = 84;
  public static final double RIGHT_WALL_TO_MIDWAY_RIGHT_BETWEEN_SWITCHWALL = 60;
  public static final double MIDWAY_RIGHT_SCALEWALL_TO_RIGHT_SWITCH = 39;

  public CenterToRight() {
    addSequential(new MoveToTarget(48, 5));
    addSequential(new DriveForward(TO_CUBES * SCALE, 10));
    addSequential(new TurnForAngle(90, 3));
    addSequential(new DriveForward(TO_RIGHT_WALL * SCALE, 9));
    addSequential(new TurnForAngle(-90, 3));
    addSequential(new DriveForward(RIGHT_WALL_TO_MIDWAY_RIGHT_BETWEEN_SWITCHWALL * SCALE, 7));
    addSequential(new TurnForAngle(-90, 3));
    addSequential(new DriveForward(MIDWAY_RIGHT_SCALEWALL_TO_RIGHT_SWITCH * SCALE, 5));
    addSequential(new RunOuttake());
    addSequential(new MoveToTarget(48, 5));
  }
}
