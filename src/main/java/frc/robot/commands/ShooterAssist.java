// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class ShooterAssist extends CommandBase {
  private Shooter m_shooter;
  private Limelight m_limelight;
  double trigger;
  public ShooterAssist(Shooter shooter, Limelight limelight) {
    m_shooter = shooter;  
    m_limelight = limelight;
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_limelight.fire() == true){
      m_shooter.assistedHighShot();
    }else{
      m_limelight.turretRotationAssist();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.shooterIdle();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}