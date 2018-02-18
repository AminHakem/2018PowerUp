package org.usfirst.frc.team3501.robot.commands.elevator;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.command.Command;

public class SetMotorValue extends Command {

  private Elevator elevator = Robot.getElevator();
  private double maxTimeOut;
  private double motorValue;

  public SetMotorValue(double motorValue, double maxTimeOut) {
    requires(elevator);
    this.maxTimeOut = maxTimeOut;
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    System.out.println("Setting motor value");
    elevator.setMotorValue(this.motorValue);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    this.elevator.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
