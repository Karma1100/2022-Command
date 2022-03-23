// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import frc.robot.Constants;






public class DriveTrain extends SubsystemBase {
  private final MotorController leftMotor;
  private final MotorController rightMotor;
  private final DifferentialDrive m_drive;

  CANSparkMax sparkLeftOne;
  CANSparkMax sparkLeftTwo;
  CANSparkMax sparkRightOne;
  CANSparkMax sparkRightTwo;

  ADIS16470_IMU imu;
  double m_gyro;


  public DriveTrain() {
    super();
    sparkLeftOne = new CANSparkMax(Constants.sparkLeftOneID, MotorType.kBrushless);
    sparkLeftOne.restoreFactoryDefaults();
    sparkLeftOne.setSmartCurrentLimit(Constants.driveTrainCurrent);
    sparkLeftOne.setIdleMode(IdleMode.kCoast);
    sparkLeftOne.setInverted(true);

    sparkLeftTwo = new CANSparkMax(Constants.sparkLeftTwoID, MotorType.kBrushless);
    sparkLeftTwo.restoreFactoryDefaults();
    sparkLeftTwo.setSmartCurrentLimit(Constants.driveTrainCurrent);
    sparkLeftTwo.setIdleMode(IdleMode.kCoast);
    sparkLeftTwo.setInverted(true);

    sparkRightOne = new CANSparkMax(Constants.sparkRightOneID, MotorType.kBrushless);
    sparkRightOne.restoreFactoryDefaults();
    sparkRightOne.setSmartCurrentLimit(Constants.driveTrainCurrent);
    sparkRightOne.setIdleMode(IdleMode.kCoast);

    sparkRightTwo = new CANSparkMax(Constants.sparkRightTwoID, MotorType.kBrushless);
    sparkRightTwo.restoreFactoryDefaults();
    sparkRightTwo.setSmartCurrentLimit(Constants.driveTrainCurrent);
    sparkRightTwo.setIdleMode(IdleMode.kCoast);


    leftMotor = new MotorControllerGroup(sparkLeftOne, sparkLeftTwo);
    rightMotor = new MotorControllerGroup(sparkRightOne, sparkRightTwo);

    m_drive = new DifferentialDrive(leftMotor, rightMotor);

    imu = new ADIS16470_IMU();

    m_gyro = imu.getAngle();    
  }
  /**
   * 
   * 
   * @param left Speed for left side
   * @param right Speed for right side
   */
  public void drive(double left, double right){
    m_drive.tankDrive((left)*.5, (right)*.5);
  }
  public void boostDrive(double left, double right){
    m_drive.tankDrive(left, right);
  }
  public void log(){
    SmartDashboard.putNumber("Gyro", m_gyro);
  }
  public void turn(double degree){
    double turn = m_gyro + degree;
    while(m_gyro != degree){
      if(m_gyro < turn){
        m_drive.tankDrive(-.25, .25);
      }else if(m_gyro > turn){
        m_drive.tankDrive(.25, -.25);
      }else{
        m_drive.tankDrive(0, 0);
      }
    }
  }
  
  @Override
  public void periodic() {
    log();
  }
}
