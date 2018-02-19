package org.usfirst.frc.team3501.robot.utils;

import org.usfirst.frc.team3501.robot.MathLib;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDController {

  private double pConst;
  private double iConst;
  private double dConst;
  private double setPoint;
  private double previousError;
  private double errorSum;
  private double errorIncrement;
  private double iRange;
  private double doneRange;
  private boolean firstCycle;
  private double maxOutput;
  private int minDoneCycleCount;
  private int doneCycleCount;

  public PIDController() {
    this(0.0, 0.0, 0.0, 0.0, 0.0);
  }

  public PIDController(double p, double i, double d, double eps, double iRange) {
    this.pConst = p;
    this.iConst = i;
    this.dConst = d;
    this.setPoint = 0.0;

    this.iRange = iRange;
    this.errorIncrement = 1.0;

    this.doneRange = eps;
    this.doneCycleCount = 0;
    this.minDoneCycleCount = 5;

    this.firstCycle = true;

    this.maxOutput = 1.0;
  }

  public PIDController(double p, double i, double d, double eps) {
    this(p, i, d, eps, eps * 0.8);
  }

  public PIDController(double p, double i, double d) {
    this(p, i, d, 1.0);
  }

  public void setConstants(double p, double i, double d) {
    this.pConst = p;
    this.iConst = i;
    this.dConst = d;
  }

  public void setConstants(double p, double i, double d, double eps, double iRange) {
    this.pConst = p;
    this.iConst = i;
    this.dConst = d;
    this.doneRange = eps; // range of error
    this.iRange = iRange;
  }

  public void setConstants(double p, double i, double d, double eps) {
    setConstants(p, i, d, eps, eps * 0.8);
  }

  public void setDoneRange(double range) {
    this.doneRange = range;
  }

  public void setIRange(double eps) {
    this.iRange = eps;
  }

  public void setSetPoint(double val) {
    this.setPoint = val;
  }

  public void setMaxOutput(double max) {
    if (max < 0.0) {
      this.maxOutput = 0.0;
    } else if (max > 1.0) {
      this.maxOutput = 1.0;
    } else {
      this.maxOutput = max;
    }
  }

  public void setMinDoneCycles(int num) {
    this.minDoneCycleCount = num;
  }

  public void resetErrorSum() {
    this.errorSum = 0.0;
  }

  public double getDesiredVal() {
    return this.setPoint;
  }

  public double calcPID(double current) {
    return calcPIDError(this.setPoint - current);
  }

  public double calcPIDError(double error) {
    SmartDashboard.putNumber("error", error);
    double pVal = 0.0;
    double iVal = 0.0;
    double dVal = 0.0;

    if (this.firstCycle) {
      this.previousError = error;
      this.firstCycle = false;
    }

    pVal = this.pConst * error;

    // + error outside of acceptable range which might distort the sum calc
    if (error > this.iRange) {
      // check if error sum was in the wrong direction
      if (this.errorSum < 0.0) {
        this.errorSum = 0.0;
      }
      // only allow up to the max contribution per cycle
      this.errorSum += Math.min(error, this.errorIncrement);
    } // - error outside of acceptable range
    else if (error < -1.0 * this.iRange) {
      // error sum was in the wrong direction
      if (this.errorSum > 0.0) {
        this.errorSum = 0.0;
      }
      // add either the full error or the max allowable amount to sum
      this.errorSum += Math.max(error, -1.0 * this.errorIncrement);
    }

    // i contribution (final) calculation
    iVal = this.iConst * this.errorSum;

    // /////D Calc///////
    double deriv = error - this.previousError;
    dVal = this.dConst * deriv;

    // overal PID calc
    SmartDashboard.putNumber("pval", pVal);
    SmartDashboard.putNumber("ival", iVal);
    SmartDashboard.putNumber("dval", dVal);
    double output = pVal + iVal + dVal;

    // limit the output
    output = MathLib.limitValue(output, this.maxOutput);

    // store current value as previous for next cycle
    this.previousError = error;
    return output;
  }

  public boolean isDone() {
    double currError = Math.abs(this.previousError);

    // close enough to target
    if (currError <= this.doneRange) {
      this.doneCycleCount++;
    }
    // not close enough to target
    else {
      this.doneCycleCount = 0;
    }

    return this.doneCycleCount >= this.minDoneCycleCount;
  }
}
