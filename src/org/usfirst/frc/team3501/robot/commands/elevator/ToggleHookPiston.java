package org.usfirst.frc.team3501.robot.commands.elevator;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.command.Command;

public class ToggleHookPiston extends Command {
  private Elevator elevator = Robot.getElevator();

  @Override
  protected void initialize() {
    this.elevator.togglePiston();
  }

  @Override
  protected void execute() {}

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {}

  @Override
  protected void interrupted() {
    end();
  }
}
