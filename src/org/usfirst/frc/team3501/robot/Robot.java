package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {

  @Override
  public void robotInit() {
    DriveTrain.getDriveTrain();
    OI.getOI();
  }
  
  public static DriveTrain getDriveTrain(){
	  return DriveTrain.getDriveTrain();
  }
  
  public static OI getOI(){
	  return OI.getOI();
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
