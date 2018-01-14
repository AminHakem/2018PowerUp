package org.usfirst.frc.team3501.robot.commands.elevator;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Elevator;
import org.usfirst.frc.team3501.robot.utils.PIDController;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class ClimbDistance extends Command {
    
    private Elevator elevator = Robot.getElevator();
    private double maxTimeOut;
    private double target;
    private double zeroAngle;
    private Preferences prefs;
    private PIDController climbController;

    private double climbP;
    private double climbI;
    private double climbD;
    
    public ClimbDistance(double distance, double maxTimeOut) {
        requires(elevator);
        this.maxTimeOut = maxTimeOut;
        this.target = distance;

        this.climbP = Constants.Elevator.CLIMB_P;
        this.climbI = Constants.Elevator.CLIMB_I;
        this.climbD = Constants.Elevator.CLIMB_D;
        this.climbController = new PIDController(climbP, climbI, climbD);
        this.climbController.setDoneRange(1.0);
        this.climbController.setMaxOutput(1.0);
        this.climbController.setMinDoneCycles(5);
      }

    //REQUIRES EXECUTE AND INITIALIZE METHODS
    
    @Override
    protected void initialize() {
      this.elevator.resetEncoders();
      this.climbController.setSetPoint(this.target);
    }
    
    @Override
    protected void execute() {
      
      double val = climbController.calcPID(elevator.getElevatorEncoderDistance());

      double drive = val;
      
      this.elevator.setMotorValue(drive);
    }
    
    @Override
    protected boolean isFinished() {
      return timeSinceInitialized() >= maxTimeOut
          || this.climbController.isDone();
    }
    
    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
      end();
    }
    
}
