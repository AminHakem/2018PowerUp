package org.usfirst.frc.team3501.robot.commands.elevator;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import org.usfirst.frc.team3501.robot.utils.PIDController;
import org.usfirst.frc.team3501.robot.utils.TimerUtil;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/***
 * DEPRECATED: This code does not have the drifting fix. Use MoveToTargetConstant!!!!!!!!!!!
 * 
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
  private double prevVal;
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
  }

  @Override
  protected void initialize() {
    this.elevatorController =
        new PIDController(0.01, Elevator.ELEVATOR_I, Elevator.ELEVATOR_D);
    this.elevatorController.setDoneRange(1.0);
    this.elevatorController.setMaxOutput(0.75);
    this.elevatorController.setMinDoneCycles(5);
    this.elevatorController.setSetPoint(this.target);
    timer.start();
    this.prevVal = 0;
  }

  @Override
  protected void execute() {
    double current = elevator.getHeight();
    double val = elevatorController.calcPID(current);
    double motorVal = val;

    if (val - prevVal > Elevator.ACCELERATION_CONTROL)
      motorVal = prevVal + Elevator.ACCELERATION_CONTROL;

    this.elevator.setMotorValue(motorVal);
    SmartDashboard.putNumber("motor val", motorVal);

    prevVal = motorVal;
  }

  @Override
  protected boolean isFinished() {
    return this.elevatorController.isDone() || timer.get() >= maxTimeOut || this.elevator.isAtTop();
  }

  @Override
  protected void end() {
    this.elevator.stop();
    TimerUtil.printTime("MoveToTarget done: ");
  }

  @Override
  protected void interrupted() {
    end();
  }
}
