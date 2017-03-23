package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.commands.climber.BrakeCANTalons;
import org.usfirst.frc.team3501.robot.commands.climber.CoastCANTalons;
import org.usfirst.frc.team3501.robot.commands.climber.ToggleWinch;
import org.usfirst.frc.team3501.robot.commands.driving.ShiftDriveHighGear;
import org.usfirst.frc.team3501.robot.commands.driving.ShiftDriveLowGear;
import org.usfirst.frc.team3501.robot.commands.driving.ToggleGearManipulatorPiston;
import org.usfirst.frc.team3501.robot.commands.intake.ReverseIntakeContinuous;
import org.usfirst.frc.team3501.robot.commands.intake.RunIntakeContinuous;
import org.usfirst.frc.team3501.robot.commands.shooter.DecreaseShootingSpeed;
import org.usfirst.frc.team3501.robot.commands.shooter.IncreaseShootingSpeed;
import org.usfirst.frc.team3501.robot.commands.shooter.ResetShootingSpeed;
import org.usfirst.frc.team3501.robot.commands.shooter.ReverseFlyWheelContinuous;
import org.usfirst.frc.team3501.robot.commands.shooter.ReverseIndexWheelContinuous;
import org.usfirst.frc.team3501.robot.commands.shooter.RunFlyWheelContinuous;
import org.usfirst.frc.team3501.robot.commands.shooter.RunIndexWheelContinuous;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
  private static OI oi;
  public static Joystick xboxController;
  // public static Joystick rightJoystick;
  public static Joystick gamePad;

  public static Button runIndexWheel;
  public static Button reverseIndexWheel;
  public static Button toggleFlyWheel;
  public static Button reverseFlyWheel;

  // public static Button toggleGear;
  public static Button shiftHigh;
  public static Button shiftLow;

  public static Button toggleGearManipulatorPiston;

  public static Button runIntake;
  public static Button reverseIntake;

  public static Button increaseShooterSpeed;
  public static Button decreaseShooterSpeed;
  public static Button resetShooterSpeed;

  public static Button brakeCANTalons;
  public static Button coastCANTalons;
  public static Button climb;

  public OI() {
    xboxController = new Joystick(Constants.OI.XBOX_CONTROLLER_PORT);
    // rightJoystick = new Joystick(Constants.OI.RIGHT_STICK_PORT);
    gamePad = new Joystick(Constants.OI.GAME_PAD_PORT);

    runIndexWheel = new JoystickButton(xboxController,
        Constants.OI.RUN_INDEXWHEEL_PORT);
    runIndexWheel.whileHeld(new RunIndexWheelContinuous());

    reverseIndexWheel = new JoystickButton(xboxController,
        Constants.OI.REVERSE_INDEXWHEEL_PORT);
    reverseIndexWheel.whileHeld(new ReverseIndexWheelContinuous());

    toggleFlyWheel = new JoystickButton(gamePad,
        Constants.OI.TOGGLE_FLYWHEEL_PORT);
    toggleFlyWheel.toggleWhenPressed(new RunFlyWheelContinuous());

    reverseFlyWheel = new JoystickButton(gamePad,
        Constants.OI.REVERSE_FLYWHEEL_PORT);
    reverseFlyWheel.whileHeld(new ReverseFlyWheelContinuous());

    // toggleGear = new JoystickButton(xboxController,
    // Constants.OI.TOGGLE_GEAR_PORT);
    // toggleGear.whenPressed(new ToggleDriveGear());

    shiftHigh = new JoystickButton(xboxController,
        Constants.OI.SHIFT_HIGH_PORT);
    shiftHigh.whenPressed(new ShiftDriveHighGear());

    shiftLow = new JoystickButton(xboxController, Constants.OI.SHIFT_LOW_PORT);
    shiftLow.whenPressed(new ShiftDriveLowGear());

    toggleGearManipulatorPiston = new JoystickButton(gamePad,
        Constants.OI.TOGGLE_GEAR_MANIPULATOR_PORT);
    toggleGearManipulatorPiston.whenPressed(new ToggleGearManipulatorPiston());

    runIntake = new JoystickButton(xboxController,
        Constants.OI.RUN_INTAKE_PORT);
    runIntake.whileHeld(new RunIntakeContinuous());

    reverseIntake = new JoystickButton(xboxController,
        Constants.OI.REVERSE_INTAKE_PORT);
    reverseIntake.whileHeld(new ReverseIntakeContinuous());

    increaseShooterSpeed = new JoystickButton(gamePad,
        Constants.OI.INCREASE_SHOOTER_SPEED_PORT);
    increaseShooterSpeed.whenPressed(new IncreaseShootingSpeed());

    decreaseShooterSpeed = new JoystickButton(gamePad,
        Constants.OI.DECREASE_SHOOTER_SPEED_PORT);
    decreaseShooterSpeed.whenPressed(new DecreaseShootingSpeed());

    resetShooterSpeed = new JoystickButton(gamePad,
        Constants.OI.RESET_SHOOTER_SPEED_PORT);
    resetShooterSpeed.whenPressed(new ResetShootingSpeed());

    brakeCANTalons = new JoystickButton(xboxController,
        Constants.OI.BRAKE_CANTALONS_PORT);
    brakeCANTalons.whenPressed(new BrakeCANTalons());

    coastCANTalons = new JoystickButton(xboxController,
        Constants.OI.COAST_CANTALONS_PORT);
    coastCANTalons.whenPressed(new CoastCANTalons());

    climb = new JoystickButton(xboxController, Constants.OI.CLIMB_PORT);
    climb.whenPressed(new ToggleWinch());
  }

  public static OI getOI() {
    if (oi == null)
      oi = new OI();
    return oi;
  }
}
