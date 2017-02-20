package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.subsystems.Intake;
import org.usfirst.frc.team3501.robot.subsystems.Shooter;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
  private static DriveTrain driveTrain;
  private static Shooter shooter;
  private static OI oi;
  private static Intake intake;

  @Override
  public void robotInit() {
    driveTrain = DriveTrain.getDriveTrain();
    oi = OI.getOI();
    shooter = Shooter.getShooter();
    intake = Intake.getIntake();
    CameraServer server = CameraServer.getInstance();
    UsbCamera climberCam = server.startAutomaticCapture("climbercam", 0);
    UsbCamera intakeCam = server.startAutomaticCapture("intakecam", 1);
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

  public static Intake getIntake() {
    return Intake.getIntake();
  }

  // If the gear values do not match in the left and right piston, then they are
  // both set to high gear
  @Override
  public void autonomousInit() {
    driveTrain.setHighGear();
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
    updateSmartDashboard();
  }

  public void updateSmartDashboard() {
    SmartDashboard.putNumber("angle", driveTrain.getAngle());
    SmartDashboard.putNumber("voltage",
        DriverStation.getInstance().getBatteryVoltage());
    SmartDashboard.putNumber("rpm", shooter.getShooterRPM());
    SmartDashboard.putNumber("motor value", shooter.getCurrentShootingSpeed());
  }
}
