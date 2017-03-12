package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.commandgroups.AutonMiddleGear;
import org.usfirst.frc.team3501.robot.commandgroups.AutonSideGear;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.subsystems.Intake;
import org.usfirst.frc.team3501.robot.subsystems.Shooter;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
  private static DriveTrain driveTrain;
  private static Shooter shooter;
  private static OI oi;
  private static Intake intake;

  Command autonCommand;
  SendableChooser autonChooser;

  @Override
  public void robotInit() {
    driveTrain = DriveTrain.getDriveTrain();
    oi = OI.getOI();
    shooter = Shooter.getShooter();
    intake = Intake.getIntake();

    autonChooser = new SendableChooser();
    autonChooser.addDefault("Middle Gear", new AutonMiddleGear());
    autonChooser.addObject("Red Boiler Gear",
        new AutonSideGear("RED", "BOILER"));
    autonChooser.addObject("Red Retrieval Gear",
        new AutonSideGear("RED", "RETRIEVAL"));
    autonChooser.addObject("Blue Boiler Gear",
        new AutonSideGear("BLUE", "BOILER"));
    autonChooser.addObject("Blue Retrieval Gear",
        new AutonSideGear("BLUE", "RETRIEVAL"));
    SmartDashboard.putData("Autonomous Chooser", autonChooser);

    CameraServer server = CameraServer.getInstance();
    UsbCamera climberCam = server.startAutomaticCapture("climbercam", 0);
    UsbCamera intakeCam = server.startAutomaticCapture("intakecam", 1);

    driveTrain.setCANTalonsBrakeMode(driveTrain.DRIVE_COAST_MODE);
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

  @Override
  public void autonomousInit() {
    // driveTrain.setLowGear();
    driveTrain.setCANTalonsBrakeMode(driveTrain.DRIVE_COAST_MODE);

    // autonCommand = (Command) autonChooser.getSelected();
    // autonCommand = new TimeDrive(1.5, 0.6);
    // Scheduler.getInstance().add(autonCommand);
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // driveTrain.setHighGear();
    driveTrain.setCANTalonsBrakeMode(driveTrain.DRIVE_COAST_MODE);
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    updateSmartDashboard();
  }

  public void updateSmartDashboard() {
    SmartDashboard.putNumber("left encode ",
        driveTrain.getLeftEncoderDistance());
    SmartDashboard.putNumber("right encoder",
        driveTrain.getRightEncoderDistance());
    SmartDashboard.putNumber("angle", driveTrain.getAngle());
    SmartDashboard.putNumber("voltage",
        DriverStation.getInstance().getBatteryVoltage());
    SmartDashboard.putNumber("rpm", shooter.getShooterRPM());
    SmartDashboard.putNumber("target shooting",
        shooter.getTargetShootingSpeed());
  }
}
