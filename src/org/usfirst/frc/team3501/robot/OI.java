package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.commands.climber.LiftRobot;
import org.usfirst.frc.team3501.robot.commands.climber.LowerRobot;
import org.usfirst.frc.team3501.robot.commands.driving.AlignWithCube;
import org.usfirst.frc.team3501.robot.commands.driving.ToggleFieldOriented;
import org.usfirst.frc.team3501.robot.commands.elevator.MoveToTarget;
import org.usfirst.frc.team3501.robot.commands.intake.RunIntake;
import org.usfirst.frc.team3501.robot.commands.intake.RunOuttake;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
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
  public static Button moveElevatorToSwitch;
  public static Button moveElevatorToScaleLow;
  public static Button moveElevatorToScaleNeutral;
  public static Button moveElevatorToBottom;

  // Elevator
  public static Button toggleHookPiston;

  public OI() {
    ps4_controller = new Joystick(Constants.OI.PS4_CONTROLLER_PORT);
    gamePad = new Joystick(Constants.OI.GAME_PAD_PORT);

    // Intake
    runIntakeForward = new JoystickButton(ps4_controller, Constants.OI.RUN_INTAKE_PORT);
    runIntakeForward.whileHeld(new RunIntake());

    runIntakeBackward = new JoystickButton(ps4_controller, Constants.OI.REVERSE_INTAKE_PORT);
    runIntakeBackward.whileHeld(new RunOuttake());

    // Climber
    // dropRamp = new JoystickButton(ps4_controller, Constants.OI.LOWER_RAMP);
    // dropRamp.whenPressed(new DropRamp());

    climbingWinch = new JoystickButton(ps4_controller, Constants.OI.RUN_WINCH_FORWARD);
    climbingWinch.whileHeld(new LiftRobot());

    loweringWinch = new JoystickButton(ps4_controller, Constants.OI.RUN_WINCH_BACKWARD);
    loweringWinch.whileHeld(new LowerRobot());

    // Drive Train
    changeFieldOriented = new JoystickButton(ps4_controller, Constants.OI.TRIANGLE_BUTTON_PORT);
    changeFieldOriented.toggleWhenPressed(new ToggleFieldOriented());

    alignWithCube = new JoystickButton(ps4_controller, Constants.OI.VISION_ALIGN_PORT);
    alignWithCube.toggleWhenPressed(new AlignWithCube());

    // toggleHookPiston = new JoystickButton(ps4_controller, Constants.OI.TOGGLE_PISTON_PORT);
    // toggleHookPiston.toggleWhenPressed(new ToggleHookPiston());

    // Elevator
    moveElevatorToSwitch = new JoystickButton(ps4_controller, Constants.OI.ELEVATOR_TO_SWITCH);
    moveElevatorToSwitch.whenPressed(new MoveToTarget(/* Elevator.SWITCH_POS */10, 10));

    moveElevatorToScaleLow = new JoystickButton(ps4_controller, Constants.OI.ELEVATOR_TO_SCALE_LOW);
    moveElevatorToScaleLow.whenPressed(new MoveToTarget(/* Elevator.SCALE_BOTTOM_POS */20, 10));

    moveElevatorToScaleNeutral =
        new JoystickButton(ps4_controller, Constants.OI.ELEVATOR_TO_SCALE_NEUTRAL);
    moveElevatorToScaleNeutral.whenPressed(new MoveToTarget(Elevator.SCALE_START_POS, 10));

    moveElevatorToBottom = new JoystickButton(ps4_controller, Constants.OI.ELEVATOR_TO_BOTTOM);
    moveElevatorToScaleNeutral.whenPressed(new MoveToTarget(Elevator.BOTTOM_POS, 10));
  }

  public static OI getOI() {
    if (oi == null)
      oi = new OI();
    return oi;
  }
}
