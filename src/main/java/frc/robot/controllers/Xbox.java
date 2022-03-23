// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Xbox extends SubsystemBase {
  private Joystick m_jJoystick;
  public Xbox() {
    m_jJoystick = new Joystick(Constants.joystickControllerPort);

  }
  public double getRightAxisX(){
    double lol = m_jJoystick.getRawAxis(2);
    return -lol;
  }

  public boolean getRawButtonX(int boop){
    boolean button = m_jJoystick.getRawButton(boop);
    return button;
  }
  public double getRawAxisX(int beep){
    double button = m_jJoystick.getRawAxis(beep);
    return button;
  }

  public boolean getTriggerRightAsButton(){
    double rightTrigger = m_jJoystick.getRawAxis(2);
    if (rightTrigger != 0){
      return true;
    }
    else{
      return false;
    }
  }



  public boolean getTriggerLeftAsButton(){
    double leftTrigger = m_jJoystick.getRawAxis(2);
    if (leftTrigger != 0){
      return true;
    }
    else{
      return false;
    }  
  }
  public double getRightY(){
    double rightY = m_jJoystick.getRawAxis(4);
    return rightY;
  }
  public double getRightTriggerAsAxis(){
    double rightTrigger = m_jJoystick.getRawAxis(2);
    return rightTrigger;
  }

  public double getLeftTriggerAsAxis(){
    double leftTrigger = m_jJoystick.getRawAxis(2);
    return leftTrigger;
  }

  public boolean getButtonA(){
    boolean a = m_jJoystick.getRawButton(1);
    return a;
  }
  public boolean getButtonB(){
    boolean b = m_jJoystick.getRawButton(2);
    return b;
  }
  public boolean getX(){
    boolean x = m_jJoystick.getRawButton(3);
    return x;
  }
  public boolean getY(){
    boolean y = m_jJoystick.getRawButton(4);
    return y;
  }
  public boolean bumperLeft(){
    boolean bumpLeft = m_jJoystick.getRawButton(5);
    return bumpLeft;
  }
  public boolean bumperRight(){
    boolean bumpRight = m_jJoystick.getRawButton(5);
    return bumpRight;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
