package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.Constants.DriveTrain;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
  public static OI oi;
  public static DriveTrain driveTrain;

  @Override
  public void robotInit() {
    driveTrain = new DriveTrain();
    oi = new OI();
  }

  @Override
  public void autonomousInit() {
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
