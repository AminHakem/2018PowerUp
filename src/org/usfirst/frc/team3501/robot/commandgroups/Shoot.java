package org.usfirst.frc.team3501.robot.commandgroups;

import org.usfirst.frc.team3501.robot.commands.shooter.RunFlyWheel;
import org.usfirst.frc.team3501.robot.commands.shooter.RunIndexWheel;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class Shoot extends CommandGroup {

  public Shoot(double time) {
    addParallel(new RunFlyWheel(time));
    addSequential(new WaitCommand(2));
    addParallel(new RunIndexWheel(time - 2));
  }
}
