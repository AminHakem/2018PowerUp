package org.usfirst.frc.team3501.robot.commands;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author: niyatisriram
 *
 *          this command runs the intake backward, for the purposes of intaking a cube wheels roll
 *          in, towards robot center
 *
 */
public class RunIntake extends Command {

  private Intake intake = Robot.getIntake();

  public RunIntake() {
    requires(intake);
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    Intake.setMotorValues(-intake.intakeSpeed);
  }

  @Override
  protected boolean isFinished() {
    return true;
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
