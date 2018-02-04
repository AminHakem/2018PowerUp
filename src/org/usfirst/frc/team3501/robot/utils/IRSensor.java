package org.usfirst.frc.team3501.robot.utils;

import edu.wpi.first.wpilibj.AnalogInput;

public class IRSensor extends AnalogInput {

  AnalogInput analogInput;

  public IRSensor(int channel) {
    super(channel);
    analogInput = new AnalogInput(channel);
  }

  public double getIRSensorVoltage() {
    return analogInput.getVoltage();
  }

  public double getIRDistance() {
    final double k = 1 / 32;
    double voltage = analogInput.getVoltage();
    double distance = voltage * k;
    return distance;
  }

  @Override
  public double getVoltage() {
    return super.getVoltage();
  }

  public double getADCValue() {
    return super.getValue();
  }

}
