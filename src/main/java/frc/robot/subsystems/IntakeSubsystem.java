package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  private final SparkMax intakeIn = new SparkMax(Constants.CAN.INTAKE_IN, MotorType.kBrushless);

  public void set(double speed) {
    intakeIn.set(speed);
  }

  public void stop() {
    intakeIn.set(0);
  }
}