package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleWinch extends Command {
  Climber climber = Robot.getClimber();
  private double climbingSpeed;

  public ToggleWinch() {
    requires(climber);
    climbingSpeed = climber.CLIMBER_SPEED;
  }

  @Override
  protected void initialize() {
    System.out.println("toggled");
  }

  @Override
  protected void execute() {
    if (climber.shouldBeClimbing) {
      climber.setCANTalonsBrakeMode(climber.COAST_MODE);
      climber.setMotorValues(climbingSpeed);
      System.out.println("climbing");
    } else {
      climber.setCANTalonsBrakeMode(climber.BRAKE_MODE);

      System.out.println("braked");
      /* Not sure if should have */
      climber.stop();
      end();
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    climber.shouldBeClimbing = !climber.shouldBeClimbing;
    System.out.println("ended");
  }

  @Override
  protected void interrupted() {
    end();
  }
}
