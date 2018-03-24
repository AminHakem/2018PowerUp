package org.usfirst.frc.team3501.robot.subsystems;

import org.usfirst.frc.team3501.robot.Constants;
import org.usfirst.frc.team3501.robot.MathLib;
import org.usfirst.frc.team3501.robot.commands.intake.MoveIntakeToTargetConstant;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Servo;
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
  public static final double BOTTOM_INTAKE_ANGLE = 70.0;
  public static final double TOP_INTAKE_ANGLE = 1.0;
  public static final double MIDDLE_INTAKE_ANGLE = 30.0;
  public static double INTAKE_P = 0.01, INTAKE_I = 0.0,
      INTAKE_D = -0.001;
  public boolean raisIntake = false;
  private static Intake intake;
  private WPI_TalonSRX intakeTalon;
  private WPI_TalonSRX intakeAngleMotor; //controls angle of intake
  private WPI_TalonSRX intakeAngleEncoderTalon; //reads angle of intake
  private final SensorCollection intakeEncoder;
  private Solenoid intakeSolenoid;
  private Solenoid intakeSolenoidTwo;
  private boolean pistonActivated = false;
  public double intakeSpeed = 1.0;
  public double outtakeSpeed = 0.5;
  public double dropSpeed = .75;
  public double intakeTarget = 0;

  private Intake() {
    // MOTOR CONTROLLERS
    this.intakeTalon = new WPI_TalonSRX(Constants.Intake.INTAKE_PORT);
    this.intakeSolenoid = new Solenoid(Constants.Intake.INTAKE_PISTON_PORT);
    this.intakeSolenoidTwo = new Solenoid(Constants.Intake.INTAKE_PISTON_PORT_TWO);
    this.intakeAngleMotor = new WPI_TalonSRX(Constants.Intake.INTAKE_ANGLE_PORT);
    this.intakeAngleEncoderTalon = new WPI_TalonSRX(Constants.Intake.INTAKE_ANGLE_ENCODER_PORT);
    this.intakeEncoder = intakeAngleEncoderTalon.getSensorCollection();
    this.intakeAngleMotor.setNeutralMode(NeutralMode.Brake);
    this.getIntakeSolenoid().set(pistonActivated);
    this.getIntakeSolenoidTwo().set(pistonActivated);
    this.intakeAngleMotor.set(0);
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
  protected void initDefaultCommand() {
    this.setDefaultCommand(new MoveIntakeToTargetConstant());
  }

  public boolean isPistonActivated() {
    return pistonActivated;
  }

  public void setPistonActivated(boolean pistonActivated) {
    this.pistonActivated = pistonActivated;
  }

  public Solenoid getIntakeSolenoid() {
    return intakeSolenoid;
  }

  public WPI_TalonSRX getIntakeAngleMotor() {
    return this.intakeAngleMotor;
  }
  public double getEncoderPulses() {
    return this.intakeEncoder.getQuadraturePosition()/2500;
  }
  public void setAngleMotorValue(double motorVal) {
    motorVal = MathLib.restrictToRange(motorVal, -1.0, 1.0);
      this.intakeAngleMotor.set(ControlMode.PercentOutput, motorVal);
    } 
  public void resetEncoder() {
    intakeEncoder.setQuadraturePosition(0, 3);
  }

  public Solenoid getIntakeSolenoidTwo() {
    return this.intakeSolenoidTwo;
  }

  public double getIntakeTarget() {
    return intakeTarget;
  }

  public void setIntakeTarget(double intakeTarget) {
    this.intakeTarget = intakeTarget;
  }

  public boolean isRaisIntake() {
    return raisIntake;
  }

  public void setRaisIntake(boolean raisIntake) {
    this.raisIntake = raisIntake;
  }
  
}
