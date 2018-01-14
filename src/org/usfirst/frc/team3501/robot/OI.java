package org.usfirst.frc.team3501.robot;

<<<<<<<15183 a1d85be5c495745cd7270da594177903d01

=======

import org.usfirst.frc.team3501.robot.commands.DropLeftRamp;
import org.usfirst.frc.team3501.robot.commands.DropRightRamp;
import org.usfirst.frc.team3501.robot.commands.FillLeftAirBalloon;
import org.usfirst.frc.team3501.robot.commands.FillRightAirBalloon;>>>>>>>add commands to control left and right ramps and air balloons with pistons
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

  // Climber
  public static Button rightRamp;
  public static Button leftRamp;
  public static Button rightAirBalloon;
  public static Button leftAirBalloon;

  public OI() {
    ps4_controller = new Joystick(Constants.OI.PS4_CONTROLLER_PORT);
    gamePad = new Joystick(Constants.OI.GAME_PAD_PORT);

    // Intake
    runIntakeForward = new JoystickButton(gamePad,
        Constants.OI.RUN_INTAKE_PORT);

    runIntakeForward.whileHeld(new RunOuttake());

    runIntakeBackward = new JoystickButton(gamePad,
        Constants.OI.REVERSE_INTAKE_PORT);
    runIntakeBackward.whileHeld(new RunIntake());

    // Climber
    rightRamp = new JoystickButton(ps4_controller,
        Constants.OI.LOWER_RIGHT_RAMP);
    rightRamp.whileHeld(new DropRightRamp());

    leftRamp = new JoystickButton(ps4_controller, Constants.OI.LOWER_LEFT_RAMP);
    leftRamp.whileHeld(new DropLeftRamp());

  }

  public static OI getOI() {
    if (oi == null)
      oi = new OI();
    return oi;
  }
}
