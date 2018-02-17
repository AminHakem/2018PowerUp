package org.usfirst.frc.team3501.robot.autoncommandgroups;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.Constants.Direction;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;
import org.usfirst.frc.team3501.robot.commands.elevator.MoveToTarget;
import org.usfirst.frc.team3501.robot.commands.intake.RunOuttake;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This auton command starts at the middle, goes to the pile of cubes in the middle of the switch's
 * front and intakes a power cube. Afterwards, the robot travels left, and after surpassing the
 * switch does a u-turn to the left, and drops the cube to the switch
 *
 * @author Ayush Nigade
 */
public class CenterToRight extends CommandGroup {
  private static final Direction DIRECTION_TO_SWITCH = Direction.RIGHT;
  private static final Direction DIRECTION_TO_SWITCH1 = Direction.LEFT;

  public static final double MIDDLE_OF_BACK_WALL_TO_CUBE = 98;
  public static final double CUBE_TO_RIGHT_WALL_HORIZONTAL = 84;
  public static final double RIGHT_WALL_TO_MIDWAY_RIGHT_BETWEEN_SWITCHWALL = 60;
  public static final double MIDWAY_RIGHT_SCALEWALL_TO_RIGHT_SWITCH = 39;

  public CenterToRight() {
    Timer timer = new Timer();
    timer.start();
    // did 48 in so it leaves margin for how much distance the cube is dropping
    addSequential(new MoveToTarget(48, 5));
    // Move forward 98 in.
    addSequential(
        new DriveForward(MIDDLE_OF_BACK_WALL_TO_CUBE * Constants.Auton.SCALE_FACTOR_TESTING, 10));
    System.out.println("Finished command 1");
    // intake a cube
    // addSequential(new RunIntake());
    System.out.println("Finished command 2");
    // turn right 90 degrees
    addSequential(new TurnForAngle(90, 3));
    System.out.println("Finished command 3");
    // drive 84 in.
    addSequential(
        new DriveForward(CUBE_TO_RIGHT_WALL_HORIZONTAL * Constants.Auton.SCALE_FACTOR_TESTING, 9));
    System.out.println("Finished command 4");
    // turn left 90 degrees
    addSequential(new TurnForAngle(-90, 3));
    System.out.println("Finished command 5");
    // drive 60 in.
    addSequential(new DriveForward(
        RIGHT_WALL_TO_MIDWAY_RIGHT_BETWEEN_SWITCHWALL * Constants.Auton.SCALE_FACTOR_TESTING, 7));
    System.out.println("Finished command 6");
    // turn 90 degrees left
    addSequential(new TurnForAngle(-90, 3));
    System.out.println("Finished command 7");
    // drive 12 in.
    addSequential(new DriveForward(
        MIDWAY_RIGHT_SCALEWALL_TO_RIGHT_SWITCH * Constants.Auton.SCALE_FACTOR_TESTING, 5));
    System.out.println("Finished command 8");
    addSequential(new RunOuttake());
    addSequential(new MoveToTarget(48, 5));
    System.out.println("TimeTaken for " + this.getClass().getName() + " : " + timer.get());
  }
}
