package org.usfirst.frc.team3501.robot.autoncommandgroups;

import org.usfirst.frc.team3501.robot.commands.driving.AlignWithCube;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.DriveSideways;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;
import org.usfirst.frc.team3501.robot.commands.elevator.ChangeElevatorTarget;
import org.usfirst.frc.team3501.robot.commands.elevator.MoveToTarget;
import org.usfirst.frc.team3501.robot.commands.intake.RunIntake;
import org.usfirst.frc.team3501.robot.commands.intake.RunOuttake;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchRightScaleRightCube extends CommandGroup {

  public static final double constTurn = 34.65;
  public static final short robotLength = 32;
  public static final short robotWidth = 27;
  public static final short scaleHeight = 60;
  public static final double diagonal = 67.15;

  public SwitchRightScaleRightCube() {
    addSequential(new DriveSideways(robotWidth + 12, 5));
    addSequential(new TurnForAngle(-1 * constTurn, 5));
    addSequential(new AlignWithCube());
    addSequential(new RunIntake());
    addSequential(new DriveForward(1, 1));
    addSequential(new ChangeElevatorTarget(scaleHeight));
    addSequential(new DriveForward(-1 * diagonal, 4));
    addSequential(new TurnForAngle(-1 * constTurn, 2));
    addSequential(new DriveForward(71.75 - robotLength, 5));
    addSequential(new RunOuttake());
  }
}
