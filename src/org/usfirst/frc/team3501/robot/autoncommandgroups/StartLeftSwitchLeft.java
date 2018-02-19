package org.usfirst.frc.team3501.robot.autoncommandgroups;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;
import org.usfirst.frc.team3501.robot.commands.elevator.MoveToTarget;
import org.usfirst.frc.team3501.robot.commands.intake.RunOuttake;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartLeftSwitchLeft extends CommandGroup {

  public static final double ROBOT_LENGTH = 38.0;
  public static final double ROBOT_WIDTH = 33.0;
  public static final double SCALE = Constants.Auton.SCALE_FACTOR_TESTING;

  public static final double VERT_SWITCH_DIST = 168.0;
  public static final double HORIZ_SWITCH_DIST = 61 - ROBOT_WIDTH / 2;

  public StartLeftSwitchLeft() {
    addSequential(new DriveForward((VERT_SWITCH_DIST - ROBOT_LENGTH / 2) * SCALE, 100));
    addSequential(new TurnForAngle(90.0, 5.0));
    addSequential(new MoveToTarget(Elevator.SWITCH_POS, 20));
    addSequential(new DriveForward((HORIZ_SWITCH_DIST - ROBOT_WIDTH / 2) * SCALE, 6.5));
    addSequential(new RunOuttake());
  }
}
