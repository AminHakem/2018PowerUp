package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;
import org.usfirst.frc.team3501.robot.commands.elevator.MoveToTargetConstant;
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
  public static double ELEVATOR_P = 0.03, ELEVATOR_I = 0.00115,
      ELEVATOR_D = -0.002;
  public static final double ACCELERATION_CONTROL = 0.05;

  // POSITIONS (in inches)
  public static final int BOTTOM_POS = 0;
  public static final int VAULT_POS = 3;
  public static final int MOVE_INTAKE_POS = 40;
  public static final int SWITCH_POS = 30;
  public static final int SCALE_POS = 69; // assumes scale is at its starting position
  public static final int SCALE_HIGH_POS = 69;
  public static final int SCALE_LOW_POS = 57;
  private final WPI_TalonSRX elevatorTalon;
  private final WPI_TalonSRX elevatorEncoderTalon;
  private final SensorCollection elevatorEncoder;
  private final DigitalInput topLimitSwitch, bottomLimitSwitch;
  public boolean hookPistonActivated = false;
  private Solenoid hookPistonOne, hookPistonTwo;

  // Calibration constants for encoders
  public static final double MOTOR_CIRCUMFERENCE = 1.18 * Math.PI; // inches
  public static final double ENCODER_PULSES_PER_REVOLUTION = 1024.0;
  public static final double INCHES_PER_PULSE =
      MOTOR_CIRCUMFERENCE / ENCODER_PULSES_PER_REVOLUTION;
  public static final double ENC_HEIGHT_CONSTANT = 0.0481;

  private double targetElevatorPos = this.BOTTOM_POS;

  private Elevator() {
    elevatorTalon = new WPI_TalonSRX(Constants.Elevator.ELEVATOR_MOTOR);
    elevatorEncoderTalon =
        new WPI_TalonSRX(Constants.Elevator.ELEVATOR_ENCODER_TALON);

    elevatorEncoder = elevatorEncoderTalon.getSensorCollection();
    this.resetEncoders();

    topLimitSwitch = new DigitalInput(Constants.Elevator.TOP_LIMIT_SWITCH);
    bottomLimitSwitch =
        new DigitalInput(Constants.Elevator.BOTTOM_LIMIT_SWITCH);

    hookPistonOne = new Solenoid(Constants.Elevator.PISTON_CHANNEL_ONE);
    hookPistonOne.set(hookPistonActivated);
   // hookPistonTwo = new Solenoid(Constants.Elevator.PISTON_CHANNEL_TWO);

    this.setCANTalonsBrake();
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

  public void setCANTalonsCoast() {
    elevatorTalon.setNeutralMode(NeutralMode.Coast);
  }

  public void setCANTalonsBrake() {
    elevatorTalon.setNeutralMode(NeutralMode.Brake);
  }

  public void stop() {
    // setMotorValue(0.1);
    setMotorValue(0);
  }

  public double getMotorVal() {
    return (elevatorTalon.getMotorOutputPercent());
  }

  // ENCODER METHODS
  public double getHeight() {
    return (ENC_HEIGHT_CONSTANT * elevatorEncoder.getQuadraturePosition()
        * INCHES_PER_PULSE / 4.0) * (2.0 / 3.0);
  }

  public double getSpeed() {
    return elevatorEncoder.getQuadratureVelocity();
  }

  public void resetEncoders() {
    elevatorEncoder.setQuadraturePosition(0, 3);
  }

  public void setEncoders(int val) {
    elevatorEncoder.setQuadraturePosition(val, 3);
  }

  public boolean isAtTop() {
    return !topLimitSwitch.get();
  }

  public boolean isAtBottom() {
    return !bottomLimitSwitch.get();
  }

  public void togglePiston() {
    hookPistonOne.set(!hookPistonOne.get());
  }

  public void periodicWarning() {}

  @Override
  protected void initDefaultCommand() {
    this.setDefaultCommand(new MoveToTargetConstant());
  }

  public double getTargetElevatorPos() {
    return targetElevatorPos;
  }

  public void setTargetElavatorPos(double val) {
    this.targetElevatorPos = val;
  }
  public void setHookPistonOne(boolean val) {
    hookPistonOne.set(val);
  }
}
