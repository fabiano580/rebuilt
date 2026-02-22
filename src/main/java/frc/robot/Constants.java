package frc.robot;

public final class Constants {
  private Constants() {}

  public static final class OI {
    public static final int DRIVER_PORT = 0;
    public static final int OPERATOR_PORT = 1;

    public static final double AXIS_DEADBAND = 0.08;
    public static final double NORMAL_LIMIT = 0.7;
    public static final double TURBO_LIMIT = 1.0;
  }

  public static final class CAN {
    public static final int RF = 6;
    public static final int RB = 5;
    public static final int LF = 2;
    public static final int LB = 3;

    public static final int INTAKE_IN = 9;
    public static final int SHOOTER_SOS = 7;
    public static final int SHOOTER = 4;

    public static final int CLIMBER = 11;

    public static final int TURRET = 0; // fix later
  }

  public static final class Limelight {
    public static final String NAME = "limelight";
  }

  public static final class Turret {
    public static final double kP = 0.03;
    public static final double kI = 0.0;
    public static final double kD = 0.002;

    public static final double OUTPUT_LIMIT = 0.5;
    public static final double TX_DEADBAND = 0.5;
    public static final double TX_TOLERANCE = 1.0;
  }

  public static final class Shooter {
    public static final double SOS_SPEED = 0.4;
    public static final double SOS_DELAY_SEC = 1.0;

    public static final double TA_FAR = 0.5;   
    public static final double TA_NEAR = 8.0; 

    public static final double SPEED_FAR = 0.75;
    public static final double SPEED_NEAR = 0.45;

    public static final double SPEED_MIN = 0.35;
    public static final double SPEED_MAX = 0.85;
  }

  public static final class Auto {
    public static final double DRIVE_SPEED = 0.5;
    public static final double DRIVE_TIME_SEC = 3.0;
    public static final double TOTAL_TIME_SEC = 15.0;

    public static final double AUTO_SHOOTER = 0.8;
    public static final double AUTO_SOS = 0.4;
  }
}