package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  private final SparkMax rf = new SparkMax(Constants.CAN.RF, MotorType.kBrushed);
  private final SparkMax rb = new SparkMax(Constants.CAN.RB, MotorType.kBrushed);
  private final SparkMax lf = new SparkMax(Constants.CAN.LF, MotorType.kBrushed);
  private final SparkMax lb = new SparkMax(Constants.CAN.LB, MotorType.kBrushed);

  private final MotorControllerGroup right = new MotorControllerGroup(rf, rb);
  private final MotorControllerGroup left = new MotorControllerGroup(lf, lb);
  private final DifferentialDrive chassi = new DifferentialDrive(left, right);

  public DriveSubsystem() {
    right.setInverted(true);

    SparkMaxConfig driveCfg = new SparkMaxConfig();
    driveCfg.idleMode(IdleMode.kCoast);
    driveCfg.inverted(false);

    rf.configure(driveCfg, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    rb.configure(driveCfg, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    lf.configure(driveCfg, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    lb.configure(driveCfg, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);

    stop();
  }

  public void arcadeDrive(double forward, double rotation, boolean squareInputs) {
    chassi.arcadeDrive(forward, rotation, squareInputs);
  }

  public void stop() {
    chassi.stopMotor();
    rf.set(0); rb.set(0); lf.set(0); lb.set(0);
  }
}