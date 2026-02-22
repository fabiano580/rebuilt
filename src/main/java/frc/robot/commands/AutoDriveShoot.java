package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;

import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoDriveShoot extends SequentialCommandGroup {
  public AutoDriveShoot(DriveSubsystem drive, ShooterSubsystem shooter) {
    addCommands(
      new ParallelDeadlineGroup(
        new WaitCommand(Constants.Auto.DRIVE_TIME_SEC),
        new RunCommand(() -> drive.arcadeDrive(Constants.Auto.DRIVE_SPEED, 0, false), drive)
      ),
      new RunCommand(drive::stop, drive).withTimeout(0.02),

      new ParallelDeadlineGroup(
        new WaitCommand(Constants.Auto.TOTAL_TIME_SEC - Constants.Auto.DRIVE_TIME_SEC),
        new RunCommand(() -> {
          drive.arcadeDrive(0, 0, false);
          shooter.setShooter(Constants.Auto.AUTO_SHOOTER);
          shooter.setSos(Constants.Auto.AUTO_SOS);
        }, drive, shooter)
      ),
      new RunCommand(() -> {
        drive.stop();
        shooter.stop();
      }, drive, shooter).withTimeout(0.02)
    );
  }
}