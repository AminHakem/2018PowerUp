package org.usfirst.frc.team3501.robot.commands;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

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
	private DatagramSocket socket;
	private byte[] buf = new byte[256];
    public AlignWithCube() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.alignmentController = new PIDController(); //need to tune
    	try {

			socket = new DatagramSocket(1025);

		} catch (SocketException e1) {

			// TODO Auto-generated catch block

			e1.printStackTrace();

		}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	 alignmentController.setSetPoint(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	while(!alignmentController.isDone()) {    		
    		DatagramPacket packet = new DatagramPacket(buf, buf.length);

    		try {

    			socket.receive(packet);

    		} catch (IOException e) {

    			// TODO Auto-generated catch block

    			System.out.println("ouch. you didn't get anything!");

    		}



    		InetAddress address = packet.getAddress();

    		int port = packet.getPort();

    		packet = new DatagramPacket(buf, buf.length, address, port);

    		String received = new String(packet.getData(), 0, packet.getLength());

    		received = received.substring(1);

    		int x = Integer.parseInt(received.trim());

    		x = x - 425;
    		
    		double output;
    		 output = (int) alignmentController.calcPID(x);
    		 DriveTrain.getDriveTrain().mecanumDrive(0, output, 0);
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
