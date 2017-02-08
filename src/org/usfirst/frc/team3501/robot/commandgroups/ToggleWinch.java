package org.usfirst.frc.team3501.robot.commandgroups;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.commands.climber.MaintainClimbedPosition;
import org.usfirst.frc.team3501.robot.commands.climber.RunWinchContinuous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ToggleWinch extends CommandGroup {

  public ToggleWinch() {
    if (!Robot.getDriveTrain().isClimbing()) {
      Robot.getDriveTrain().setClimbing(true);
      addSequential(new RunWinchContinuous());

    } else {
      Robot.getDriveTrain().setClimbing(false);
      addSequential(new MaintainClimbedPosition());
    }
  }

}
