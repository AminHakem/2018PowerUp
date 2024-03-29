package org.usfirst.frc.team3501.robot.commands.intake;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import org.usfirst.frc.team3501.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author: niyatisriram
 *
 *          this command runs the intake forward, for the purposes of expelling a cube wheels roll
 *          out, away from robot center
 *
 */
public class RunOuttake extends Command {

  private Intake intake = Robot.getIntake();

  public RunOuttake() {
    requires(intake);
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    intake.setMotorValues(intake.outtakeSpeed);
  }

  @Override
  protected boolean isFinished() {
    return timeSinceInitialized() >= 0.5;
  }

  @Override
  protected void end() {
    Robot.getIntake().stop();
   // Robot.getElevator().setTargetElavatorPos(Elevator.BOTTOM_POS);
    if(intake.isPistonActivated())intake.setMotorValues(-intake.intakeSpeed);
    intake.setPistonActivated(!intake.isPistonActivated());
    intake.getIntakeSolenoid().set(intake.isPistonActivated());
    intake.getIntakeSolenoidTwo().set(intake.isPistonActivated());
    }

  @Override
  protected void interrupted() {
    end();
  }
}
