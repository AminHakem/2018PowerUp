package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author: niyatisriram
 *          Ramp that lifts other robots above the line
 *
 */
public class Ramp extends Subsystem {

  private static Ramp ramp;

  private static Solenoid dropPiston;

  private Ramp() {

    dropPiston = new Solenoid(Constants.Ramp.PISTON_MODULE,
        Constants.Ramp.RAMP_RELEASE);
  }

  public static Ramp getRamp() {
    if (ramp == null) {
      ramp = new Ramp();
    }
    return ramp;
  }

  public Solenoid getDropPiston() {
    return dropPiston;
  }

  public static void extendPiston() {
    boolean on = true;
    dropPiston.set(on);
  }

  public static void retractPiston() {
    boolean off = false;
    dropPiston.set(off);
  }

  @Override
  public void initDefaultCommand() {
  }

}
