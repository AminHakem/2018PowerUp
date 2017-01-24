package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Meeta
 */
public class Intake extends Subsystem {
  public static Intake intake = null;
  private CANTalon intakeWheel;
  public static final double INTAKE_SPEED = 0;

  // create speed of intake whee
  public Intake() {
    intakeWheel = new CANTalon(Constants.Intake.INTAKE_ROLLER_PORT);
  }

  public static Intake getIntake() {
    if (intake == null) {
      intake = new Intake();
    }
    return intake;
  }

  @Override
  protected void initDefaultCommand() {

  }

  public void setSpeed(double speed) {
    intakeWheel.set(speed);
  }

}
