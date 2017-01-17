package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
  private static OI oi;
  public static Joystick leftJoystick;
  public static Joystick rightJoystick;
  public static Button toggleWinch;

  public OI() {
    leftJoystick = new Joystick(Constants.OI.LEFT_STICK_PORT);
    rightJoystick = new Joystick(Constants.OI.RIGHT_STICK_PORT);
     toggleWinch = new JoystickButton(leftJoystick,Constants.OI.TOGGLE_WINCH_PORT);

  }

  public static OI getOI() {
    if (oi == null)
      oi = new OI();
    return oi;
  }
}
