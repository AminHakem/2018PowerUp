package org.usfirst.frc.team3501.robot.commands;
import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.utils.NetworkThread;
import org.usfirst.frc.team3501.robot.utils.PIDController;

import com.sun.org.apache.bcel.internal.Constants;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command starts a thread which will get values from
 * RaspberryPi regarding the alignment of a cube
 * using camera input
 * Is currently mapped to the X button on controller
 * @author Amin Hakem
 *
 */
public class AlignWithCube extends Command {
	
	private double alignment;
	private double alignmentError;
	private PIDController alignmentController;
	private NetworkThread thread;
    
	public AlignWithCube() {
	    requires(Robot.getDriveTrain());
    	this.alignmentController = new PIDController(DriveTrain.driveP,0,0);  //PID CONSTANTS NEED TUNING
    	//initialize a thread which will run code to constantly update
    	//the thread output in DriveTrain
    	thread = new NetworkThread();
    //System.out.println(this.getClass().getName()+" thread initialized");
    	
    	thread.start();
       // System.out.println(this.getClass().getName()+" thread started");
    	
        //RaspberryPi will output 0 when camera is aligned
        alignmentController.setSetPoint(0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	 alignmentController.setSetPoint(0);
     	thread.run();		//this thread will continuously update the threadOutput variable in DriveTrain
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	while(!alignmentController.isDone()) {    		
       // System.out.println(this.getClass().getName()+" command began executing");
   		//this thread will continuously update the threadOutput variable in DriveTrain
    		this.alignmentError = DriveTrain.getDriveTrain().getThreadOutput();
    		double output = alignmentController.calcPIDError(alignmentError);
    		DriveTrain.getDriveTrain().mecanumDrive(0, output, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(this.alignmentController.isDone()) {
    		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
