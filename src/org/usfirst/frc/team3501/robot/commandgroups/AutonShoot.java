package org.usfirst.frc.team3501.robot.commandgroups;

import org.usfirst.frc.team3501.robot.Constants.Direction;
import org.usfirst.frc.team3501.robot.commands.driving.DriveDistance;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonShoot extends CommandGroup {

  private static final int ROBOT_WIDTH = 40; // inches
  private static final int ROBOT_LENGTH = 36; // inches

  public AutonShoot() {
    addSequential(
        new DriveDistance(37.12 + (ROBOT_WIDTH / 2) - (ROBOT_LENGTH / 2), 5));
    addSequential(new TurnForAngle(135, Direction.LEFT, 10));
    addSequential(new DriveDistance(
        (37.12 + (ROBOT_WIDTH / 2)) * Math.sqrt(2) - 26.25 - (ROBOT_LENGTH / 2),
        10));
    addSequential(new Shoot(15 - timeSinceInitialized()));
  }
}
