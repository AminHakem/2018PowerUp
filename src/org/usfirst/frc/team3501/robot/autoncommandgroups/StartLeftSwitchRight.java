package org.usfirst.frc.team3501.robot.autoncommandgroups;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.DriveSideways;
import org.usfirst.frc.team3501.robot.commands.driving.ResetGyro;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;
import org.usfirst.frc.team3501.robot.commands.elevator.ChangeElevatorTarget;
import org.usfirst.frc.team3501.robot.commands.intake.RunOuttake;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This auton command starts at the middle, goes to the pile of cubes in the middle of the switch's
 * front and intakes a power cube. Afterwards, the robot travels left, and after surpassing the
 * switch does a u-turn to the left, and drops the cube to the switch
 *
 * @author Ayush Nigade
 */
public class StartLeftSwitchRight extends CommandGroup {

  public static final double ROBOT_LENGTH = 38.0;
  public static final double SCALE = Constants.Auton.SCALE_FACTOR_TESTING;

  public static final double TO_CUBES = 93;
  public static final double LEFT_TO_RIGHT = 135;
  public static final double TO_SWITCH_VERT = 60;

  public StartLeftSwitchRight() {
    addSequential(new ChangeElevatorTarget(Elevator.SWITCH_POS));
    addSequential(new DriveSideways(-(TO_CUBES - ROBOT_LENGTH) * SCALE, 10));
    addSequential(new DriveForward(LEFT_TO_RIGHT * SCALE, 10));
    addSequential(new TurnForAngle(-90, 5));
    addSequential(new ResetGyro());
    addSequential(new DriveForward(TO_SWITCH_VERT * SCALE, 7.5));
    addSequential(new RunOuttake());
  }
}
