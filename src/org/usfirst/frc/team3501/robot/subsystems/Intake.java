package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Meeta
 */
public class Intake extends Subsystem {
  private static Intake intake = null;
  private CANTalon intakeWheel;
  public static final double INTAKE_SPEED = 1;
  public static final double REVERSE_SPEED = -1;

  public Intake() {
    intakeWheel = new CANTalon(Constants.Intake.INTAKE_ROLLER_PORT);
  }

  /***
   * It gets the intake instance, and if intake has not been initialized, then
   * it will be initialized.
   *
   * @returns intake
   */
  public static Intake getIntake() {
    if (intake == null) {
      intake = new Intake();
    }
    return intake;
  }

  @Override
  protected void initDefaultCommand() {

  }

  /***
   * Sets speed of intake wheel to input speed
   *
   * @param speed
   *          from -1 to 1
   */
  private void setSpeed(double speed) {
    intakeWheel.set(speed);
  }

  /***
   * Runs the intake wheel at the set intake speed.
   */
  public void runIntake() {
    setSpeed(INTAKE_SPEED);
  }

  /***
   * Stops the intake wheel by setting intake wheel's speed to 0.
   */
  public void stopIntake() {
    setSpeed(0);
  }

  /***
   * Purpose is to release all balls from the ball container to the outside of
   * the robot. Reverses intake wheel by setting wheel speed to reverse speed.
   *
   */
  public void runReverseIntake() {
    setSpeed(REVERSE_SPEED);
  }

}
