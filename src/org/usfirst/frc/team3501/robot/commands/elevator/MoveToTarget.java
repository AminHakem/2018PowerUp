package org.usfirst.frc.team3501.robot.commands.elevator;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import org.usfirst.frc.team3501.robot.utils.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/***
 * This command moves the elevator to a specified target.
 *
 * @author Samhita
 *
 * @param target: the height the elevator will move to in inches
 * @param maxTimeOut: the maximum time this command will be allowed to run before being cut
 */

public class MoveToTarget extends Command {

  private Elevator elevator = Robot.getElevator();
  private PIDController elevatorController;

  private double maxTimeOut;
  private double target;
  Timer timer;

  /**
   * @param target the height the elevator will move to in inches
   * @param maxTimeOut the maximum time this command will be allowed to run before being cut
   */
  public MoveToTarget(double target, double maxTimeOut) {
    requires(elevator);
    this.maxTimeOut = maxTimeOut;
    this.target = target;
    timer = new Timer();

    this.elevatorController =
        new PIDController(Elevator.ELEVATOR_P, Elevator.ELEVATOR_I, Elevator.ELEVATOR_D);
    this.elevatorController.setDoneRange(2.0);
    this.elevatorController.setMaxOutput(1.0);
    this.elevatorController.setMinDoneCycles(5);
  }

  @Override
  protected void initialize() {
    this.elevatorController.setSetPoint(this.target);
    timer.start();
    this.elevator.setCANTalonsCoast();
  }

  @Override
  protected void execute() {
    double current = elevator.getHeight();
    double val = elevatorController.calcPID(current);
    this.elevator.setMotorValue(val);
  }

  @Override
  protected boolean isFinished() {
    return this.elevatorController.isDone() || timer.get() >= maxTimeOut || this.elevator.isAtTop();
  }

  @Override
  protected void end() {
    this.elevator.stop();
    this.elevator.setCANTalonsBrake();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
