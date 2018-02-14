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
  private DriveTrain driveTrain = DriveTrain.getDriveTrain();

  private static int BOX_SHIFT;
  private static boolean IS_VISIBLE;

  public NetworkThread() {
    try {
      socket = new DatagramSocket(1025);
    } catch (SocketException e1) {
      e1.printStackTrace();
    }
  }

  @Override
  public void run() {
    System.out.println("1");
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
      String trimmed = received.trim();
      int alignmentError = 0;
      if (!trimmed.equals("")) {
        alignmentError = Integer.parseInt(received.trim());
        if (alignmentError != 500) {
          NetworkThread.BOX_SHIFT = alignmentError;
          IS_VISIBLE = true;
        } else {
          IS_VISIBLE = false;
        }
      }


    }
  }

  /**
   *
   *
   * @return the box shift relative to the camera (if 500 then box is not visible)
   */
  public static int getBoxPos() {
    return BOX_SHIFT;
  }

  /**
   * If box is visible returns true
   *
   * @return
   */
  public static boolean isBoxVisible() {
    return IS_VISIBLE;
  }
}
