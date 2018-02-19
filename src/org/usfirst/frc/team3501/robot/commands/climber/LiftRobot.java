package org.usfirst.frc.team3501.robot.commands.climber;

import org.usfirst.frc.team3501.robot.Constants.Direction;
import org.usfirst.frc.team3501.robot.Robot;
import org.usfirst.frc.team3501.robot.subsystems.Climber;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author: niyatisriram
 *          Lifts robot using climbing winch when button is pressed
 */
public class LiftRobot extends Command {

  private Climber climber = Robot.getClimber();
  private Direction whichMotor;
  public LiftRobot(Direction whichMotor) {
    requires(climber);
    this.whichMotor = whichMotor;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if(whichMotor == Direction.LEFT) {
    Climber.setLeftMotorVal(1.0/4);
    Timer.delay(0.5);
    }else if(whichMotor ==Direction.RIGHT) {
      Climber.setRightMotorVal(1.0/4);
      Timer.delay(0.5);
    }else if(whichMotor == Direction.UP) {
      Climber.setRightMotorVal(1.0/4);
      Climber.setLeftMotorVal(1.0/4);
      Timer.delay(0.5);
    }
    
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
    
    Climber.stop();
    Climber.setRightMotorVal(0);
    Climber.setLeftMotorVal(0);
  }

  @Override
  protected void interrupted() {
  }
}
