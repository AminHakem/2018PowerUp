package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.Constants.Direction;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
import org.usfirst.frc.team3501.robot.commands.driving.DriveSideways;
import org.usfirst.frc.team3501.robot.commands.driving.JoystickDrive;
import org.usfirst.frc.team3501.robot.commands.driving.TurnForAngle;
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
   //autonCommand = new DriveSideways(30, 1000);
    //autonCommand = new DriveForward(50, 5);
    autonCommand = new TurnForAngle(90, 5);
    Scheduler.getInstance().add(autonCommand);
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    updateSmartDashboard();
  }

  @Override
  public void teleopInit() {
    teleopCommand = new JoystickDrive();
    Scheduler.getInstance().add(teleopCommand);
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    System.out.println("rightleft: " + driveTrain.getRightLeftEncoderDistance());
    System.out.println("frontback: " + driveTrain.getRightLeftEncoderDistance());
    updateSmartDashboard();
  }

  public void updateSmartDashboard() {
   
    SmartDashboard.putNumber("right left encoder values: ", driveTrain.getRightLeftEncoderDistance());
    SmartDashboard.putNumber("front back encoder values: ", driveTrain.getFrontBackEncoderDistance());
    SmartDashboard.putNumber("angle", driveTrain.getAngle());
    SmartDashboard.putNumber("Elevator encoder: ", elevator.getHeight());
    SmartDashboard.putNumber("Top IR Sensor: ", elevator.getTopIRSensorValue());
    SmartDashboard.putNumber("Bottom IR Sensor: ", elevator.getBottomIRSensorValue());
    SmartDashboard.putNumber("angle", driveTrain.getAngle());
    // SmartDashboard.putNumber("Elevator encoder: ", elevator.getHeight());
    SmartDashboard.putNumber("Elevator motor speed: ", elevator.getMotorVal());
    // SmartDashboard.putNumber("Elevator Direction: ", elevator.getDirection());
    SmartDashboard.putNumber("front back speed",
        driveTrain.getFrontBackSpeed());
    // SmartDashboard.putNumber("Elevator encoder: ", elevator.getHeight());
    SmartDashboard.putNumber("right left speed: ",driveTrain.getRightLeftSpeed());
    // SmartDashboard.putNumber("Elevator Direction: ", elevator.getDirection());
    
  }
}
