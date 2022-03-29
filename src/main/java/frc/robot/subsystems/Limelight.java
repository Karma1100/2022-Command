// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import frc.robot.subsystems.Turret;

public class Limelight extends SubsystemBase {
  private NetworkTable limelightTable;
  private NetworkTableEntry tx, ty;
  private double targetx, targety;
  private double defaultValue;
  final PIDController PIDtargetX;
  double turretSpeed;

  public Limelight() {
    limelightTable =  NetworkTableInstance.getDefault().getTable("limelight");
    tx = limelightTable.getEntry("tx");
    ty = limelightTable.getEntry("ty");
    PIDtargetX = new PIDController(1, .0005, .00005);
    PIDtargetX.setSetpoint(0);

  }

  public void turretRotationAssist(){
    double pidTargetXturretOutput = PIDtargetX.calculate(targetx);

    turretSpeed = pidTargetXturretOutput * .015;
     
    //return (turretSpeed);
    
  }
  public boolean OnTarget(){
    if(targetx <= 3 && targetx >= -3 && targetx != 0){
      return true;
    }else{
      return false;
    }
  }
  public boolean OnTarget2(){
    if(targetx <= 10 && targetx >= -10 && targetx != 0){
      return true;
    }else{
      return false;
    }
  }

  public double getTurretSpeed(){
      return turretSpeed;
  }
  
  public double deadreckonTurret(){
    if(targetx > 15){
      return -.25;
    }else if(targetx > 10){
      return -.15;
    }else if(targetx > 5){
      return -.010;
    }else if(targetx > 3){
      return -.05;
    }else if(targetx < -15){
      return .25;
    }else if(targetx < -10){
      return .15;
    }else if(targetx < -5){
      return .10;
    }else if(targetx < -3){
      return .05;
    }{
      return 0;
    }
  }

  public double distance(){
    double distanceToHab = 74/(Math.tan(30+targety));
    return distanceToHab;
  }
  public void log(){
    SmartDashboard.putNumber("TX", targetx);
    SmartDashboard.putNumber("TY", targety);
   // SmartDashboard.putNumber("TurretSpeed", turretSpeed);
  }
  
  @Override
  public void periodic() {
    targetx = tx.getDouble(defaultValue);
    targety = ty.getDouble(defaultValue);
    this.turretRotationAssist();
    log();
    this.OnTarget();
  }
}
