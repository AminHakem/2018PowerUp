package org.usfirst.frc.team3501.robot.commands.shooter;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Shooter;
import org.usfirst.frc.team3501.robot.utils.PIDController;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command runs the fly wheel continuously at a set speed using a PID
 * Controller when OI button managing fly wheel is pressed. The command will run
 * the fly wheel motor until the button triggering it is released.
 *
 * Should only be run from the operator interface.
 *
 * pre-condition: This command must be run by a button in OI, with
 * button.whileHeld(...).
 *
 * @author Shaina & Chris
 */
public class RunFlyWheel extends Command {
  private Shooter shooter = Robot.getShooter();
  double time;

  private PIDController wheelController;

  public RunFlyWheel(double time) {
    this.time = time;
  }

  @Override
  protected void initialize() {
    shooter.initializePIDController();
  }

  @Override
  protected void execute() {
    shooter.setFlyWheelMotorVal(shooter.calculateShooterSpeed());
  }

  @Override
  protected boolean isFinished() {
    return timeSinceInitialized() >= time;
  }

  @Override
  protected void end() {
    this.shooter.stopFlyWheel();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
