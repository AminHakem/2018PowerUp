package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.commands.driving.TimeDrive;
import org.usfirst.frc.team3501.robot.subsystems.Climber;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
  private static DriveTrain driveTrain;
  private static OI oi;
  private static Climber climber;
  private static Shooter shooter;

  @Override
  public void robotInit() {
    driveTrain = DriveTrain.getDriveTrain();
    oi = OI.getOI();
    climber = Climber.getClimber();
    shooter = Shooter.getShooter();

  }

  public static DriveTrain getDriveTrain() {
    return DriveTrain.getDriveTrain();
  }

  public static OI getOI() {
    return OI.getOI();
  }

  public static Climber getClimber() {
    return Climber.getClimber();
  }

  public static Shooter getShooter() {
    return Shooter.getShooter();
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
