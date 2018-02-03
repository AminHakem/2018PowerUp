package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.subsystems.Climber;
import org.usfirst.frc.team3501.robot.commands.AlignWithCube;
import org.usfirst.frc.team3501.robot.commands.driving.DriveForward;
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
  private static Elevator elevator;
  private int time;
  Command teleopCommand;
  Command autonCommand;
  SendableChooser autonChooser;

  @Override
  public void robotInit() {
    driveTrain = DriveTrain.getDriveTrain();
    oi = OI.getOI();
    elevator = Elevator.getElevator();

    time = 0;

    autonChooser = new SendableChooser();

    CameraServer server = CameraServer.getInstance();

    driveTrain.resetEncoders();
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
    autonCommand = new DriveForward(48, 5);
    Scheduler.getInstance().add(autonCommand);
    driveTrain.resetEncoders();
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
    driveTrain.resetGyro();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    updateSmartDashboard();
    time++;
    if (time % 50 == 0) {
      System.out.println("Angle:  " + driveTrain.getAngle());
    }

  }


  public void updateSmartDashboard() {
    SmartDashboard.putNumber("right left encoder: ", driveTrain.getRightLeftEncoderDistance());
    SmartDashboard.putNumber("front back encoder: ", driveTrain.getFrontBackEncoderDistance());
    SmartDashboard.putNumber("angle", driveTrain.getAngle());
    SmartDashboard.putNumber("Front Left Motor power",
        driveTrain.getFrontLeftMotorPower());
    SmartDashboard.putNumber("Front Right Motor power",
        driveTrain.getFrontRightMotorPower());
    SmartDashboard.putNumber("Rear Left Motor power",
        driveTrain.getRearLeftMotorPower());
    SmartDashboard.putNumber("Rear Right Motor power",
        driveTrain.getRearRightMotorPower());
  }

  @Override
  public void robotPeriodic() {
    // nothing to do in robotPeriodic
  }

  @Override
  public void disabledPeriodic() {
    // nothing to do in disabledPeriodic
  }
  @Override
	public void disabledInit() {
		// TODO Auto-generated method stub
		super.disabledInit();
	}

}
