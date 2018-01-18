package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.commands.driving.DriveDistance;
import org.usfirst.frc.team3501.robot.commands.driving.JoystickDrive;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import org.usfirst.frc.team3501.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.CameraServer;
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

  @Override
  public void autonomousInit() {
    autonCommand = (Command) autonChooser.getSelected();
    autonCommand = new DriveDistance(100, 0, 0);
    Scheduler.getInstance().add(autonCommand);
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    autonCommand = new JoystickDrive();
    Scheduler.getInstance().add(autonCommand);
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    updateSmartDashboard();
    driveTrain.printEncoderOutput();
  }

  public void updateSmartDashboard() {
    SmartDashboard.putNumber("right left encoder: ", driveTrain.getRightLeftEncoderDistance());
    SmartDashboard.putNumber("front back encoder: ", driveTrain.getFrontBackEncoderDistance());
    SmartDashboard.putNumber("angle", driveTrain.getAngle());
  }
}
