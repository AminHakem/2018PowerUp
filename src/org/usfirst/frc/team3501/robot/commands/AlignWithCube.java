package org.usfirst.frc.team3501.robot.commands;

import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.utils.PIDController;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AlignWithCube extends Command {
	
	private double alignment;
	private double x;
	private PIDController alignmentController;
    public AlignWithCube() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.alignmentController = new PIDController(); //need to tune
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	 alignmentController.setSetPoint(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	while(!alignmentController.isDone()) {
    		this.x = 0;
    		
    		
    		
    		
    		
    		 x  = alignmentController.calcPID(x);
    		 DriveTrain.getDriveTrain().mecanumDrive(0, x, 0, false);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
