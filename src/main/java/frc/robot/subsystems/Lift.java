// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Lift extends SubsystemBase {
  CANSparkMax sparkLiftOne, sparkLiftTwo;
  MotorControllerGroup liftController;
  public Lift() {
    sparkLiftOne = new CANSparkMax(Constants.sparkLiftOneID, MotorType.kBrushless);
    sparkLiftOne.restoreFactoryDefaults();
    sparkLiftOne.setIdleMode(IdleMode.kBrake);
    sparkLiftOne.setSmartCurrentLimit(Constants.liftCurrentLimit);
    sparkLiftOne.setInverted(true);
    sparkLiftTwo = new CANSparkMax(Constants.sparkLiftTwoID, MotorType.kBrushless);
    sparkLiftTwo.restoreFactoryDefaults();
    sparkLiftTwo.setIdleMode(IdleMode.kBrake);
    sparkLiftTwo.setSmartCurrentLimit(Constants.liftCurrentLimit);

    liftController = new MotorControllerGroup(sparkLiftOne, sparkLiftTwo);
  }
  public void liftUp(){
    liftController.set(Constants.liftSpeedUp);
  }
  public void liftDown(){
    liftController.set(Constants.liftSpeedDown);
  }
  public void liftStop(){
    liftController.set(0);
  }
  public void liftSet(double speed){
    liftController.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
