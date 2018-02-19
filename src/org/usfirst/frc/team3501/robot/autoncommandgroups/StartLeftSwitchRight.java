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

  public static final double TO_CUBES = 84;
  public static final double LEFT_TO_RIGHT = 225;
  public static final double TO_SWITCH_VERT = 60;
  public static final double TO_SWITCH_HORIZ = 42;

  public StartLeftSwitchRight() {
    // did 48 in so it leaves margin for how much distance the cube is dropping
    addSequential(new MoveToTarget(48, 5));
    addSequential(new DriveForward((TO_CUBES - ROBOT_LENGTH / 2) * SCALE, 10)); // drive forward
    addSequential(new TurnForAngle(90, 3)); // turn right
    addSequential(new DriveForward(LEFT_TO_RIGHT * SCALE, 9.5)); // drive right
    addSequential(new TurnForAngle(-90, 3)); // turn left
    addSequential(new DriveForward(TO_SWITCH_VERT * SCALE, 7.5)); // drive forward
    addSequential(new TurnForAngle(-90, 3)); // turn left
    addSequential(new DriveForward(TO_SWITCH_HORIZ * SCALE, 4.5)); // drive left
    addSequential(new RunOuttake());
    addSequential(new MoveToTarget(48, 5));
  }
}
