package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class ReverseFlyWheelContinuous extends Command {
  private Shooter shooter = Robot.getShooter();

  public ReverseFlyWheelContinuous() {
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    shooter.reverseFlyWheel();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    shooter.stopFlyWheel();
  }

  @Override
  protected void interrupted() {
    end();
  }

}
