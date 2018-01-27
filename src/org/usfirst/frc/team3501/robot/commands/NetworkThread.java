package org.usfirst.frc.team3501.robot.commands;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;

public class NetworkThread extends Thread {
	private DatagramSocket socket;
	private byte[] buf = new byte[256];
	private DriveTrain driveTrain;
	public NetworkThread() {
	   	try {

				socket = new DatagramSocket(1025);

			} catch (SocketException e1) {

				// TODO Auto-generated catch block

				e1.printStackTrace();

			}
	   	driveTrain = DriveTrain.getDriveTrain();
	}
    public void run(){
    	System.out.println(this.getClass().getName()+" thread  run() started");
     	while(true) {
        	System.out.println(this.getClass().getName()+" while loop started");

    		DatagramPacket packet = new DatagramPacket(buf, buf.length);

    		try {
    			socket.receive(packet);
    		} catch (IOException e) {
    			System.out.println("ouch. you didn't get anything!");
    		}
			System.out.println("Got it!");

    		InetAddress address = packet.getAddress();

    		int port = packet.getPort();

    		packet = new DatagramPacket(buf, buf.length, address, port);
   		String received = "   ";
			try {
				received = new String(packet.getData(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		int alignmentError = Integer.parseInt(received.trim());
    	

    		buf = new byte[256];
    		System.out.println("test: "+alignmentError);
    		DriveTrain.setThreadOutput(alignmentError);   
    		}
    }
  }
