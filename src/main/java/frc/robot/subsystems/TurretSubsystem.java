package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.LimelightHelpers;

public class TurretSubsystem extends SubsystemBase {
  private final SparkMax turretMotor = new SparkMax(Constants.CAN.TURRET, MotorType.kBrushed);

  private final PIDController turnPID =
      new PIDController(Constants.Turret.kP, Constants.Turret.kI, Constants.Turret.kD);

  public TurretSubsystem() {
    turnPID.setSetpoint(0.0);
    turnPID.setTolerance(Constants.Turret.TX_TOLERANCE);
  }

  public boolean hasTarget() {
    return LimelightHelpers.getTV(Constants.Limelight.NAME);
  }

  public double getTx() {
    return LimelightHelpers.getTX(Constants.Limelight.NAME);
  }

  public double getTa() {
    return LimelightHelpers.getTA(Constants.Limelight.NAME);
  }

  public boolean isAligned() {
    if (!hasTarget()) return false;
    return Math.abs(getTx()) <= Constants.Turret.TX_TOLERANCE;
  }

  public void aimToTarget() {
    if (!hasTarget()) {
      turretMotor.set(0);
      turnPID.reset();
      return;
    }

    double tx = getTx();

    if (Math.abs(tx) < Constants.Turret.TX_DEADBAND) {
      turretMotor.set(0);
      return;
    }

    double output = turnPID.calculate(tx);
    output = MathUtil.clamp(output, -Constants.Turret.OUTPUT_LIMIT, Constants.Turret.OUTPUT_LIMIT);

    turretMotor.set(output);
  }

  public void stop() {
    turretMotor.set(0);
    turnPID.reset();
  }
}