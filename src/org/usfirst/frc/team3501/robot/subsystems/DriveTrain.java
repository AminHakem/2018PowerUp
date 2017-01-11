package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.commands.driving.JoystickDrive;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	private static DriveTrain driveTrain;
	private final CANTalon frontLeft, frontRight, rearLeft, rearRight;
	private final RobotDrive robotDrive;
	private final Encoder leftEncoder, rightEncoder;

	private DriveTrain() {
		// MOTOR CONTROLLERS
		frontLeft = new CANTalon(Constants.DriveTrain.FRONT_LEFT);
		frontRight = new CANTalon(Constants.DriveTrain.FRONT_RIGHT);
		rearLeft = new CANTalon(Constants.DriveTrain.REAR_LEFT);
		rearRight = new CANTalon(Constants.DriveTrain.REAR_RIGHT);

		// ENCODERS
		leftEncoder = new Encoder(Constants.DriveTrain.ENCODER_LEFT_A, Constants.DriveTrain.ENCODER_LEFT_B);
		rightEncoder = new Encoder(Constants.DriveTrain.ENCODER_RIGHT_A, Constants.DriveTrain.ENCODER_RIGHT_B);

		leftEncoder.setDistancePerPulse(Constants.DriveTrain.INCHES_PER_PULSE);
		rightEncoder.setDistancePerPulse(Constants.DriveTrain.INCHES_PER_PULSE);

		// ROBOT DRIVE
		robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
	}

	public static DriveTrain getDriveTrain() {
		if (driveTrain == null) {
			driveTrain = new DriveTrain();
		}
		return driveTrain;
	}

	// DRIVE METHODS
	public void setMotorValues(final double left, final double right) {
		robotDrive.tankDrive(left, right);
	}

	public void joystickDrive(final double thrust, final double twist) {
		robotDrive.arcadeDrive(thrust, twist);
	}

	public void stop() {
		setMotorValues(0, 0);
	}

	public double getFrontLeftMotorVal() {
		return frontLeft.get();
	}

	public double getFrontRightMotorVal() {
		return frontRight.get();
	}

	public double getRearLeftMotorVal() {
		return frontLeft.get();
	}

	public double getRearRightMotorVal() {
		return frontLeft.get();
	}

	public CANTalon getFrontLeft() {
		return frontLeft;
	}

	public CANTalon getFrontRight() {
		return frontRight;
	}

	public CANTalon getRearLeft() {
		return rearLeft;
	}

	public CANTalon getRearRight() {
		return rearRight;
	}

	// ENCODER METHODS

	public double getLeftEncoder() {
		return leftEncoder.getDistance();
	}

	public double getRightEncoder() {
		return rightEncoder.getDistance();
	}

	public double getAvgEncoderDistance() {
		return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
	}

	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public double getLeftSpeed() {
		return leftEncoder.getRate();
	}

	public double getRightSpeed() {
		return rightEncoder.getRate();
	}

	public double getSpeed() {
		return (getLeftSpeed() + getRightSpeed()) / 2.0;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoystickDrive());
	}

}
