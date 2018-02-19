package org.usfirst.frc.team3501.robot.autoncommandgroups;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;
import org.usfirst.frc.team3501.robot.commands.elevator.MoveToTarget;
import org.usfirst.frc.team3501.robot.commands.intake.RunOuttake;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartRightScaleLeft extends CommandGroup {

  public static final double ROBOT_LENGTH = 38.0;
  public static final double SCALE = Constants.Auton.SCALE_FACTOR_TESTING;

  public static final double TO_PLATFORM_ZONE = 240;
  public static final double THROUGH_PLATFORM_ZONE = 264.0;
  public static final double TO_NULL_TERRITORY = 58.8;

  public StartRightScaleLeft() {
    addSequential(new MoveToTarget(84, 5));
    // robot drives straight 240 in
    addSequential(new DriveForward(TO_PLATFORM_ZONE * SCALE, 0)); // forward
    MathLib.debugPrint("Finished command 1");
    // robot turns right 90 degrees
    addSequential(new TurnForAngle(-90.0, 5.0)); // turn left
    addSequential(new DriveForward(THROUGH_PLATFORM_ZONE * SCALE, 0)); // left
    addSequential(new TurnForAngle(-90.0, 5.0)); // turn
    addSequential(new DriveForward(TO_NULL_TERRITORY * SCALE, 0));
    MathLib.debugPrint("Finished command 5");
    // robot turns left 90 degrees
    addSequential(new TurnForAngle(-90.0, 5.0));
    MathLib.debugPrint("Finished command 6");
    addSequential(new RunOuttake());
    addSequential(new MoveToTarget(84, 5));
  }
}
