package org.usfirst.frc.team3501.robot.autoncommandgroups.switchcommands;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.DriveSideways;
import org.usfirst.frc.team3501.robot.commands.driving.ResetGyro;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;
import org.usfirst.frc.team3501.robot.commands.elevator.ChangeElevatorTarget;
import org.usfirst.frc.team3501.robot.commands.intake.RunOuttake;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartRightSwitchLeft extends CommandGroup {

  public static final double ROBOT_LENGTH = 38.0;
  public static final double SCALE = Constants.Auton.SCALE_FACTOR_TESTING;

  public static final double FIRST_VERT_DIST = 518;
  public static final double LEFT_TO_RIGHT = 439;
  public static final double TO_SWITCH_VERT = 10;

  public StartRightSwitchLeft() {
    addSequential(new ChangeElevatorTarget(Elevator.SWITCH_POS));
    addSequential(new DriveSideways((FIRST_VERT_DIST) * SCALE, 10));
    addSequential(new DriveForward(LEFT_TO_RIGHT * SCALE, 10));
    addSequential(new TurnForAngle(-90, 5));
    addSequential(new ResetGyro());
    addSequential(new DriveForward(TO_SWITCH_VERT * SCALE, 7.5));
    addSequential(new RunOuttake());
  }
}
