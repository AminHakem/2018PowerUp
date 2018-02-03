package org.usfirst.frc.team3501.robot.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
/**
 * Thread which will constantly receive alignment values from RaspberryPi and update variable in
 * DriveTrain
 *
 * @author Amin Hakem
 */
import org.usfirst.frc.team3501.robot.subsystems.DriveTrain;

public class NetworkThread extends Thread {
  private DatagramSocket socket;
  private byte[] buf = new byte[256];
  private DriveTrain driveTrain = DriveTrain.getDriveTrain();;

  public NetworkThread() {
    try {
      socket = new DatagramSocket(1025);
    } catch (SocketException e1) {
      e1.printStackTrace();
    }
  }

  @Override
  public void run() {
    while (true) {
      DatagramPacket packet = new DatagramPacket(buf, buf.length);
      try {
        socket.receive(packet);
      } catch (IOException e) {
        System.out.println("ouch. you didn't get anything!");
      }

      InetAddress address = packet.getAddress();
      int port = packet.getPort();
      packet = new DatagramPacket(buf, buf.length, address, port);

      String received = "   ";
      try {
        received = new String(packet.getData(), "UTF-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      buf = new byte[256];

      int alignmentError = Integer.parseInt(received.trim());
      driveTrain.setThreadOutput(alignmentError);
    }
  }
}
