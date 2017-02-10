package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleWinch extends Command {
  DriveTrain driveTrain = Robot.getDriveTrain();
  private double climbingSpeed;
  private double maintainPositionSpeed;

  public ToggleWinch() {
    requires(driveTrain);
    climbingSpeed = driveTrain.CLIMBER_SPEED;
    maintainPositionSpeed = driveTrain.MAINTAIN_CLIMBED_POSITION;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (driveTrain.shouldBeClimbing) {
      driveTrain.setMotorValues(climbingSpeed, climbingSpeed);
    } else {
      driveTrain.setMotorValues(maintainPositionSpeed, maintainPositionSpeed);
    }
  }

  @Override
  protected boolean isFinished() {
    return Robot.getOI().toggleWinch.get();
  }

  @Override
  protected void end() {
    driveTrain.shouldBeClimbing = !driveTrain.shouldBeClimbing;
  }

  @Override
  protected void interrupted() {
    end();
  }
}
