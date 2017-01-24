package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.commands.driving.TimeDrive;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.subsystems.Intake;
import org.usfirst.frc.team3501.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
  private static DriveTrain driveTrain;
  private static Shooter shooter;
  private static OI oi;
  private static Shooter shooter;
  private static OI oi;
  private static Intake intake;

  @Override
  public void robotInit() {
    driveTrain = DriveTrain.getDriveTrain();
    oi = OI.getOI();
    shooter = Shooter.getShooter();
    intake = Intake.getIntake();
  }

  public static DriveTrain getDriveTrain() {
    return DriveTrain.getDriveTrain();
  }

  public static Shooter getShooter() {
    return Shooter.getShooter();
  }

  public static OI getOI() {
    return OI.getOI();
  }

  public static OI getOI() {
    return OI.getOI();
  }

  public static Intake getIntake() {
    return Intake.getIntake();
  }

  public static Intake getIntake() {
    return Intake.getIntake();

  }

  @Override
  public void autonomousInit() {
    Scheduler.getInstance().add(new TimeDrive(1.5, 0.4));
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();

  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

  }
}
