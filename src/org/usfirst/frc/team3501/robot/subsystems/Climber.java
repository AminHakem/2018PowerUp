package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
  public static Climber climber;

  public static final boolean BRAKE_MODE = true;
  public static final boolean COAST_MODE = false;

  public static final double CLIMBER_SPEED = 1.0;
  public boolean shouldBeClimbing = true;

  private CANTalon winch;

  public Climber() {
    winch = new CANTalon(Constants.Climber.WINCH_PORT);
  }

  public static Climber getClimber() {
    if (climber == null) {
      climber = new Climber();
    }
    return climber;
  }

  public void setMotorValues(double climbingSpeed) {
    winch.set(MathLib.limitValue(climbingSpeed, 0.0, 1.0));
  }

  public void stop() {
    winch.set(0);
  }

  public void setCANTalonsBrakeMode(boolean mode) {
    winch.enableBrakeMode(mode);
  }

  @Override
  protected void initDefaultCommand() {
  }
}
