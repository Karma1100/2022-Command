// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
  
  CANSparkMax sparkTurret;
  double speedTurret;
  public Turret() {
    sparkTurret = new CANSparkMax(Constants.sparkTurret, MotorType.kBrushed);
    sparkTurret.restoreFactoryDefaults();
    sparkTurret.setSmartCurrentLimit(Constants.turretCurrentLimit);
    sparkTurret.setIdleMode(IdleMode.kBrake);
    sparkTurret.setInverted(true);
  
  }
  /**
   * This will turn the turret at a spicific speed
   * @param speed
   */
  public void turretTurn(double speed){
    sparkTurret.set(-speed);
    speedTurret = speed;
  }
  @Override
  public void periodic() {
    System.out.println(speedTurret);
  }
  
}
