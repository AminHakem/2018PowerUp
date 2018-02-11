package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.commands.climber.LiftRobot;
import org.usfirst.frc.team3501.robot.commands.climber.LowerRobot;
import org.usfirst.frc.team3501.robot.commands.driving.AlignWithCube;
import org.usfirst.frc.team3501.robot.commands.driving.ToggleFieldOriented;
import org.usfirst.frc.team3501.robot.commands.elevator.ToggleHookPiston;
import org.usfirst.frc.team3501.robot.commands.intake.RunIntake;
import org.usfirst.frc.team3501.robot.commands.intake.RunOuttake;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
  private static OI oi;
  public static Joystick ps4_controller;
  public static Joystick gamePad;

  // Driving
  public static Button changeFieldOriented;
  public static Button alignWithCube;

  // Intake
  public static Button runIntakeForward;
  public static Button runIntakeBackward;

  // Climber
  public static Button dropRamp;
  public static Button climbingWinch;
  public static Button loweringWinch;
  public static Button toggleJoystickClimb;

  // Elevator
  public static Button toggleHookPiston;

  public OI() {
    ps4_controller = new Joystick(Constants.OI.PS4_CONTROLLER_PORT);
    gamePad = new Joystick(Constants.OI.GAME_PAD_PORT);

    // Intake
    runIntakeForward = new JoystickButton(gamePad, Constants.OI.RUN_INTAKE_PORT);
    runIntakeForward.whileHeld(new RunOuttake());

    runIntakeBackward = new JoystickButton(gamePad, Constants.OI.REVERSE_INTAKE_PORT);
    runIntakeBackward.whileHeld(new RunIntake());

    climbingWinch = new JoystickButton(ps4_controller, Constants.OI.RUN_WINCH_FORWARD);
    climbingWinch.whileHeld(new LiftRobot());

    loweringWinch = new JoystickButton(ps4_controller, Constants.OI.RUN_WINCH_BACKWARD);
    loweringWinch.whileHeld(new LowerRobot());

    // Drive Train
    changeFieldOriented = new JoystickButton(ps4_controller, Constants.OI.TRIANGLE_BUTTON_PORT);
    changeFieldOriented.toggleWhenPressed(new ToggleFieldOriented());

    alignWithCube = new JoystickButton(ps4_controller, Constants.OI.X_BUTTON_PORT);
    alignWithCube.toggleWhenPressed(new AlignWithCube());

    toggleHookPiston = new JoystickButton(ps4_controller, Constants.OI.TOGGLE_PISTON_PORT);
    toggleHookPiston.toggleWhenPressed(new ToggleHookPiston());
  }

  public static OI getOI() {
    if (oi == null)
      oi = new OI();
    return oi;
  }
}
