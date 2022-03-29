// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;

public class Targeting extends CommandBase {
  Limelight m_limelight;
  Turret m_turret;
  public Targeting(Limelight limelight, Turret turret) {
    m_limelight = limelight;
    m_turret = turret;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while(m_limelight.OnTarget2() == false && DriverStation.isAutonomous() == true){
      m_limelight.periodic();

      m_turret.turretTurn(.23);
      if(m_limelight.OnTarget2() == true){
        m_turret.turretTurn(0);
        break;
      }
    }
    this.isFinished();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    m_turret.turretTurn(0);
    return true;
  }
}
