package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

  private static Elevator elevator;

  // PID VALUES
  public static double ELEVATOR_P = 0.01, ELEVATOR_I = 0.00115, ELEVATOR_D = -0.002;

  // IR SENSOR CONSTANTS
  public static final int DISTANCE_THRESHOLD = 10;
  // There will be two flags, each telling us the top and bottom bounds of the elevator.
  // Due to the lag of the elevator, we will need to count the flags so that we stop at the correct
  // flag
  boolean irOne, irTwo;


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

  private Elevator() {
    elevatorTalon = new WPI_TalonSRX(Constants.Elevator.ELEVATOR);
    elevatorSensor = elevatorTalon.getSensorCollection();
    topLimitSwitch = new DigitalInput(Constants.Elevator.TOP_LIMIT_SWITCH);
    bottomLimitSwitch = new DigitalInput(Constants.Elevator.BOTTOM_LIMIT_SWITCH);
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
  public int getHeight() {
    return elevatorSensor.getQuadraturePosition();
  }

  public double getSpeed() {
    return elevatorSensor.getQuadratureVelocity();
  }

  public void resetEncoders() {
    elevatorSensor.setQuadraturePosition(0, 3);
  }

<<<<<<< fd235fa3ce4cae826007f2f9262941937bb8059c
  /*
   * public boolean inBounds() {
   *
   * if (elevatorSensor.getIRDistance() <= DISTANCE_THRESHOLD || elevatorSensor.getIRDistance() <=
   * DISTANCE_THRESHOLD) { if (atFlag == false) { flagCount++; } atFlag = true; } else { atFlag =
   * false; }
   *
   * if (flagCount == 2) { flagCount = 0; return false; } else { return true; } }
   */
=======
  // IR Sensor METHODS
  public double getTopIRSensorValue() {
    return irSensor1.getADCValue();
  }

  public double getBottomIRSensorValue() {
    return irSensor2.getADCValue();
  }

  public boolean atIRFlag() {
    if (irSensor1.getIRDistance() <= DISTANCE_THRESHOLD && irOne == false) {
      irOne = true;
      if (irTwo == true) {
        irOne = false;
        irTwo = false;
        return true;
      }
    }
    if (irSensor2.getIRDistance() <= DISTANCE_THRESHOLD && irTwo == false) {
      irTwo = true;
      if (irOne == true) {
        irOne = false;
        irTwo = false;
        return true;
      }
    }
    return false;
  }
>>>>>>> Changed ElevatorDistance to MoveToTarget and inBounds to atIRFlag.

  public boolean isAtTop() {
    return !topLimitSwitch.get();
  }

  public boolean isAtBottom() {
    return !bottomLimitSwitch.get();
  }

  public void periodicWarning() {}

  @Override
  protected void initDefaultCommand() {}

}
