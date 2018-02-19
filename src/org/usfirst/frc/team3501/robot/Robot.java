package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.autoncommandgroups.CenterToLeft;
import org.usfirst.frc.team3501.robot.autoncommandgroups.CenterToRight;
import org.usfirst.frc.team3501.robot.autoncommandgroups.StartLeftSwitchLeft;
import org.usfirst.frc.team3501.robot.autoncommandgroups.StartLeftSwitchRight;
import org.usfirst.frc.team3501.robot.autoncommandgroups.StartRightSwitchLeft;
import org.usfirst.frc.team3501.robot.autoncommandgroups.StartRightSwitchRight;
import org.usfirst.frc.team3501.robot.subsystems.Climber;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import org.usfirst.frc.team3501.robot.subsystems.Intake;
import org.usfirst.frc.team3501.robot.utils.NetworkThread;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
  private static DriveTrain driveTrain;
  private static OI oi;
  private static Elevator elevator;
  Command autonCommand;

  NetworkThread thread;

  UsbCamera climberCam, intakeCam;
  CameraServer cameraServer;

  SendableChooser<Integer> autonChooser;
  int autonStartPos;
  String gameData;

  @Override
  public void robotInit() {
    driveTrain = DriveTrain.getDriveTrain();
    oi = OI.getOI();
    elevator = Elevator.getElevator();
    LiveWindow.setEnabled(false);

    // initialize a thread which will run code to constantly update
    thread = new NetworkThread();
    thread.start();

    autonChooser = new SendableChooser();

    gameData = DriverStation.getInstance().getGameSpecificMessage();

    CameraServer server = CameraServer.getInstance();
    autonChooser = new SendableChooser<Integer>();
    autonChooser.addObject("right", Integer.valueOf(0));
    autonChooser.addObject("left", Integer.valueOf(1));
    autonChooser.addObject("middle", Integer.valueOf(2));

    // UsbCamera rampCam = server.startAutomaticCapture("rampCam", 0);
    // rampCam.setResolution(1024, 1060);
    // UsbCamera hookCam = server.startAutomaticCapture("hookCam", 1);
    // hookCam.setResolution(1024, 1060);
    SmartDashboard.putData("Autonomous Selector", autonChooser);
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
    autonStartPos = autonChooser.getSelected();
    if (gameData.length() > 0) {
      if (gameData.charAt(0) == 'L') {
        switch (autonStartPos) {
          case 0:
            autonCommand = new StartRightSwitchLeft();
          case 1:
            autonCommand = new StartLeftSwitchLeft();
          case 2:
            autonCommand = new CenterToLeft();
        }
      } else if (gameData.charAt(0) == 'R') {
        switch (autonStartPos) {
          case 0:
            autonCommand = new StartRightSwitchRight();
          case 1:
            autonCommand = new StartLeftSwitchRight();
          case 2:
            autonCommand = new CenterToRight();
        }
      }
    }
    Scheduler.getInstance().add(autonCommand);
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    updateSmartDashboard();
    if (elevator.isAtBottom() == true) {
      elevator.resetEncoders();
    }
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    updateSmartDashboard();
    if (elevator.isAtBottom() == true) {
      elevator.resetEncoders();
    }
  }

  public void updateSmartDashboard() {
    updateDriving();
    updateElevator();
  }

  public void updateDriving() {
    SmartDashboard.putNumber("right left encoder: ", driveTrain.getRightLeftEncoderDistance());
    SmartDashboard.putNumber("front back encoder: ", driveTrain.getFrontBackEncoderDistance());
    SmartDashboard.putNumber("angle", driveTrain.getAngle());
    SmartDashboard.putNumber("rearleft", driveTrain.getRearLeftMotorPower());
    SmartDashboard.putNumber("rearright", driveTrain.getRearRightMotorPower());
    SmartDashboard.putNumber("frontleft", driveTrain.getFrontLeftMotorPower());
    SmartDashboard.putNumber("frontright", driveTrain.getFrontRightMotorPower());
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
