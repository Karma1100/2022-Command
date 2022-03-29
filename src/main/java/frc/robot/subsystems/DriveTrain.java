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

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;





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
  DigitalInput channalA1, channalB1, channalIndex1,channalA2, channalB2, channalIndex2;
  Encoder encoderleft, encoderright, lol;


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

    channalA1 = new DigitalInput(0);        // The interger in the () is for what DIO port the encoder is plugged into 
    channalB1 = new DigitalInput(1);        // Blue wire = Channal A, Yellow wire = Channal B, Green wire = Index Wire
    channalIndex1 = new DigitalInput(2);
    encoderleft = new Encoder(channalA1, channalB1, channalIndex1, false);

    channalA2 = new DigitalInput(3);        // The interger in the () is for what DIO port the encoder is plugged into 
    channalB2 = new DigitalInput(4);        // Blue wire = Channal A, Yellow wire = Channal B, Green wire = Index Wire
    channalIndex2 = new DigitalInput(5);
    encoderright = new Encoder(channalA2, channalB2, channalIndex2, false);

    encoderleft.setDistancePerPulse(0.000511);
    encoderright.setDistancePerPulse(0.000511);
  }
  double count;

  public boolean driveForward(){
      if(Math.abs(count) <= Constants.AutoMove){
        return true;
      }
      return false;
  }
  public void resetCount(){
    count = 0;
  }
  public void resetEncoder() {
    encoderleft.reset();
    encoderright.reset();
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
    SmartDashboard.putNumber("Encoder", encoderleft.getDistance());


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
    if(encoderleft.getDistance() >= 1.5){
      count = count - 1;
    }
     
    if(encoderleft.getDistance() <= -1.5){
      count = count + 1;
    }
    //SmartDashboard.putNumber("count", count);
  }
}
