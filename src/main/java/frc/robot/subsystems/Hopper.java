// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hopper extends SubsystemBase {
  CANSparkMax sparkHopperOne;
  CANSparkMax sparkHopperTwo;
  MotorControllerGroup hopperMotors;



  /** Creates a new Hopper. */
  public Hopper() {
    super();
    sparkHopperOne = new CANSparkMax(Constants.sparkHopperOneID, MotorType.kBrushless);
    sparkHopperOne.restoreFactoryDefaults();
    sparkHopperOne.setIdleMode(IdleMode.kBrake);
    sparkHopperOne.setSmartCurrentLimit(Constants.hopperCurrentLimit);
    sparkHopperOne.setInverted(true);
    sparkHopperTwo = new CANSparkMax(Constants.sparKHopperTwoID, MotorType.kBrushless);
    sparkHopperTwo.restoreFactoryDefaults();
    sparkHopperTwo.setIdleMode(IdleMode.kBrake);
    sparkHopperTwo.setSmartCurrentLimit(Constants.hopperCurrentLimit);
    hopperMotors = new MotorControllerGroup(sparkHopperOne, sparkHopperTwo);
  }

  public void up(){
    hopperMotors.set(Constants.hopperSpeedUp);
  }
  public void down(){
    hopperMotors.set(Constants.hopperSpeedDown);
  }
  public void set(double set){
    hopperMotors.set(set);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
