// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class StickJoy extends SubsystemBase {
  private Joystick m_jJoystick;
  public StickJoy(int number) {
  m_jJoystick = new Joystick(number);

  }

  public boolean getMaxamumOverDrive(){ //lol
    boolean rightTrigger = m_jJoystick.getRawButton(1);
    return rightTrigger;
  }

  public double getAxisX(){
    double axisX = m_jJoystick.getRawAxis(0);
    return axisX;

  }
  public double getAxisY(){
    double axisY = m_jJoystick.getRawAxis(1);
    return axisY;
  }
  public boolean getRawButton(int button){
    boolean rawButton = m_jJoystick.getRawButton(button);
    return rawButton;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
