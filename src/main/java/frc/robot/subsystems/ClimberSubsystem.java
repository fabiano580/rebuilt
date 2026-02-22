package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
  private final SparkMax climber = new SparkMax(Constants.CAN.CLIMBER, MotorType.kBrushed);

  public void set(double speed) {
    climber.set(speed);
  }

  public void stop() {
    climber.set(0);
  }
}