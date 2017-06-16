
package org.usfirst.frc.team3501.robot.commandgroups;

import org.usfirst.frc.team3501.robot.commands.driving.ShiftGearManipulatorPistonLow;
import org.usfirst.frc.team3501.robot.commands.driving.TimeDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * Authors Ayush, Nadia, Aziza and Abhinav
 *
 * This comandGroup, PlaceGearOnMiddlePeg is expected to have the robot start
 * from the middle of the starting line, right in front of the airship. The
 * robot will drive forward and place the gear on the peg. Then, the robot will
 * drive backwards, turn left, drive forward, turn right, and drive forward
 * again to cross the baseline.
 */
public class AutonMiddleGear extends CommandGroup {
  private static final double DISTANCE_TO_PEG = 91.3 - 32;
  private static final double maxTimeOut = 7;

  /***
   * This auton command group places the gear on the middle peg then crosses the
   * baseline
   *
   * @param direction
   *          direction to turn after placing gear on peg in order to cross the
   *          baseline. Only Direction.LEFT and Direction.RIGHT will be accepted
   */
  public AutonMiddleGear() {
    // addSequential(new DriveDistance(DISTANCE_TO_PEG, maxTimeOut));

    addSequential(new ShiftGearManipulatorPistonLow());
    addSequential(new TimeDrive(2, 0.6));
  }
}
