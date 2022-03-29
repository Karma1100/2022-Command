// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxLimitSwitch.Type;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.Limelight;

public class Turret extends SubsystemBase {
  
  CANSparkMax sparkTurret;
  double speedTurret;
  Limelight m_limelight;

  public Turret(Limelight limelight) {
    super();
    this.m_limelight = limelight;
    sparkTurret = new CANSparkMax(Constants.sparkTurret, MotorType.kBrushed);
    sparkTurret.restoreFactoryDefaults();
    sparkTurret.setSmartCurrentLimit(Constants.turretCurrentLimit);
    sparkTurret.setIdleMode(IdleMode.kBrake);
    sparkTurret.setInverted(true);
    // limelight.turretRotationAssist();
  }
  /**
   * This will turn the turret at a spicific speed
   * @param speed
   */
  public void turretTurn(double speed){
    
    sparkTurret.set(-speed);
    //sparkTurret.getForwardLimitSwitch(Type.kNormallyOpen);
  }
  
  @Override
  public void periodic() {
    //System.out.println(speedTurret);
    
  }

  
}
