package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.commands.AutoDriveShoot;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.AimTurretCommand;
import frc.robot.commands.ShootWhenAlignedCommand;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class RobotContainer {
  private final DriveSubsystem drive = new DriveSubsystem();
  private final IntakeSubsystem intake = new IntakeSubsystem();
  private final ClimberSubsystem climber = new ClimberSubsystem();
  private final ShooterSubsystem shooter = new ShooterSubsystem();
  private final TurretSubsystem turret = new TurretSubsystem();

  private final XboxController controller1 = new XboxController(Constants.OI.DRIVER_PORT);
  private final XboxController controller2 = new XboxController(Constants.OI.OPERATOR_PORT);

  private final Command autoCommand = new AutoDriveShoot(drive, shooter);

  public RobotContainer() {
    drive.setDefaultCommand(
      new DefaultDriveCommand(
        drive,
        () -> controller1.getLeftY(),
        () -> controller1.getRightX(),
        () -> controller1.getLeftBumperButton()
      )
    );

    intake.setDefaultCommand(
      new RunCommand(() -> {
        double v = controller2.getRightTriggerAxis() * 0.5;
        if (v < Constants.OI.AXIS_DEADBAND) v = 0.0;
        intake.set(v);
      }, intake)
    );

    climber.setDefaultCommand(
      new RunCommand(() -> climber.set(controller2.getAButton() ? 1.0 : 0.0), climber)
    );

    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(controller2, XboxController.Button.kB.value)
      .whileTrue(new AimTurretCommand(turret));

    new JoystickButton(controller2, XboxController.Button.kLeftBumper.value)
      .whileTrue(new ShootWhenAlignedCommand(turret, shooter));
  }

  public Command getAutonomousCommand() {
    return autoCommand;
  }

  public void stopAll() {
    drive.stop();
    intake.stop();
    climber.stop();
    shooter.stop();
    turret.stop();
  }
}