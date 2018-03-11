package org.usfirst.frc.team3501.robot.commands.elevator;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.command.Command;

public class ToggleHookPiston extends Command {
  private Elevator elevator = Robot.getElevator();
    public ToggleHookPiston() {
      requires(elevator);
    }
  @Override
  protected void initialize() {
    this.elevator.togglePiston();
  }

  @Override
  protected void execute() {
    elevator.setMotorValue(-0.3);
    System.out.println("toggle hook execute");
  }

  @Override
  protected boolean isFinished() {
    return timeSinceInitialized()>1;
  }

  @Override
  protected void end() {
    elevator.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
