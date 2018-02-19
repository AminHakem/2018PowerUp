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

  private static int BOX_SHIFT_X;
  private static int BOX_SHIFT_Y;
  private static double BOX_SIZE;
  private static boolean IS_VISIBLE;

  public NetworkThread() {
    try {
      socket = new DatagramSocket(10025);
    } catch (SocketException e1) {
      e1.printStackTrace();
    }
  }

  @Override
  public void run() {
    while (true) {
      // System.out.println(this.BOX_SHIFT_X + "\t" + this.BOX_SIZE + "\t" + this.IS_VISIBLE);

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

      if (!trimmed.equals("")) {
        int startIndex = trimmed.indexOf("(");
        int firstCommaIndex = trimmed.indexOf(",");
        int secondCommaIndex = trimmed.indexOf(",", firstCommaIndex + 1);
        int thirdCommaIndex = trimmed.indexOf(",", secondCommaIndex + 1);
        int endIndex = trimmed.indexOf(")");
        BOX_SHIFT_X = (int) Double.parseDouble(trimmed.substring(startIndex + 1, firstCommaIndex));
        BOX_SHIFT_Y =
            60 + (int) Double.parseDouble(trimmed.substring(firstCommaIndex + 1, secondCommaIndex));
        BOX_SIZE = Double.parseDouble(trimmed.substring(secondCommaIndex + 1, thirdCommaIndex));
        IS_VISIBLE = trimmed.substring(thirdCommaIndex + 1, endIndex).equals("true");

      }

    }
  }

  /**
   *
   *
   * @return the box shift relative to the camera (if 500 then box is not visible)
   */
  public static int getBoxX() {
    if (IS_VISIBLE)
      return BOX_SHIFT_X;
    return 0;
  }

  /**
   * Returns boxes vertical position
   *
   * @return
   */
  public static int getBoxY() {
    return BOX_SHIFT_Y;
  }

  public static double getBoxSize() {
    return BOX_SIZE;
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
