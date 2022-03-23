// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.controllers;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Button;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;

public class ControllerAxisToButton extends Button {
  private final GenericHID m_stick;
  double val;
  int button;


  public ControllerAxisToButton(GenericHID stick, int axis){
    m_stick = stick;
    button = axis;
  }
  public void pressed(Command command) {
    if(get() == true){
      command.execute();
    }else{
      command.end(true);
    }

  }
  @Override
  public boolean get() {
   val =  m_stick.getRawAxis(button);
   System.out.println(val);
   if(val > .05){
    return true;
    }else{
    return false;
    }
  }
}
  












/**
  public ControllerAxisToButton(Joystick stick, int button) {
    m_stick = stick;
    m_Axis = button;
    //boolean controle = m_controller.getRawButtonX(m_button);


  }
  public boolean whenHeld(){
    boolean pressed = m_controller.getRawButtonX(m_button);
    return pressed;
  } 
  public boolean getTriggerRightAsButton(){
    double rightTrigger = m_controller.getRawAxisX(2);
    if (rightTrigger != 0){
      return true;
    }
    else{
      return false;
    }
  }

  */

