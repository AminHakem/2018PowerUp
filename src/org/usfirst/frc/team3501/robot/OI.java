package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.commands.climber.ExtendHook;
import org.usfirst.frc.team3501.robot.commands.climber.LiftRobot;
import org.usfirst.frc.team3501.robot.commands.climber.LowerRobot;
import org.usfirst.frc.team3501.robot.commands.driving.AlignWithCube;
import org.usfirst.frc.team3501.robot.commands.driving.ToggleFieldOriented;
import org.usfirst.frc.team3501.robot.commands.elevator.ChangeElevatorTarget;
import org.usfirst.frc.team3501.robot.commands.intake.RunIntake;
import org.usfirst.frc.team3501.robot.commands.intake.RunOuttake;
import org.usfirst.frc.team3501.robot.commands.intake.Shoot;
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
  public Button shoot;
  // Climber
  public Button dropRamp;
  public Button climbingWinch;
  public Button loweringWinch;
  public Button toggleJoystickClimb;
  public Button extendHook;

  public Button climbLeft;
  public Button climbRight;
  public Button climbBoth;
  public Button backDriveBoth;

  // Elevator
  public static Button moveElevatorToSwitch;
  public static Button moveElevatorToScale;
  public static Button moveElevatorToVault;
  public static Button moveElevatorToBottom;

  // Elevator
  public static Button toggleHookPiston;

  public OI() {
    ps4_controller = new Joystick(Constants.OI.PS4_CONTROLLER_PORT);
    gamePad = new Joystick(Constants.OI.GAME_PAD_PORT);

    // Intake
    runIntakeForward =
        new JoystickButton(ps4_controller, Constants.OI.RUN_INTAKE_PORT);
    runIntakeForward.whileHeld(new RunIntake());

    runIntakeBackward =
        new JoystickButton(ps4_controller, Constants.OI.REVERSE_INTAKE_PORT);
    runIntakeBackward.whenPressed(new RunOuttake());

    shoot = new JoystickButton(ps4_controller, Constants.OI.SHOOT_PORT);
    shoot.whileHeld(new Shoot());

    // Drive Train
    changeFieldOriented =
        new JoystickButton(ps4_controller, Constants.OI.FIELD_ORIENTED_PORT);
    changeFieldOriented.toggleWhenPressed(new ToggleFieldOriented());

    alignWithCube =
        new JoystickButton(ps4_controller, Constants.OI.VISION_ALIGN_PORT);
    alignWithCube.toggleWhenPressed(new AlignWithCube());

    // toggleHookPiston = new JoystickButton(ps4_controller, Constants.OI.TOGGLE_PISTON_PORT);
    // toggleHookPiston.toggleWhenPressed(new ToggleHookPiston());

    // Elevator
    moveElevatorToBottom =
        new JoystickButton(ps4_controller, Constants.OI.ELEVATOR_TO_BOTTOM);
    moveElevatorToBottom
        .whenPressed(new ChangeElevatorTarget(Elevator.BOTTOM_POS));

    moveElevatorToVault =
        new JoystickButton(ps4_controller, Constants.OI.ELEVATOR_TO_VAULT);
    moveElevatorToVault
        .whenPressed(new ChangeElevatorTarget(Elevator.VAULT_POS));

    moveElevatorToSwitch =
        new JoystickButton(ps4_controller, Constants.OI.ELEVATOR_TO_SWITCH);
    moveElevatorToSwitch
        .whenPressed(new ChangeElevatorTarget(Elevator.SWITCH_POS));

    moveElevatorToScale =
        new JoystickButton(ps4_controller, Constants.OI.ELEVATOR_TO_SCALE);
    moveElevatorToScale
        .whenPressed(new ChangeElevatorTarget(Elevator.SCALE_POS));

    // Climber
    // dropRamp = new JoystickButton(ps4_controller, Constants.OI.LOWER_RAMP);
    // dropRamp.whenPressed(new DropRamp());

    extendHook =
        new JoystickButton(ps4_controller, Constants.OI.TOGGLE_PISTON_PORT);
    extendHook.whenPressed(new ExtendHook());

    climbBoth =
        new JoystickButton(ps4_controller, Constants.OI.RUN_WINCH_FORWARD);
    climbBoth.whileHeld(new LiftRobot());

    backDriveBoth =
        new JoystickButton(ps4_controller, Constants.OI.RUN_WINCH_BACKWARD);
    backDriveBoth.whileHeld(new LowerRobot());
  }

  public static OI getOI() {
    if (oi == null)
      oi = new OI();
    return oi;
  }
}
