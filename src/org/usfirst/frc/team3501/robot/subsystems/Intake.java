package org.usfirst.frc.team3501.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Meeta
 */
public class Intake extends Subsystem {
  public static Intake intake = null;
  protected double speed = 0;
  private CANTalon intakeWheel;
  public static final double INTAKE_SPEED = 0;

  // create speed of intake whee
  public Intake() {

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
    this.speed = speed;
  }

}
