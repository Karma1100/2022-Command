// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class Intake extends SubsystemBase {
  CANSparkMax sparkIntakeVertical;
  CANSparkMax sparkIntakeIn;
  Compressor pcmCompressor;
  DoubleSolenoid intake;


  
  public Intake() {
    sparkIntakeVertical = new CANSparkMax(Constants.sparkIntakeVertial, MotorType.kBrushed);
    sparkIntakeIn = new CANSparkMax(Constants.sparkIntakeIn, MotorType.kBrushed);
    pcmCompressor = new Compressor(PneumaticsModuleType.CTREPCM);
    pcmCompressor.enableDigital();
    pcmCompressor.enabled();
    //turretencoder = sparkTurret.getAlternateEncoder(encoder);
    intake = new DoubleSolenoid(0, PneumaticsModuleType.CTREPCM, 7, 0);
  }
  public void setVertical(double speed){
    sparkIntakeVertical.set(speed);
  }
  public void setRollers(double speed){
    sparkIntakeIn.set(speed);
  }
  public void setPistons(Value value){
    intake.set(value);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
