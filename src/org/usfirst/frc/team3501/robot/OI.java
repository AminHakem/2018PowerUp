package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.commands.DropRamp;
import org.usfirst.frc.team3501.robot.commands.LiftRobot;
import org.usfirst.frc.team3501.robot.commands.LowerRobot;
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
  public static Button Ramp;
  public static Button climbingWinch;
  public static Button loweringWinch;

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

    Ramp = new JoystickButton(xboxController, Constants.OI.LOWER_RAMP);
    Ramp.whileHeld(new DropRamp());

    climbingWinch = new JoystickButton(xboxController,
        Constants.OI.CLIMBER_WINCH);
    climbingWinch.whileHeld(new LiftRobot());

    loweringWinch = new JoystickButton(xboxController,
        Constants.OI.CLIMBER_WINCH);
    loweringWinch.whileHeld(new LowerRobot());

  }

  public static OI getOI() {
    if (oi == null)
      oi = new OI();
    return oi;
  }
}
