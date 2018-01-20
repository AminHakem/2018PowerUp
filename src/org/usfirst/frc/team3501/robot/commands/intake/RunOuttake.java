package org.usfirst.frc.team3501.robot.commands.intake;

import org.usfirst.frc.team3501.robot.Robot;
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
    Intake.setMotorValues(intake.intakeSpeed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.getIntake().stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
