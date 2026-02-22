package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class DefaultDriveCommand extends Command {
  private final DriveSubsystem drive;
  private final DoubleSupplier forward;
  private final DoubleSupplier rotation;
  private final BooleanSupplier turbo;

  public DefaultDriveCommand(
      DriveSubsystem drive,
      DoubleSupplier forward,
      DoubleSupplier rotation,
      BooleanSupplier turbo) {
    this.drive = drive;
    this.forward = forward;
    this.rotation = rotation;
    this.turbo = turbo;
    addRequirements(drive);
  }

  @Override
  public void execute() {
    double f = MathUtil.applyDeadband(forward.getAsDouble(), Constants.OI.AXIS_DEADBAND);
    double r = MathUtil.applyDeadband(rotation.getAsDouble(), Constants.OI.AXIS_DEADBAND);

    double limit = turbo.getAsBoolean() ? Constants.OI.TURBO_LIMIT : Constants.OI.NORMAL_LIMIT;

    drive.arcadeDrive(f * limit, r * limit, true);
  }

  @Override
  public void end(boolean interrupted) {
    drive.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}