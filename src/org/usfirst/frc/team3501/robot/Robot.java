package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.autoncommandgroups.switchcommands.CenterToLeft;
import org.usfirst.frc.team3501.robot.autoncommandgroups.switchcommands.CenterToRight;
import org.usfirst.frc.team3501.robot.autoncommandgroups.switchcommands.StartLeftSwitchLeft;
import org.usfirst.frc.team3501.robot.autoncommandgroups.switchcommands.StartLeftSwitchRight;
import org.usfirst.frc.team3501.robot.autoncommandgroups.switchcommands.StartRightSwitchLeft;
import org.usfirst.frc.team3501.robot.autoncommandgroups.switchcommands.StartRightSwitchRight;
import org.usfirst.frc.team3501.robot.commands.FireAllPistonPorts;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.DriveSideways;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;
import org.usfirst.frc.team3501.robot.subsystems.Climber;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import org.usfirst.frc.team3501.robot.subsystems.Intake;
import org.usfirst.frc.team3501.robot.utils.NetworkThread;
import org.usfirst.frc.team3501.robot.utils.TimerUtil;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
  public static Ultrasonic ultra;
  private static DriveTrain driveTrain;
  private static OI oi;
  private static Elevator elevator;
  private static Intake intake;
  Command autonCommand;

  // NetworkThread thread;

  UsbCamera climberCam, intakeCam;
  CameraServer cameraServer;

  SendableChooser<Integer> autonChooser;
  SendableChooser<Boolean> autonBaselineChooser;

  int autonStartPos;
  boolean baseline;
  String gameData, choice;

  @Override
  public void robotInit() {
    driveTrain = DriveTrain.getDriveTrain();
    oi = OI.getOI();
    elevator = Elevator.getElevator();
    LiveWindow.setEnabled(false);
    intake = Intake.getIntake();
    ultra = new Ultrasonic(8, 9);
    ultra.setAutomaticMode(true);

    // initialize and start timer Util
    TimerUtil.startTime(); // DO NOT REMOVE WILL CAUSE ERRORS

    // initialize and start a thread which will run code to constantly update
    // thread = new NetworkThread();
    // thread.start();
    CameraServer server = CameraServer.getInstance();

    autonChooser = new SendableChooser();
    autonBaselineChooser = new SendableChooser();

    autonChooser = new SendableChooser<Integer>();
    autonChooser.addDefault("Right", Integer.valueOf(0));
    autonChooser.addObject("Left", Integer.valueOf(1));
    autonChooser.addObject("Middle", Integer.valueOf(2));

    autonBaselineChooser = new SendableChooser<Boolean>();
    autonBaselineChooser.addDefault("Switch", Boolean.valueOf(false));
    autonBaselineChooser.addObject("Baseline", Boolean.valueOf(true));

    // UsbCamera rampCam = server.startAutomaticCapture("rampCam", 0);
    // rampCam.setResolution(1024, 1060);
    // UsbCamera hookCam = server.startAutomaticCapture("hookCam", 1);
    // hookCam.setResolution(1024, 1060);
    SmartDashboard.putData("Autonomous Selector", autonChooser);
    SmartDashboard.putData("Cross Field Selector", autonBaselineChooser);

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
    intake.resetEncoder();
    TimerUtil.startTime();

    chooseAuton();
    System.out.println("Game data: " + gameData + "***********");
    SmartDashboard.putString("Game Data", gameData);
    System.out.println(autonChooser.getSelected());
    System.out.println(autonCommand.getName());
    Scheduler.getInstance().add(autonCommand);
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    updateSmartDashboard();
    if (elevator.isAtBottom() == true)
      elevator.resetEncoders();
  }

  @Override
  public void teleopInit() {
    Scheduler.getInstance().removeAll();
    intake.resetEncoder();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    updateSmartDashboard();
    System.out.println("Intake Encoder pulses: " + intake.getEncoderPulses());
    if (elevator.isAtBottom() == true)
      elevator.resetEncoders();
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
    SmartDashboard.putNumber("ultrasonic", ultra.getRangeInches());
  }

  public void updateElevator() {
    SmartDashboard.putNumber("Elevator encoder: ", elevator.getHeight());
    SmartDashboard.putBoolean("Is elevator at top", elevator.isAtTop());
    SmartDashboard.putBoolean("Is elevator at bottom", elevator.isAtBottom());
  }

  public void displayCameraFeed() {
    SmartDashboard.putData("Ramp Camera Feed", (Sendable) cameraServer.getVideo("rampCam"));
    SmartDashboard.putData("Hook Camera Feed", (Sendable) cameraServer.getVideo("hookCam"));
    SmartDashboard.putBoolean("Cube visible: ", NetworkThread.isBoxVisible());
  }

  public void chooseAuton() {
    this.autonStartPos = autonChooser.getSelected();
    this.baseline = autonBaselineChooser.getSelected();
    do {
      gameData = DriverStation.getInstance().getGameSpecificMessage();// Right=0 Left=1 Mid=2
    } while (gameData.length() < 1);
    if (gameData.charAt(0) == 'L') {
      if (autonStartPos == 0) {
       // autonCommand = new StartRightSwitchLeft();
        autonCommand = new DriveSideways(150, 5);
      } else if (autonStartPos == 1) {
        autonCommand = new StartLeftSwitchLeft();
      } else if (autonStartPos == 2) {
        autonCommand = new CenterToLeft();
      }
      if (baseline)
        autonCommand = new DriveSideways(-150, 5);
    } else if (gameData.charAt(0) == 'R') {
      if (autonStartPos == 0) {
        autonCommand = new StartRightSwitchRight();
      } else if (autonStartPos == 1) {
        //autonCommand = new StartLeftSwitchRight();
        autonCommand = new DriveSideways(-150, 5);
      } else if (autonStartPos == 2) {
        autonCommand = new CenterToRight();
      }
      if (baseline) {
        autonCommand = new DriveSideways(150, 5);
      }
    }
  }
}
