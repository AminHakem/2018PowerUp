package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.commands.elevator.MoveToTarget;
import org.usfirst.frc.team3501.robot.subsystems.Climber;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import org.usfirst.frc.team3501.robot.subsystems.Intake;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
  private static DriveTrain driveTrain;
  private static OI oi;
  private static Elevator elevator;
  Command autonCommand;
  Command teleopCommand;
  SendableChooser autonChooser;

  UsbCamera climberCam, intakeCam;
  CameraServer cameraServer;

  @Override
  public void robotInit() {
    driveTrain = DriveTrain.getDriveTrain();
    oi = OI.getOI();
    elevator = Elevator.getElevator();

    driveTrain.resetEncoders();

    autonChooser = new SendableChooser();
    autonCommand = (Command) autonChooser.getSelected();


    CameraServer server = CameraServer.getInstance();

    UsbCamera rampCam = server.startAutomaticCapture("rampCam", 0);
    rampCam.setResolution(1024, 1060);
    UsbCamera hookCam = server.startAutomaticCapture("hookCam", 1);
    hookCam.setResolution(1024, 1060);
  }

  public static Elevator getElevator() {
    return Elevator.getElevator();
  }

  public static DriveTrain getDriveTrain() {
    return DriveTrain.getDriveTrain();
  }

  public static OI getOI() {
    return OI.getOI();
  }

  public static Intake getIntake() {
    return Intake.getIntake();
  }

  public static Climber getClimber() {
    return Climber.getClimber();
  }

  @Override
  public void autonomousInit() {
    driveTrain.resetGyro();
    driveTrain.resetEncoders();
    autonCommand = new MoveToTarget(10, 60);
    Scheduler.getInstance().add(autonCommand);
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    updateSmartDashboard();
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    updateSmartDashboard();
  }

  public void updateSmartDashboard() {
    updateDriving();
    updateElevator();
  }

  public void updateDriving() {
    SmartDashboard.putNumber("right left encoder: ", driveTrain.getRightLeftEncoderDistance());
    SmartDashboard.putNumber("front back encoder: ", driveTrain.getFrontBackEncoderDistance());
    SmartDashboard.putNumber("angle", driveTrain.getAngle());
  }

  public void updateElevator() {
    SmartDashboard.putNumber("Elevator encoder: ", elevator.getHeight());
    SmartDashboard.putBoolean("Is elevator at top", elevator.isAtTop());
    SmartDashboard.putBoolean("Is elevator at bottom", elevator.isAtBottom());
  }

  public void displayCameraFeed() {
    SmartDashboard.putData("Ramp Camera Feed", (Sendable) cameraServer.getVideo("rampCam"));

    SmartDashboard.putData("Hook Camera Feed", (Sendable) cameraServer.getVideo("hookCam"));
  }
}
