package org.usfirst.frc.team3501.robot.autoncommandgroups;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartRightSwitchRightStraight extends CommandGroup {

  public static final double ROBOT_LENGTH = 38.0;
  public static final double ROBOT_WIDTH = 33.0;
  public static final double SCALE = Constants.Auton.SCALE_FACTOR_TESTING;

  public static final double VERT_SWITCH_DIST = 150;
  public static final double HORIZ_SWITCH_DIST = 30;

  public StartRightSwitchRightStraight() {
    // addSequential(new ChangeElevatorTarget(Elevator.SWITCH_POS));
    addSequential(new DriveForward(VERT_SWITCH_DIST, 10));
    // addSequential(new TurnForAngle(-90, 5));
    // addSequential(new DriveForward((HORIZ_SWITCH_DIST) * SCALE, 6.5));


    // addSequential(new RunOuttake());
    // addSequential(new TurnForAngle(90, 5));
    // addSequential(new ResetGyro());
  }
}

