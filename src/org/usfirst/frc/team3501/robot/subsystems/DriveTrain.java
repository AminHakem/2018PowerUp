package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
  private CANTalon frontLeft, frontRight, rearLeft, rearRight;

  public DriveTrain() {
    frontLeft = new CANTalon(Constants.DriveTrain.FRONT_LEFT);
    frontRight = new CANTalon(Constants.DriveTrain.FRONT_RIGHT);
    rearLeft = new CANTalon(Constants.DriveTrain.REAR_LEFT);
    rearRight = new CANTalon(Constants.DriveTrain.REAR_RIGHT);
  }

  @Override
  protected void initDefaultCommand() {
  }

}
