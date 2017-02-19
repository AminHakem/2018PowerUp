package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command runs index wheel continuously when OI button managing index
 * wheel is pressed. The command will run the index wheel motor until the button
 * triggering it is released.
 *
 * Should only be run from the operator interface.
 *
 * pre-condition: This command must be run by a button in OI with
 * button.whileHeld(...).
 *
 * @author Shaina
 */
public class RunIndexWheelContinuous extends Command {
  private Shooter shooter = Robot.getShooter();
  private Timer t = new Timer();

  /**
   * See JavaDoc comment in class for details
   */
  public RunIndexWheelContinuous() {
    requires(shooter);
  }

  @Override
  protected void initialize() {
    t.reset();
  }

  @Override
  protected void execute() {
    if (t.get() % 1 == 0) {
      if (Shooter.getShooter().getPistonValue() == Constants.Shooter.LOW_GEAR) {
        Shooter.getShooter().setHighGear();
      } else {
        Shooter.getShooter().setLowGear();
      }
    }

    double shooterSpeed = shooter.getShooterRPM();
    double targetShooterSpeed = shooter.getTargetShootingSpeed();
    double threshold = shooter.getRPMThreshold();
    if (Math.abs(shooterSpeed - targetShooterSpeed) <= threshold)
      shooter.runIndexWheel();
  }

  @Override
  protected void end() {
    shooter.stopIndexWheel();
  }

  @Override
  protected void interrupted() {
    end();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

}
