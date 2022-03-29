// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Limelight;

public class TurretAssistAuto extends CommandBase {
  Turret m_Turret;
  Limelight m_Limelight;

  //double m_speed;
  double m_speed;

  public TurretAssistAuto(Turret turret, Limelight limelight) {
    m_Turret = turret;
    m_Limelight = limelight;
    addRequirements(m_Turret, m_Limelight);
  } 

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //m_Limelight.getTurretSpeed()
    while(true){
      m_Limelight.periodic();
      //System.out.println(m_Limelight.getTurretSpeed());
      m_Turret.turretTurn(m_Limelight.getTurretSpeed());
      if(m_Limelight.OnTarget() == true){
        m_Turret.turretTurn(0);
        break;
      }
      this.isFinished();
    }
    
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //m_Turret.turretTurn(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
