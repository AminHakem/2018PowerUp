package org.usfirst.frc.team3501.robot.commands.intake;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import org.usfirst.frc.team3501.robot.subsystems.Intake;
import org.usfirst.frc.team3501.robot.utils.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Amin
 *
 */
public class MoveIntakeToTargetConstant extends Command {


  private Intake intake = Intake.getIntake();
  private PIDController angleController;

  private double target=Elevator.BOTTOM_POS;
  private double prevVal;
  Timer timer;
  double previousTarget=Elevator.BOTTOM_POS;

  public MoveIntakeToTargetConstant() {
    requires(intake);
    this.target = intake.getIntakeTarget();
    this.previousTarget = target;
    timer = new Timer();
    this.angleController = new PIDController(Intake.INTAKE_P,Intake.INTAKE_I,Intake.INTAKE_D);
    this.angleController.setDoneRange(3.0);
    this.angleController.setMaxOutput(1.0);
    this.angleController.setMinDoneCycles(5);
  }

  @Override
  protected void initialize() {
    this.angleController.setSetPoint(this.target);
    this.previousTarget = intake.getIntakeTarget();
    timer.start();
  }

  @Override
  protected void execute() {
    angleController.setSetPoint(intake.getIntakeTarget());
    double current = intake.getEncoderPulses();
    double val = angleController.calcPID(current);
    double motorVal = val;
    this.intake.setAngleMotorValue(motorVal);
    prevVal = motorVal;

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    intake.setAngleMotorValue(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
