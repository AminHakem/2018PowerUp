package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
  private static DriveTrain driveTrain;
  private CANTalon frontLeft, frontRight, rearLeft, rearRight;

  private DriveTrain() {
    frontLeft = new CANTalon(Constants.DriveTrain.FRONT_LEFT);
    frontRight = new CANTalon(Constants.DriveTrain.FRONT_RIGHT);
    rearLeft = new CANTalon(Constants.DriveTrain.REAR_LEFT);
    rearRight = new CANTalon(Constants.DriveTrain.REAR_RIGHT);
  }

  public static DriveTrain getDriveTrain() {
    if (driveTrain == null)
      driveTrain = new DriveTrain();
    return driveTrain;
  }

  @Override
  protected void initDefaultCommand() {
  }

}
