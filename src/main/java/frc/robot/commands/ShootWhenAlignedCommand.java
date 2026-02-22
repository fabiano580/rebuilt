package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class ShootWhenAlignedCommand extends Command {
  private final TurretSubsystem turret;
  private final ShooterSubsystem shooter;

  private boolean alignedLatch = false;
  private double alignedStartTime = 0;

  public ShootWhenAlignedCommand(TurretSubsystem turret, ShooterSubsystem shooter) {
    this.turret = turret;
    this.shooter = shooter;
    addRequirements(turret, shooter);
  }

  @Override
  public void initialize() {
    alignedLatch = false;
    alignedStartTime = 0;
    shooter.stop();
  }

  private double shooterSpeedFromTa(double ta) {
    double t = (ta - Constants.Shooter.TA_FAR) / (Constants.Shooter.TA_NEAR - Constants.Shooter.TA_FAR);
    t = MathUtil.clamp(t, 0.0, 1.0);

    double speed = Constants.Shooter.SPEED_FAR + (Constants.Shooter.SPEED_NEAR - Constants.Shooter.SPEED_FAR) * t;
    return MathUtil.clamp(speed, Constants.Shooter.SPEED_MIN, Constants.Shooter.SPEED_MAX);
  }

  @Override
  public void execute() {
    turret.aimToTarget();

    if (!turret.hasTarget() || !turret.isAligned()) {
      shooter.stop();
      alignedLatch = false;
      alignedStartTime = 0;
      return;
    }

    double ta = turret.getTa();
    double shooterSpeed = shooterSpeedFromTa(ta);

    shooter.setShooter(shooterSpeed);

    if (!alignedLatch) {
      alignedLatch = true;
      alignedStartTime = Timer.getFPGATimestamp();
      shooter.setSos(0);
      return;
    }

    if (Timer.getFPGATimestamp() - alignedStartTime >= Constants.Shooter.SOS_DELAY_SEC) {
      shooter.setSos(Constants.Shooter.SOS_SPEED);
    } else {
      shooter.setSos(0);
    }
  }

  @Override
  public void end(boolean interrupted) {
    turret.stop();
    shooter.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}