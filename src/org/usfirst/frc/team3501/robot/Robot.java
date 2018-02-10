package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.commands.elevator.MoveToTarget;
import org.usfirst.frc.team3501.robot.subsystems.Climber;
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
  private static Elevator elevator;
  Command autonCommand;
  Command teleopCommand;
  SendableChooser autonChooser;

  @Override
  public void robotInit() {
    driveTrain = DriveTrain.getDriveTrain();
    oi = OI.getOI();
    elevator = Elevator.getElevator();

    driveTrain.resetEncoders();
    CameraServer server = CameraServer.getInstance();

    autonChooser = new SendableChooser();
    autonCommand = (Command) autonChooser.getSelected();
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
    autonCommand = new MoveToTarget(4, 160);
    autonCommand = new MoveToTarget(5, 3);
    autonCommand = new MoveToTarget(10, 5);
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
    SmartDashboard.putNumber("Elevator encoder: ", elevator.getHeight());
    SmartDashboard.putNumber("Elevator motor speed: ", elevator.getMotorVal());
    SmartDashboard.putBoolean("limitSwitch", elevator.isAtTop());
    SmartDashboard.putBoolean("Is elevator at top", elevator.isAtTop());
    SmartDashboard.putBoolean("Is elevator at bottom", elevator.isAtBottom());
    // SmartDashboard.putNumber("right left encoder: ", driveTrain.getRightLeftEncoderDistance());
    // SmartDashboard.putNumber("front back encoder: ", driveTrain.getFrontBackEncoderDistance());
    // SmartDashboard.putNumber("angle", driveTrain.getAngle());
    // SmartDashboard.putNumber("Elevator encoder: ", elevator.getHeight());
    // SmartDashboard.putNumber("Top IR Sensor: ", elevator.getTopIRSensorValue());
    // SmartDashboard.putNumber("Bottom IR Sensor: ", elevator.getBottomIRSensorValue());
  }
}
