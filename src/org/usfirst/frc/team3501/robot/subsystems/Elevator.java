package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {
    
    private static Elevator elevator;
 
    //PID VALUES ARE FOUND IN CONSTANTS.JAVA
    
    private final TalonSRX elevatorTalon;
    private final Encoder elevatorEncoder;
    private Elevator() {
        
        //MOTOR CONTROLLERS
        elevatorTalon = new TalonSRX(Constants.Elevator.ELEVATOR);
        
        //ENCODERS
        elevatorEncoder = new Encoder(Constants.Elevator.ELEVATOR_ENCODER_A,
                Constants.Elevator.ELEVATOR_ENCODER_B, false, Encoder.EncodingType.k4X);
    
    }
        
    public static Elevator getElevator() {
        if (elevator == null) {
          elevator = new Elevator();
        }
        return elevator;
     }
    
    
    // MOTOR METHODS
    public void setMotorValue(double motorVal) {
      motorVal = MathLib.restrictToRange(motorVal, -1.0, 1.0);

      elevatorTalon.set(ControlMode.PercentOutput, motorVal);
    }

    
    public void setCANTalonsBrake() {
      elevatorTalon.setNeutralMode(NeutralMode.Brake);
    }

    public void stop() {
      setMotorValue(0);
    }

    public double getMotorVal() {
      return (elevatorTalon.getMotorOutputPercent());
    }


    
    // ENCODER METHODS

    public double getElevatorEncoderDistance() {
      return elevatorEncoder.getDistance();
    }


    public void printEncoderOutput() {
      System.out.println("Encoder: " + getElevatorEncoderDistance());
    }


    public void resetEncoders() {
      elevatorEncoder.reset();
    }

    public double getSpeed() {
      return elevatorEncoder.getRate();
    }

    
    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub
        
    }
   
}
