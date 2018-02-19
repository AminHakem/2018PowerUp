package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.Constants.Direction;
import org.usfirst.frc.team3501.robot.commands.climber.LiftRobot;
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
  public Joystick ps4_controller;
  public Joystick gamePad;

  // Driving
  public Button changeFieldOriented;
  public Button alignWithCube;

  // Intake
  public Button runIntakeForward;
  public Button runIntakeBackward;

  // Climber
  public Button dropRamp;
  public Button climbingWinch;
  public Button loweringWinch;
  public Button toggleJoystickClimb;
  
  public Button climbLeft;
  public Button climbRight;
  public Button climbBoth;

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

    // // Intake
    runIntakeForward = new JoystickButton(ps4_controller, Constants.OI.RUN_INTAKE_PORT);
    runIntakeForward.whenPressed(new RunIntake());

    runIntakeBackward = new JoystickButton(ps4_controller, Constants.OI.REVERSE_INTAKE_PORT);
    runIntakeBackward.whenPressed(new RunOuttake());

    // Climber
    // dropRamp = new JoystickButton(ps4_controller, Constants.OI.LOWER_RAMP);
    // dropRamp.whenPressed(new DropRamp());

    // climbingWinch = new JoystickButton(ps4_controller, Constants.OI.RUN_WINCH_FORWARD);
    // climbingWinch.whileHeld(new LiftRobot());
    //
    // loweringWinch = new JoystickButton(ps4_controller, Constants.OI.RUN_WINCH_BACKWARD);
    // loweringWinch.whileHeld(new LowerRobot());

    // Drive Train
    changeFieldOriented = new JoystickButton(ps4_controller, Constants.OI.FIELD_ORIENTED_PORT);
    changeFieldOriented.toggleWhenPressed(new ToggleFieldOriented());

    alignWithCube = new JoystickButton(ps4_controller, Constants.OI.VISION_ALIGN_PORT);
    alignWithCube.toggleWhenPressed(new AlignWithCube());

    // toggleHookPiston = new JoystickButton(ps4_controller, Constants.OI.TOGGLE_PISTON_PORT);
    // toggleHookPiston.toggleWhenPressed(new ToggleHookPiston());

    // Elevator
    moveElevatorToSwitch = new JoystickButton(ps4_controller, Constants.OI.ELEVATOR_TO_SWITCH);
    moveElevatorToSwitch.whenPressed(new MoveToTarget(Elevator.SWITCH_POS, 20));
    // moveElevatorToScaleLow = new JoystickButton(ps4_controller,
    // Constants.OI.ELEVATOR_TO_SCALE_LOW);
    // moveElevatorToScaleLow.whenPressed(new MoveToTarget(Elevator.SCALE_BOTTOM_POS, 20));
    //
    // moveElevatorToScaleNeutral =
    // new JoystickButton(ps4_controller, Constants.OI.ELEVATOR_TO_SCALE_NEUTRAL);
    // moveElevatorToScaleNeutral.whenPressed(new MoveToTarget(Elevator.SCALE_START_POS, 20));

    moveElevatorToBottom = new JoystickButton(ps4_controller, Constants.OI.ELEVATOR_TO_BOTTOM);
    moveElevatorToBottom.whenPressed(new MoveToTarget(0, 20));
    
    climbLeft = new JoystickButton(ps4_controller, 7);
    climbLeft.whileHeld(new LiftRobot(Direction.LEFT));
    
    climbRight = new JoystickButton(ps4_controller, 8);
    climbRight.whileHeld(new LiftRobot(Direction.RIGHT));

    climbBoth = new JoystickButton(ps4_controller, 6);
    climbBoth.whileHeld(new LiftRobot(Direction.UP));
  }

  public static OI getOI() {
    if (oi == null)
      oi = new OI();
    return oi;
  }
}
