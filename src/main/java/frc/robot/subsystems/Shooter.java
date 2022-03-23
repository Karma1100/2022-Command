// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;


public class Shooter extends SubsystemBase {
  CANSparkMax sparkShooterOne;
  CANSparkMax sparkShooterTwo;
  private MotorController shootController;

  Limelight m_limelight = new Limelight();

  public Shooter() {
    super();
    sparkShooterOne = new CANSparkMax(Constants.sparkShooterOneID, MotorType.kBrushed);
    sparkShooterOne.restoreFactoryDefaults();
    sparkShooterOne.setIdleMode(IdleMode.kCoast);
    //sparkShooterOne.setSmartCurrentLimit(Constants.shooterCurrentLimit);
    sparkShooterOne.setInverted(true);
    sparkShooterTwo = new CANSparkMax(Constants.sparkShooterTwoID, MotorType.kBrushed);
    sparkShooterTwo.restoreFactoryDefaults();
    sparkShooterTwo.setIdleMode(IdleMode.kCoast);
    //sparkShooterTwo.setSmartCurrentLimit(Constants.shooterCurrentLimit);
    shootController = new MotorControllerGroup(sparkShooterOne, sparkShooterTwo);

  }
  public void shooterIdle(){
    shootController.set(-.1);
  }
  public void shootLow(){
    shootController.set(Constants.LowShotFactor);
  }

  public void shootHigh(){
    shootController.set(Constants.HighShotFactor);
  }

  //Untested Deadrecon way of making an ajustable shooter
  public void assistedHighShot(){
    double d = m_limelight.distance();
    if(d < 90){
      shootController.set(.75);
    }else if(d < 100){
      shootController.set(.8);
    }else if(d < 110){
      shootController.set(.85);
    }else if(d < 120){
      shootController.set(.9);
    }else if(d < 130){
      shootController.set(.95);
    }else{
      shootController.set(1);
    }
  }
  public void stop(){
    shootController.set(0);
  }
  public void set(double eww){
    shootController.set(eww);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
