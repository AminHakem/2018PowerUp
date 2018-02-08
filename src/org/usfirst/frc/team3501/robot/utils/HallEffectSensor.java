package org.usfirst.frc.team3501.robot.utils;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

public class HallEffectSensor extends DigitalInput {
  private Counter counter;

  public HallEffectSensor(int port) {
    super(port);
  }

}
