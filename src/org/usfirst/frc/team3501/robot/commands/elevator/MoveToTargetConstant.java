package org.usfirst.frc.team3501.robot.commands.elevator;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import org.usfirst.frc.team3501.robot.subsystems.Intake;
import org.usfirst.frc.team3501.robot.utils.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Set this to the default command for the elevator and then update targetElevatorPos
 * with the ChangeElevatorTarget
 * @author Amin
 *
 */
public class MoveToTargetConstant extends Command {


  private Elevator elevator = Robot.getElevator();
  private PIDController elevatorController;
  private Intake intake;
  private double target=Elevator.BOTTOM_POS;
  private double prevVal;
  Timer timer;
  double previousTarget=Elevator.BOTTOM_POS;

  public MoveToTargetConstant() {
    requires(elevator);
    this.target = elevator.getTargetElevatorPos();
    this.previousTarget = target;
    timer = new Timer();
    this.elevatorController = new PIDController(Elevator.ELEVATOR_P,
        Elevator.ELEVATOR_I, Elevator.ELEVATOR_D);
    this.elevatorController.setDoneRange(3.0);
    this.elevatorController.setMaxOutput(1.0);
    this.elevatorController.setMinDoneCycles(5);
    this.intake = Intake.getIntake();
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
    double motorVal = val;

    if (val - prevVal > Elevator.ACCELERATION_CONTROL)
      motorVal = prevVal + Elevator.ACCELERATION_CONTROL;
    this.elevator.setMotorValue(motorVal);
    prevVal = motorVal;
    //SmartDashboard.putNumber("elevator power", motorVal);
    if(elevator.getTargetElevatorPos()<=elevator.SWITCH_POS) {
      intake.setIntakeTarget(intake.BOTTOM_INTAKE_ANGLE);
    }else if(elevator.getHeight()>elevator.MOVE_INTAKE_POS) {
      intake.setIntakeTarget(intake.MIDDLE_INTAKE_ANGLE);
    }
    if(intake.raisIntake) intake.setIntakeTarget(intake.TOP_INTAKE_ANGLE);
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
