package org.usfirst.frc.team3501.robot.commands.intake;

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
public class RunIntakeOnTime extends Command {

  private Intake intake = Robot.getIntake();
  double time;

  public RunIntakeOnTime(double time) {
    requires(intake);
    this.time = time;
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    intake.setMotorValues(-intake.intakeSpeed);
  }

  @Override
  protected boolean isFinished() {
    return timeSinceInitialized() >= time;
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
