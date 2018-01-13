package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {
    private static Elevator Lift;
 
    private final TalonSRX liftLeft, liftRight;
    private final Encoder encoderLeft, encoderRight;
    private Elevator() {
        //MOTOR CONTROLLERS
        liftLeft = new TalonSRX(Constants.Lift.LIFT_LEFT);
        liftRight = new TalonSRX(Constants.Lift.LIFT_RIGHT);
        
        //ENCODERS
        encoderLeft = new Encoder(Constants.Lift.ELEVATOR_ENCODER_LEFT_A,
                Constants.Lift.ELEVATOR_ENCODER_LEFT_B, false, Encoder.EncodingType.k4X);
        encoderRight = new Encoder(Constants.Lift.ELEVATOR_ENCODER_RIGHT_A,
                Constants.Lift.ELEVATOR_ENCODER_RIGHT_B, false, Encoder.EncodingType.k4X);
    }
    
    // MOTOR METHODS
    public void setMotorValues(double left, double right) {
      left = MathLib.restrictToRange(left, -1.0, 1.0);
      right = MathLib.restrictToRange(right, -1.0, 1.0);

      liftLeft.set(ControlMode.PercentOutput, left);
      liftRight.set(ControlMode.PercentOutput, -right);
    }

    
    public void setCANTalonsBrake() {
      liftLeft.setNeutralMode(NeutralMode.Brake);
      liftRight.setNeutralMode(NeutralMode.Brake);
    }

    public void stop() {
      setMotorValues(0, 0);
    }

    public double getLeftMotorVal() {
      return (liftLeft.getMotorOutputPercent());
    }

    public double getRightMotorVal() {
      return (liftRight.getMotorOutputPercent());
    }

    
    // ENCODER METHODS

    public double getEncoderLeftDistance() {
      return encoderLeft.getDistance();
    }

    public double getEncoderRightDistance() {
      return encoderRight.getDistance();
    }

    public void printEncoderOutput() {
      System.out.println("left: " + getEncoderLeftDistance());
      System.out.println("right: " + getEncoderRightDistance());
    }

    public double getAvgEncoderDistance() {
      return (encoderLeft.getDistance() + encoderRight.getDistance()) / 2;
    }

    public void resetEncoders() {
      encoderLeft.reset();
      encoderRight.reset();
    }

    public double getLeftSpeed() {
      return encoderLeft.getRate();
    }

    public double getRightSpeed() {
      return encoderRight.getRate();
    }

    public double getSpeed() {
      return (getLeftSpeed() + getRightSpeed()) / 2.0;
    }
    
    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub
        
    }
}
