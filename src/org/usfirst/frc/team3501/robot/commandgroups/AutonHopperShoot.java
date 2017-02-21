package org.usfirst.frc.team3501.robot.commandgroups;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.Constants.Direction;
import org.usfirst.frc.team3501.robot.commands.driving.DriveDistance;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * // Robot starts in middle, goes to the hopper, then boiler,then shoots during
 * auton
 */
public class AutonHopperShoot extends CommandGroup {
  // If red, direction is right; if blue, direction is left
  private static final Direction DIRECTION_TO_HOPPER = Constants.Direction.LEFT;
  // If red, direction is left; if blue, direction is right
  private static final Direction DIRECTION_TO_BOILER = Constants.Direction.RIGHT;

  private Timer timer;

  public AutonHopperShoot() {
    timer = new Timer();
    // Robot drives from center to front of airship
    addSequential(new DriveDistance(78.5, 2.7));
    // Robot turns towards hopper
    addSequential(new TurnForAngle(90.0, DIRECTION_TO_HOPPER, 2.5));
    // Robot drives into hopper switch
    addSequential(new DriveDistance(42.12, 5.25));
    addSequential(new WaitCommand(1));
    // Robot backs up from switch
    addSequential(new DriveDistance(-25.0, 2.9));
    // Robot turns towards the boiler
    addSequential(new TurnForAngle(90.0, DIRECTION_TO_HOPPER, 5.0));
    // Robot drives to boiler
    addSequential(new DriveDistance(90, 5.0));
    // Robot turns parallel to boiler
    addSequential(new TurnForAngle(45, DIRECTION_TO_BOILER, 5.0));
    // Shoot
    addSequential(new Shoot(15 - timeSinceInitialized()));
  }

}
