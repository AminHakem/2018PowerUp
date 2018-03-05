package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/****
 *
 * @author niyatisriram
 *
 *         TODO: do not make running intake possible unless elevator is down and ready for a cube
 *
 */

public class Intake extends Subsystem {

  private static Intake intake;
  private WPI_TalonSRX intakeTalon;
  private Solenoid intakeSolenoid;
  private boolean pistonActivated = false;
  public double intakeSpeed = 1.0;

  private Intake() {
    // MOTOR CONTROLLERS
    intakeTalon = new WPI_TalonSRX(Constants.Intake.INTAKE_PORT);
    this.intakeSolenoid = new Solenoid(Constants.Intake.INTAKE_PISTON_PORT);
  }

  public static Intake getIntake() {
    if (intake == null) {
      intake = new Intake();
    }
    return intake;
  }

  public void setMotorValues(double motorSpeed) {
    motorSpeed = MathLib.restrictToRange(motorSpeed, -1.0, 1.0);

    intakeTalon.set(ControlMode.PercentOutput, motorSpeed);
  }

  public void stop() {
    setMotorValues(0);
  }

  @Override
  protected void initDefaultCommand() {}

  public boolean isPistonActivated() {
    return pistonActivated;
  }

  public void setPistonActivated(boolean pistonActivated) {
    this.pistonActivated = pistonActivated;
  }

  public Solenoid getIntakeSolenoid() {
    return intakeSolenoid;
  }

}
