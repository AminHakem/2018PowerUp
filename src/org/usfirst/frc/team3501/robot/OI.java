package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.commands.climber.ToggleWinch;
import org.usfirst.frc.team3501.robot.commands.driving.ToggleGear;
import org.usfirst.frc.team3501.robot.commands.intake.ReverseIntakeContinuous;
import org.usfirst.frc.team3501.robot.commands.intake.RunIntakeContinuous;
import org.usfirst.frc.team3501.robot.commands.shooter.DecreaseShootingSpeed;
import org.usfirst.frc.team3501.robot.commands.shooter.IncreaseShootingSpeed;
import org.usfirst.frc.team3501.robot.commands.shooter.ReverseIndexWheelContinuous;
import org.usfirst.frc.team3501.robot.commands.shooter.RunFlyWheelContinuous;
import org.usfirst.frc.team3501.robot.commands.shooter.RunIndexWheelContinuous;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
  private static OI oi;
  public static Joystick leftJoystick;
  public static Joystick rightJoystick;
  public static Button toggleWinch;

  public static Button runIndexWheel;
  public static Button reverseIndexWheel;
  public static Button toggleFlyWheel;

  public static Button toggleGear;

  public static Button runIntake;
  public static Button reverseIntake;

  public static Button increaseShooterSpeed;
  public static Button decreaseShooterSpeed;

  public OI() {
    leftJoystick = new Joystick(Constants.OI.LEFT_STICK_PORT);
    rightJoystick = new Joystick(Constants.OI.RIGHT_STICK_PORT);

    runIndexWheel = new JoystickButton(rightJoystick,
        Constants.OI.RUN_INDEXWHEEL_PORT);
    runIndexWheel.whileHeld(new RunIndexWheelContinuous());

    reverseIndexWheel = new JoystickButton(rightJoystick,
        Constants.OI.REVERSE_INDEXWHEEL_PORT);
    reverseIndexWheel.whileHeld(new ReverseIndexWheelContinuous());

    toggleFlyWheel = new JoystickButton(rightJoystick,
        Constants.OI.TOGGLE_FLYWHEEL_PORT);
    toggleFlyWheel.toggleWhenPressed(new RunFlyWheelContinuous());

    toggleGear = new JoystickButton(leftJoystick,
        Constants.OI.TOGGLE_GEAR_PORT);
    toggleGear.whenPressed(new ToggleGear());

    runIntake = new JoystickButton(leftJoystick, Constants.OI.RUN_INTAKE_PORT);
    runIntake.whileHeld(new RunIntakeContinuous());

    reverseIntake = new JoystickButton(leftJoystick,
        Constants.OI.REVERSE_INTAKE_PORT);
    reverseIntake.whileHeld(new ReverseIntakeContinuous());

    toggleWinch = new JoystickButton(leftJoystick,
        Constants.OI.TOGGLE_WINCH_PORT);
    toggleWinch.whenPressed(new ToggleWinch());

    increaseShooterSpeed = new JoystickButton(leftJoystick,
        Constants.OI.INCREASE_SHOOTER_SPEED_PORT);
    increaseShooterSpeed.whenPressed(new IncreaseShootingSpeed());

    decreaseShooterSpeed = new JoystickButton(leftJoystick,
        Constants.OI.DECREASE_SHOOTER_SPEED_PORT);
    decreaseShooterSpeed.whenPressed(new DecreaseShootingSpeed());
  }

  public static OI getOI() {
    if (oi == null)
      oi = new OI();
    return oi;
  }
}
