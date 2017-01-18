package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
  private static Climber climber;
  private final CANTalon motor;

  private Climber() {
    motor = new CANTalon(Constants.Climber.MOTOR);
  }

  public static Climber getClimber() {
    if (climber == null) {
      climber = new Climber();
    }
    return climber;
  }

  public void stop() {
    setMotorValue(0);
  }

  public void setMotorValue(final double val) {
    motor.set(MOTOR);

  }
}
