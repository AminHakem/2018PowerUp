package org.usfirst.frc.team3501.robot.commandgroups;

import org.usfirst.frc.team3501.robot.Constants.Direction;
import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.commands.driving.DriveDistance;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonSideGear extends CommandGroup {

  private static final double ROBOT_LENGTH = 36.0;
  private static final double ROBOT_WIDTH = 40.0;

  private static final double STRAIGHT_DIST_FROM_BOILER = 76.8;
  private static final double DISTANCE_TO_RETRIEVAL_PEG = 114.3 + 17.625
      - ((162 - (17.625 + 35.25) - 38.625) / Math.sqrt(3));
  private static final double LENGTH_OF_PEG = 10.5;
  private static final double PEG_EXTENDED_BOILER = 117.518;
  private static final double PEG_EXTENDED_RETRIEVAL_ZONE = (DISTANCE_TO_RETRIEVAL_PEG
      * 2) / Math.sqrt(3);
  private static final double DISTANCE_BETWEEN_BOILER_AND_RETRIEVAL_ZONE = 255.6765151902;
  private static final double MOTOR_VALUE_TURN = 0.5;// this is probably not
                                                     // correct
  private static final double MOTOR_VALUE_FORWARD = 0.5;// random
  double distanceFromCorner;

  /**
   * this is an auton strategy to cross the baseline and put a gear on a peg
   * requires predetermined starting point anywhere except between 109.1767
   * inches and 205.7286 inches from the right side of the arena this program
   * chooses which peg to go for based on the starting point
   */
  public AutonSideGear(String side) {
    requires(Robot.getDriveTrain());

    if (side.equals("BOILER")) {
      addSequential(new DriveDistance(
          131.6 - (94.88 - ROBOT_WIDTH / 2 - distanceFromCorner) / Math.sqrt(3)
              - ROBOT_LENGTH / 2,
          5));
      addSequential(new TurnForAngle(60, Direction.RIGHT, 5));
      addSequential(new DriveDistance(
          2 * (94.88 - ROBOT_WIDTH / 2 - distanceFromCorner) / Math.sqrt(3)
              - ROBOT_LENGTH / 2 + 7,
          5));
    } else if (side.equals("RETRIEVAL")) {
      addSequential(new DriveDistance(
          131.6 - (93.13 - ROBOT_WIDTH / 2 - distanceFromCorner) / Math.sqrt(3)
              - ROBOT_LENGTH / 2,
          5));
      addSequential(new TurnForAngle(60, Direction.LEFT, 5));
      addSequential(new DriveDistance(
          2 * (93.13 - ROBOT_WIDTH / 2 - distanceFromCorner) / Math.sqrt(3)
              - ROBOT_LENGTH / 2,
          5));
    }
  }
}
