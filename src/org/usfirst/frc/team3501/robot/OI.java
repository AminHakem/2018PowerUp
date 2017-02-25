package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.commands.driving.BrakeCANTalons;
import org.usfirst.frc.team3501.robot.commands.driving.CoastCANTalons;
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

  public static Button runIndexWheel;
  public static Button reverseIndexWheel;
  public static Button toggleFlyWheel;

  public static Button toggleGear;

  public static Button runIntake;
  public static Button reverseIntake;

  public static Button increaseShooterSpeed;
  public static Button decreaseShooterSpeed;

  public static Button brakeCANTalons;
  public static Button coastCANTalons;

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

    increaseShooterSpeed = new JoystickButton(leftJoystick,
        Constants.OI.INCREASE_SHOOTER_SPEED_PORT);
    increaseShooterSpeed.whenPressed(new IncreaseShootingSpeed());

    decreaseShooterSpeed = new JoystickButton(leftJoystick,
        Constants.OI.DECREASE_SHOOTER_SPEED_PORT);
    decreaseShooterSpeed.whenPressed(new DecreaseShootingSpeed());

    brakeCANTalons = new JoystickButton(rightJoystick,
        Constants.OI.BRAKE_CANTALONS_PORT);
    brakeCANTalons.whenPressed(new BrakeCANTalons());

    coastCANTalons = new JoystickButton(rightJoystick,
        Constants.OI.COAST_CANTALONS_PORT);
    coastCANTalons.whenPressed(new CoastCANTalons());
  }

  public static OI getOI() {
    if (oi == null)
      oi = new OI();
    return oi;
  }
}
