package org.usfirst.frc.team3501.robot.commands;

import java.util.concurrent.TimeUnit;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FireAllPistonPorts extends Command {
    Solenoid solenoid;
    int i =0;
    public FireAllPistonPorts() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
      System.out.println("piston port: "+i+"**********");
      solenoid = new Solenoid(i);
      solenoid.set(true);
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        System.out.println("ERROR********");
      }
      i++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return i>7;
    }

    // Called once after isFinished returns true
    protected void end() {
      System.out.println("DONE********");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
