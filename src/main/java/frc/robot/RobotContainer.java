package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.chassi;
import frc.robot.commands.arcadedrive;
import static frc.robot.Constants.*;

public class RobotContainer{
  private final XboxController controller = new XboxController(CONTROLLER1_PORT);
  private final chassi m_Chassi = new chassi();
  private final arcadedrive arcadedriveCommand = new arcadedrive(m_Chassi, controller);

  public RobotContainer(){
    m_Chassi.setDefaultCommand(arcadedriveCommand);
    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return null;
  }
}