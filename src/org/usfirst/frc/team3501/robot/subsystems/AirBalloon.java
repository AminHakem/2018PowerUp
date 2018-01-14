package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author: niyatisriram
 *          Air balloon attached to ramp that inflates to lift alliance robots
 */
public class AirBalloon extends Subsystem {

  private static AirBalloon airBalloon;
  private static Solenoid fillPiston;

  private AirBalloon() {

    fillPiston = new Solenoid(Constants.AirBalloon.PISTON_MODULE,
        Constants.AirBalloon.FILL_AIR_BALLOON);
  }

  public static AirBalloon getAirBalloon() {
    if (airBalloon == null) {
      airBalloon = new AirBalloon();
    }
    return airBalloon;
  }

  public Solenoid getFillPiston() {
    return fillPiston;
  }

  public static void extendPiston() {
    boolean on = true;
    fillPiston.set(on);
  }

  public static void retractPiston() {
    boolean off = false;
    fillPiston.set(off);
  }

  @Override
  public void initDefaultCommand() {
  }
}
