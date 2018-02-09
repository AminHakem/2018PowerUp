package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.utils.NetworkThread;
import org.usfirst.frc.team3501.robot.utils.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Command starts a thread which will get values from RaspberryPi regarding the alignment of a cube
 * using camera input Is currently mapped to the X button on controller
 *
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
    alignmentController =
        new PIDController(DriveTrain.driveSidewaysP, DriveTrain.driveSidewaysI, 0);
    alignmentController.setDoneRange(5);

    // initialize a thread which will run code to constantly update
    thread = new NetworkThread();
    thread.start();

    // RaspberryPi will output 0 when camera is aligned
  }

  @Override
  protected void initialize() {
    alignmentController.setSetPoint(0);
    thread.run(); // this thread will continuously update the threadOutput variable in DriveTrain
  }

  @Override
  protected void execute() {
    this.alignmentError = DriveTrain.getDriveTrain().getThreadOutput();
    double output = alignmentController.calcPIDError(alignmentError);
    DriveTrain.getDriveTrain().mecanumDrive(0, output, 0);
  }

  @Override
  protected boolean isFinished() {
    if (this.alignmentController.isDone()) {
      System.out.println(this.getClass().getName() + " is finished!!");
      Robot.getDriveTrain().toggleAlignedWithCube();
      return true;
    }
    return false;
  }

  @Override
  protected void end() {}

  @Override
  protected void interrupted() {}
}
