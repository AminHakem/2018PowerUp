package org.usfirst.frc.team3501.robot.subsystems;

public class Climber {
  private static Climber climber;

  private Climber() {

  }

  public static Climber getClimber() {
    if (climber == null) {
      climber = new Climber();
    }
    return climber;
  }
}
