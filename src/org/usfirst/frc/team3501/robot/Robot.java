package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
  private static DriveTrain driveTrain;
  private static OI oi;

  Command autonCommand;
  SendableChooser autonChooser;

  @Override
  public void robotInit() {
    driveTrain = DriveTrain.getDriveTrain();
    oi = OI.getOI();

    autonChooser = new SendableChooser();

    CameraServer server = CameraServer.getInstance();
  }

  public static DriveTrain getDriveTrain() {
    return DriveTrain.getDriveTrain();
  }

  public static OI getOI() {
    return OI.getOI();
  }

  @Override
  public void autonomousInit() {
    // autonCommand = (Command) autonChooser.getSelected();
    Scheduler.getInstance().add(autonCommand);
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    updateSmartDashboard();
  }

  public void updateSmartDashboard() {
    SmartDashboard.putNumber("left encode ", driveTrain.getLeftEncoderDistance());
    SmartDashboard.putNumber("right encoder", driveTrain.getRightEncoderDistance());
    SmartDashboard.putNumber("angle", driveTrain.getAngle());
    SmartDashboard.putNumber("voltage", DriverStation.getInstance().getBatteryVoltage());
  }
}
