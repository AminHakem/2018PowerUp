package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Intakes the balls into ball container.
 * 
 * @author Meeta
 */
public class Intake extends Subsystem {
  public static Intake intake = null;
  private CANTalon intakeWheel;
  public static final double INTAKE_SPEED = 0;

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
  public void setSpeed(double speed) {
    intakeWheel.set(speed);
  }

}
