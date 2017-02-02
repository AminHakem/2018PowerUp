package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.commands.driving.ToggleGear;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
  private static OI oi;
  public static Joystick leftJoystick;
  public static Joystick rightJoystick;
  public static Button toggleWinch;

  public static Button toggleIndexWheel;
  public static Button toggleFlyWheel;

  public static Button toggleGear;

  public OI() {
    leftJoystick = new Joystick(Constants.OI.LEFT_STICK_PORT);
    rightJoystick = new Joystick(Constants.OI.RIGHT_STICK_PORT);
    toggleWinch = new JoystickButton(leftJoystick,
        Constants.OI.TOGGLE_WINCH_PORT);
    toggleIndexWheel = new JoystickButton(leftJoystick,
        Constants.OI.TOGGLE_INDEXWHEEL_PORT);
    toggleFlyWheel = new JoystickButton(leftJoystick,
        Constants.OI.TOGGLE_FLYWHEEL_PORT);
    toggleGear = new JoystickButton(leftJoystick,
        Constants.OI.TOGGLE_GEAR_PORT);
    toggleGear.whenPressed(new ToggleGear());
  }

  public static OI getOI() {
    if (oi == null)
      oi = new OI();
    return oi;
  }
}
