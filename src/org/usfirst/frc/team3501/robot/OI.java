package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
  private static OI oi;
  public static Joystick xboxController;
  public static Joystick gamePad;

  public OI() {
    xboxController = new Joystick(Constants.OI.XBOX_CONTROLLER_PORT);
    gamePad = new Joystick(Constants.OI.GAME_PAD_PORT);
  }

  public static OI getOI() {
    if (oi == null)
      oi = new OI();
    return oi;
  }
}
