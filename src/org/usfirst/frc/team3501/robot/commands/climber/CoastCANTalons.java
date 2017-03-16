package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CoastCANTalons extends Command {
  Climber climber = Robot.getClimber();

  public CoastCANTalons() {
    requires(climber);
  }

  @Override
  protected void initialize() {
    climber.setCANTalonsBrakeMode(climber.COAST_MODE);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
