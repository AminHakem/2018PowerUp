package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author: niyatisriram
 *          Ramp that lifts other robots above the line
 *
 */
public class Ramp extends Subsystem {

  private static Ramp ramp;

  private final DoubleSolenoid dropPiston;

  private Ramp() {

    dropPiston = new DoubleSolenoid(Constants.Ramp.PISTON_MODULE,
        Constants.Ramp.RAMP_RELEASE_FORWARD,
        Constants.Ramp.RAMP_RELEASE_REVERSE);
  }

  public static Ramp getRamp() {
    if (ramp == null) {
      ramp = new Ramp();
    }
    return ramp;
  }

  public DoubleSolenoid getDropPiston() {
    return dropPiston;
  }

  @Override
  public void initDefaultCommand() {
  }

}
