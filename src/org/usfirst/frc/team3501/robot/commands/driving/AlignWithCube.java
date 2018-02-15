package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.utils.NetworkThread;
import org.usfirst.frc.team3501.robot.utils.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Command starts a thread which will get values from RaspberryPi regarding the
 * alignment of a cube using camera input Is currently mapped to the X button on
 * controller. Uses values from camera to align itself with the cube by moving
 * horizontally
 * 
 * @author Amin Hakem
 * @author Alexander Ivanov (a bit of help later on;)
 *
 */
public class AlignWithCube extends Command {

	private PIDController alignmentControllerX;
	private PIDController alignmentControllerY;
	private NetworkThread thread;

	public AlignWithCube() {
		requires(Robot.getDriveTrain());
		alignmentControllerX = new PIDController(DriveTrain.driveSidewaysP, DriveTrain.driveSidewaysI, 0);
		alignmentControllerX.setDoneRange(3);
	
		alignmentControllerY = new PIDController(DriveTrain.driveStraightP, DriveTrain.driveStraightI, 0);
        alignmentControllerY.setDoneRange(3);
         
		// initialize a thread which will run code to constantly update
		thread = new NetworkThread();
		thread.start();
        System.out.println("AlignWithCube initialized, thread started");
		// RaspberryPi will output 0 when camera is aligned
	}

	@Override
	protected void initialize() {
		alignmentControllerX.setSetPoint(0);
	      alignmentControllerY.setSetPoint(70);
	        System.out.println("setpoints set");

	}

	@Override
	protected void execute() {

		if (NetworkThread.isBoxVisible()) {
			double outputX = alignmentControllerX.calcPID(NetworkThread.getBoxX());
			double outputY = alignmentControllerX.calcPID(NetworkThread.getBoxY());
            DriveTrain.getDriveTrain().mecanumDrive(-outputX,-outputY, 0);
		}
	}

	@Override
	protected boolean isFinished() {
		return this.alignmentControllerX.isDone()&&this.alignmentControllerY.isDone();
	}

	@Override
	protected void end() {
      System.out.println("AlignWithCube finished");
	}

	@Override
	protected void interrupted() {
	}
}
