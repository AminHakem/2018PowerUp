package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

  private static Elevator elevator;

  // PID VALUES
  public static double ELEVATOR_P = 0.001, ELEVATOR_I = 0.00115,
      ELEVATOR_D = -0.002;

  // IR SENSOR CONSTANTS
  public static final int DISTANCE_THRESHOLD = 10;


  // POSITIONS (in inches)
  public static final int START_POS = 6;
  public static final int BOTTOM_POS = 0;
  public static final int TOP_POS = 71; // assumes at max height for robot
  public static final int SWITCH_POS = 19;
  public static final int SCALE_START_POS = 60; // assumes scale is at its starting position
  public static final int SCALE_BOTTOM_POS = 48; // assumes scale is at bottom position
  // there is no scale_top_pos because exceeds robot max height
  private final WPI_TalonSRX elevatorTalon;
  private final SensorCollection elevatorSensor;
  private final DigitalInput topLimitSwitch, bottomLimitSwitch;

  private Solenoid hookPiston;

  // Calibration constants for encoders
  public static final double MOTOR_DIAMETER = 0.5; // inches
  public static final double ENCODER_PULSES_PER_REVOLUTION = 1024;
  public static final double INCHES_PER_PULSE =
      MOTOR_DIAMETER * Math.PI / ENCODER_PULSES_PER_REVOLUTION;
  public static final double ELEVATOR_HEIGHT_CONSTANT = 0.0481;

  private Elevator() {
    elevatorTalon = new WPI_TalonSRX(Constants.Elevator.ELEVATOR);
    elevatorSensor = elevatorTalon.getSensorCollection();
    topLimitSwitch = new DigitalInput(Constants.Elevator.TOP_LIMIT_SWITCH);
    bottomLimitSwitch =
        new DigitalInput(Constants.Elevator.BOTTOM_LIMIT_SWITCH);
    resetEncoders();

    hookPiston = new Solenoid(Constants.Elevator.PISTON_HOOK);
  }

  public static Elevator getElevator() {
    if (elevator == null) {
      elevator = new Elevator();
    }
    return elevator;
  }

  // MOTOR METHODS
  public void setMotorValue(double motorVal) {
    motorVal = MathLib.restrictToRange(motorVal, -1.0, 1.0);

    elevatorTalon.set(ControlMode.PercentOutput, motorVal);
  }

  public void setCANTalonsBrake() {
    elevatorTalon.setNeutralMode(NeutralMode.Brake);
  }

  public void stop() {
    setMotorValue(0);
  }

  public double getMotorVal() {
    return (elevatorTalon.getMotorOutputPercent());
  }

  // ENCODER METHODS
  public double getHeight() {
    return ELEVATOR_HEIGHT_CONSTANT * elevatorSensor.getQuadraturePosition()
        * INCHES_PER_PULSE / 4;
  }

  public double getSpeed() {
    return elevatorSensor.getQuadratureVelocity();
  }

  public void resetEncoders() {
    elevatorSensor.setQuadraturePosition(0, 3);
  }

  public boolean isAtTop() {
    return !topLimitSwitch.get();
  }

  public boolean isAtBottom() {
    return !bottomLimitSwitch.get();
  }

  public void togglePiston() {
    if (hookPiston.get())
      this.hookPiston.free();
    else
      this.hookPiston.startPulse();
  }

  public void periodicWarning() {}

  @Override
  protected void initDefaultCommand() {}

}
