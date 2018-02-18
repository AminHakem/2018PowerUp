package org.usfirst.frc.team3501.robot.commands.elevator;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import org.usfirst.frc.team3501.robot.utils.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveToTargetConstant extends Command {


  private Elevator elevator = Robot.getElevator();
  private PIDController elevatorController;

  private double maxTimeOut;
  private double target;
  private double prevVal;
  Timer timer;

  /**
   * @param target the height the elevator will move to in inches
   * @param maxTimeOut the maximum time this command will be allowed to run before being cut
   */
  public MoveToTargetConstant(double target) {
    requires(elevator);
    this.target = elevator.getTargetElevatorPos();

    this.elevatorController = new PIDController(Elevator.ELEVATOR_P,
        Elevator.ELEVATOR_I, Elevator.ELEVATOR_D);
    this.elevatorController.setDoneRange(1.0);
    this.elevatorController.setMaxOutput(0.75);
    this.elevatorController.setMinDoneCycles(5);
  }

  @Override
  protected void initialize() {
    this.elevatorController.setSetPoint(this.target);
    timer.start();
  }

  @Override
  protected void execute() {
    this.target = elevator.getTargetElevatorPos();
    double current = elevator.getHeight();
    double val = elevatorController.calcPID(current);

    if (val - prevVal > Elevator.ACCELERATION_CONTROL) {
      this.elevator.setMotorValue(val + Elevator.ACCELERATION_CONTROL);
    } else {
      this.elevator.setMotorValue(val);
    }
    val = prevVal;
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
