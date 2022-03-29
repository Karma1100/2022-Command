// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax.IdleMode;
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
  boolean forward = true;


  
  public Intake() {
    sparkIntakeVertical = new CANSparkMax(Constants.sparkIntakeVertial, MotorType.kBrushed);
    sparkIntakeVertical.restoreFactoryDefaults();
    sparkIntakeVertical.setIdleMode(IdleMode.kBrake);
    sparkIntakeVertical.setSmartCurrentLimit(Constants.intakeCurrentLimit);
    sparkIntakeIn = new CANSparkMax(Constants.sparkIntakeIn, MotorType.kBrushed);
    sparkIntakeIn.restoreFactoryDefaults();
    sparkIntakeIn.setIdleMode(IdleMode.kCoast);
    sparkIntakeIn.setSmartCurrentLimit(Constants.liftCurrentLimit);

    pcmCompressor = new Compressor(PneumaticsModuleType.REVPH);
    pcmCompressor.enableDigital();
    pcmCompressor.enabled();
    intake = new DoubleSolenoid(1, PneumaticsModuleType.REVPH, 14, 15);
  }
  public void setVertical(double speed){
    sparkIntakeVertical.set(speed);
    System.out.println("vertical " + speed);
  }
  public void setRollers(double speed){
    sparkIntakeIn.set(speed);
  }
  public void setPistons(Value value){
    intake.set(value);
    if(value == Value.kForward){
        forward = true;
    }else{
      forward = false;
    }
  }
  public void swtichPistons(){
    if(forward == true){
      intake.set(Value.kReverse);
      forward = false;
    }else{
      intake.set(Value.kForward);
      forward = true;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
