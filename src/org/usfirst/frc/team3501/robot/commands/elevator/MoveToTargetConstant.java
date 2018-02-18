package org.usfirst.frc.team3501.robot.commands.elevator;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import org.usfirst.frc.team3501.robot.utils.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Set this to the default command for the elevator and then update targetElevatorPos
 * with the ChangeElevatorTarget
 * @author Amin
 *
 */
public class MoveToTargetConstant extends Command {


  private Elevator elevator = Robot.getElevator();
  private PIDController elevatorController;

  private double target=Elevator.BOTTOM_POS;
  private double prevVal;
  Timer timer;
  double previousTarget=Elevator.BOTTOM_POS;

  public MoveToTargetConstant() {
    requires(elevator);
    this.target = elevator.getTargetElevatorPos();
    this.previousTarget = target;

    this.elevatorController = new PIDController(Elevator.ELEVATOR_P,
        Elevator.ELEVATOR_I, Elevator.ELEVATOR_D);
    this.elevatorController.setDoneRange(1.0);
    this.elevatorController.setMaxOutput(0.75);
    this.elevatorController.setMinDoneCycles(5);
  }

  @Override
  protected void initialize() {
    this.elevatorController.setSetPoint(this.target);
    this.previousTarget = elevator.getTargetElevatorPos();
    timer.start();
  }

  @Override
  protected void execute() {
    target = elevator.getTargetElevatorPos();
    if(target!=previousTarget) {
      double startTime = timer.get();
      while(timer.get()<startTime+0.5) {
        elevator.setMotorValue(0);
      }
      this.previousTarget = target;
    }
    elevatorController.setSetPoint(target);
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
