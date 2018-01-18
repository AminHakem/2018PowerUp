package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.commands.RunIntake;
import org.usfirst.frc.team3501.robot.commands.RunOuttake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
  private static OI oi;
  public static Joystick ps4_controller;
  public static Joystick gamePad;

  // Intake
  public static Button runIntakeForward;
  public static Button runIntakeBackward;

  public OI() {
    gamePad = new Joystick(Constants.OI.PS4_CONTROLLER_PORT);

    runIntakeForward = new JoystickButton(gamePad,
        Constants.OI.RUN_INTAKE_PORT);
    runIntakeForward.whileHeld(new RunOuttake());

    runIntakeBackward = new JoystickButton(gamePad,
        Constants.OI.REVERSE_INTAKE_PORT);
    runIntakeBackward.whileHeld(new RunIntake());

  }

  public static OI getOI() {
    if (oi == null)
      oi = new OI();
    return oi;
  }
}
