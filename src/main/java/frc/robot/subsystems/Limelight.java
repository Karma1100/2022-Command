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

public class Limelight extends SubsystemBase {
  private NetworkTable limelightTable;
  private NetworkTableEntry tx, ty;
  private double targetx, targety;
  private double defaultValue;
  double turretSpeed;


  public Limelight() {
    limelightTable =  NetworkTableInstance.getDefault().getTable("limelight");
    tx = limelightTable.getEntry("tx");
    ty = limelightTable.getEntry("ty");

  }

  public double turretRotationAssist(){
    PIDController PIDtargetX = new PIDController(1, .0005, .00005);

    PIDtargetX.setSetpoint(0);

    double pidTargetXturretOutput = PIDtargetX.calculate(targetx);

    turretSpeed = pidTargetXturretOutput * .015;
    PIDtargetX.close(); 
    return (turretSpeed);
    
  }
  public boolean fire(){
    if(targetx >= 5 && targetx <= -5){
      return true;
    }else{
      return false;
    }
  }

  public double distance(){
    double distanceToHab = 74/(Math.tan(30+targety));
    return distanceToHab;
  }
  public void log(){
    SmartDashboard.putNumber("TX", targetx);
    SmartDashboard.putNumber("TY", targety);
    SmartDashboard.putNumber("TurretSpeed", turretSpeed);
  }
  
  @Override
  public void periodic() {
    targetx = tx.getDouble(defaultValue);
    targety = ty.getDouble(defaultValue);
    log();
  }
}
