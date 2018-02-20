package org.usfirst.frc.team3501.robot.autoncommandgroups;

import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.DriveSideways;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;
import org.usfirst.frc.team3501.robot.commands.elevator.MoveToTarget;
import org.usfirst.frc.team3501.robot.commands.intake.RunIntake;
import org.usfirst.frc.team3501.robot.commands.intake.RunOuttake;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchRightScaleRight extends CommandGroup {

  public static final double constTurn = 34.65;
  public static final short robotLength = 32;
  public static final short robotWidth = 27;
  public static final short scaleHeight = 60;
  public static final double diagonal = 67.15;

  public SwitchRightScaleRight() {
    addSequential(new DriveSideways(robotWidth + 12, 5));
    addSequential(new TurnForAngle(-1 * constTurn, 5));
    addSequential(new DriveForward(1, 1)); // just in case too far away from block
    addSequential(new RunIntake());
    addSequential(new MoveToTarget(scaleHeight, 7));
    addSequential(new DriveForward(-1 * diagonal, 4));
    addSequential(new TurnForAngle(-1 * constTurn, 2));
    addSequential(new DriveForward(71.75 - robotLength, 5));
    addSequential(new RunOuttake());
  }
}