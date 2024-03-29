package org.usfirst.frc.team3501.robot.commands.driving;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.utils.NetworkThread;
import org.usfirst.frc.team3501.robot.utils.PIDController;

/**
 * Command starts a thread which will get values from RaspberryPi regarding the alignment of a cube
 * using camera input Is currently mapped to the X button on controller. Uses values from camera
 * to align itself with the cube by rotating
 * @author Amin Hakem
 *
 */
public class AlignWithCubeRotate extends Command {
  private PIDController alignmentController;
  private NetworkThread thread;

    public AlignWithCubeRotate() {
      requires(Robot.getDriveTrain());
      alignmentController =
          new PIDController(DriveTrain.driveSidewaysPLong, DriveTrain.driveSidewaysILong, 0);
      alignmentController.setDoneRange(5);

      // initialize a thread which will run code to constantly update
      thread = new NetworkThread();
      thread.start();

      // RaspberryPi will output 0 when camera is aligned
    }

    // Called just before this Command runs the first time
    protected void initialize() {
      alignmentController.setSetPoint(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
      if (NetworkThread.isBoxVisible()) {
        double output = alignmentController.calcPID(NetworkThread.getBoxX());
        DriveTrain.getDriveTrain().mecanumDrive(0, 0, -output);
      }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
      return this.alignmentController.isDone();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
