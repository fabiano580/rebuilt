package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class chassi extends SubsystemBase{

    private final SparkMax leftfront = new SparkMax(Constants.lf, MotorType.kBrushed);
    private final SparkMax leftback = new SparkMax(Constants.lb, MotorType.kBrushed);
    private final SparkMax rightfront = new SparkMax (Constants.rf, MotorType.kBrushed);
    private final SparkMax rightback = new SparkMax (Constants.rb, MotorType.kBrushed);

    private final MotorControllerGroup leftgroup = new MotorControllerGroup(leftback, leftfront);
    private final MotorControllerGroup rightgroup = new MotorControllerGroup(rightback, rightfront);

    private final DifferentialDrive drive = new DifferentialDrive(leftgroup, rightgroup);

    private final Pigeon2 pg = new Pigeon2(Constants.pg_value);

    public chassi() {
    rightgroup.setInverted(true);
    }

    public void arcadedrive (double forward, double rotation){;
        drive.arcadeDrive(forward, rotation);
    }
}
