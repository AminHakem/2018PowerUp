package org.usfirst.frc.team3501.robot.autoncommandgroups;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.DriveSideways;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;
import org.usfirst.frc.team3501.robot.commands.elevator.ChangeElevatorTarget;
import org.usfirst.frc.team3501.robot.commands.elevator.MoveToTarget;
import org.usfirst.frc.team3501.robot.commands.intake.RunOuttake;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartRightSwitchLeft extends CommandGroup {

  public static final double ROBOT_LENGTH = 38.0;
  public static final double SCALE = Constants.Auton.SCALE_FACTOR_TESTING;

  public static final double TO_CUBES = 93;
  public static final double RIGHT_TO_LEFT = 155;
  public static final double TO_SWITCH_VERT = 60;

  public StartRightSwitchLeft() {
    addSequential(new ChangeElevatorTarget(Elevator.SWITCH_POS));
    addSequential(new DriveForward((TO_CUBES - ROBOT_LENGTH) * SCALE, 10));
    addSequential(new DriveSideways(-RIGHT_TO_LEFT * SCALE, 10));
    addSequential(new DriveForward(TO_SWITCH_VERT * SCALE, 7.5));
    addSequential(new RunOuttake());
  }
}
