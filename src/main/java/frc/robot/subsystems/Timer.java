// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Timer extends SubsystemBase {
  private long time;
  private long editedTime;
  private long counter;
  private long autonTimer;

  public Timer() {
    time = System.currentTimeMillis();
    autonTimer = 15000;
  }
  /**
   * This Subsystem is made so that a command can use time. 
   */
  public void reset(){
    editedTime = time;
  }
  public void update(){
    time = System.currentTimeMillis();
    editedTime = time;
  }
  public long startTimer(){
    update();
    editedTime = time;
    return editedTime;
  }
  public long currentTime(){
    editedTime = currentTime();
    return editedTime;
  }
  /**
   * increases by 1000 = 1 second
   * @param increasedTime
   */
  public void increaseTime(long increasedTime){
    counter = System.currentTimeMillis();
    counter = counter + increasedTime;
  }

  public void alarm(Command command, long timel){
    increaseTime(timel);
    while(System.currentTimeMillis() <= counter){
      command.execute();
    }
    command.isFinished();
  }
  public void alarm2(Command command, Command command2,long timel){
    increaseTime(timel);
    while(System.currentTimeMillis() <= counter){
      command.execute();
      command2.execute();
    }
    command.end(true);
    command2.end(true);
    autonTimer = autonTimer - timel;
  }
  public void alarm3(Command command, Command command2,Command command3, long timel){
    increaseTime(timel);
    while(System.currentTimeMillis() <= counter){
      command.execute();
      command2.execute();
      command3.execute();
    }
    command.end(true);
    command2.end(true);
    command3.end(true);
    autonTimer = autonTimer - timel;
  }

  public void hold(){
    while((System.currentTimeMillis() <= (System.currentTimeMillis() + autonTimer))){
      System.out.println("Waiting for it all to end");
    }
  }
  /*
  public boolean alarm(){
    if(counter == System.currentTimeMillis()){
      return true;
    }else{
      return false;
    }
  }
  public void stop(){
    time = 0;
  }
*/
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
