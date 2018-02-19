package org.usfirst.frc.team3501.robot.commands.driving;

import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3501.robot.utils.NetworkThread;
import org.usfirst.frc.team3501.robot.utils.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Command starts a thread which will get values from RaspberryPi regarding the alignment of a cube
 * using camera input Is currently mapped to the X button on controller. Uses values from camera to
 * align itself with the cube by moving horizontally
 *
 * @author Amin Hakem
 * @author Alexander Ivanov (a bit of help later on;)
 *
 */
public class AlignWithCube extends Command {

  private PIDController alignmentControllerX;
  private PIDController alignmentControllerY;

  public AlignWithCube() {
    requires(Robot.getDriveTrain());
    alignmentControllerX = new PIDController(0.012, 0.0011, -0.004);
    alignmentControllerX.setDoneRange(3);

    alignmentControllerY = new PIDController(0.2, 0.003, -0.002);
    alignmentControllerY.setDoneRange(0.2);
  }

  @Override
  protected void initialize() {
    alignmentControllerX.setSetPoint(0);
    alignmentControllerY.setSetPoint(1);
    System.out.println("setpoints set");

  }

  @Override
  protected void execute() {

    if (NetworkThread.isBoxVisible()) {
      double outputX = alignmentControllerX.calcPID(NetworkThread.getBoxX());
      double outputY = alignmentControllerY.calcPID(NetworkThread.getBoxSize());
      DriveTrain.getDriveTrain().mecanumDrive(-outputX, -outputY, 0);
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    DriveTrain.getDriveTrain().stop();
    System.out.println("AlignWithCube finished");
  }

  @Override
  protected void interrupted() {}
}
