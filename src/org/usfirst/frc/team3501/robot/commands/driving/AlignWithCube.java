package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.utils.NetworkThread;
import org.usfirst.frc.team3501.robot.utils.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Command starts a thread which will get values from RaspberryPi regarding the alignment of a cube
 * using camera input Is currently mapped to the X button on controller. Uses values from camera
 * to align itself with the cube by moving horizontally
 * @author Amin Hakem
 *
 */
public class AlignWithCube extends Command {

  private PIDController alignmentController;
  private NetworkThread thread;
  private double previousThrust = 0;
  private Joystick joystick;
  
  public AlignWithCube() {
    requires(Robot.getDriveTrain());
    alignmentController =
        new PIDController(DriveTrain.driveSidewaysP, DriveTrain.driveSidewaysI, 0);
    alignmentController.setDoneRange(5);
    System.out.println("align constructor");
    joystick = Robot.getOI().ps4_controller;
    System.out.println("after getting joystick");
    // initialize a thread which will run code to constantly update
    thread = new NetworkThread();
    thread.start();

    // RaspberryPi will output 0 when camera is aligned
  }

  @Override
  protected void initialize() {
    alignmentController.setSetPoint(0);
  }

  @Override
  protected void execute() {
  //  double ySpeed = -joystick.getRawAxis(1);
    //ySpeed = (6 * previousThrust + ySpeed) / 7;
    //previousThrust = ySpeed;
    
    if (NetworkThread.isBoxVisible()) {
      double output = alignmentController.calcPID(NetworkThread.getPos());
      DriveTrain.getDriveTrain().mecanumDrive(-output, 0, 0);
    }
  }

  @Override
  protected boolean isFinished() {
    return this.alignmentController.isDone();
  }

  @Override
  protected void end() {
    Robot.getDriveTrain().toggleAlignedWithCube();
  }

  @Override
  protected void interrupted() {}
}
