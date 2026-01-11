package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class chassi extends SubsystemBase{

    private final SparkMax leftfront;
    private final SparkMax leftback;
    private final SparkMax rightfront;
    private final SparkMax rightback;

    private final MotorControllerGroup leftgroup;
    private final MotorControllerGroup rightgroup;

    private final DifferentialDrive drive;

    private final Pigeon2 pg;

    public chassi() {
        leftfront = new SparkMax(LF, MotorType.kBrushed);
        leftback = new SparkMax(LB, MotorType.kBrushed);
        rightfront = new SparkMax (RF, MotorType.kBrushed);
        rightback = new SparkMax (RB, MotorType.kBrushed);

        leftgroup = new MotorControllerGroup(leftback, leftfront);
        rightgroup = new MotorControllerGroup(rightback, rightfront);

        drive = new DifferentialDrive(leftgroup, rightgroup);

        pg = new Pigeon2(PG_VALUE);

    rightgroup.setInverted(true);
    }

    public void arcadedrive (double forward, double rotation){;
        drive.arcadeDrive(forward, rotation);
    }
}
