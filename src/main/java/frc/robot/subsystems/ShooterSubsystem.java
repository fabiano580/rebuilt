package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private final SparkMax shooter = new SparkMax(Constants.CAN.SHOOTER, MotorType.kBrushless);
  private final SparkMax shooterSos = new SparkMax(Constants.CAN.SHOOTER_SOS, MotorType.kBrushed);

  public void setShooter(double speed) {
    shooter.set(speed);
  }

  public void setSos(double speed) {
    shooterSos.set(speed);
  }

  public void stop() {
    shooter.set(0);
    shooterSos.set(0);
  }
}