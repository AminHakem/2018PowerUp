package org.usfirst.frc.team3501.robot.autoncommandgroups;

import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.DriveSideways;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;
import org.usfirst.frc.team3501.robot.commands.elevator.ChangeElevatorTarget;
import org.usfirst.frc.team3501.robot.commands.intake.RunIntakeOnTime;
import org.usfirst.frc.team3501.robot.commands.intake.RunOuttake;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class SwitchRightScaleRight extends CommandGroup {

  public static final double constTurn = 40;
  public static final short robotLength = 32;
  public static final short robotWidth = 27;
  public static final short scaleHeight = 67;
  public static final double diagonal = 67.15;

  public SwitchRightScaleRight() {
    addSequential(new DriveSideways(robotWidth + 12, 5));
    addSequential(new DriveForward(-20, 1));
    addSequential(new ChangeElevatorTarget(0));
    addSequential(new WaitCommand(1));
    addSequential(new DriveForward(20, 1));
    addSequential(new TurnForAngle(-1 * constTurn, 5));
    addSequential(new DriveForward(16, 1)); // just in case too far away from block
    addSequential(new RunIntakeOnTime(1.0));
    addParallel(new TurnForAngle(1 * constTurn, 5));
    addSequential(new ChangeElevatorTarget(scaleHeight));
    addSequential(new DriveForward(-20, 4));
    addSequential(new DriveSideways(110, 7));
    // addSequential(new DriveForward(/* 71.75 - robotLength */20, 5));
    addSequential(new DriveForward(30, 1));
    addSequential(new WaitCommand(2));
    addSequential(new RunOuttake());
  }
}
