// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Timer;
public class DefautAuton extends SequentialCommandGroup {
  private DriveTrain m_drive;
  private Shooter m_shooter;
  private Limelight m_limelight;
  private Hopper m_hopper;
  private Turret m_turret;
  private Timer m_timer = new Timer();
  
  public DefautAuton(DriveTrain drivetrain, Shooter shooter, Limelight limelight, Hopper hopper, Turret turret) {
    m_drive = drivetrain;
    m_shooter = shooter;
    m_limelight = limelight;
    m_hopper = hopper;
    m_turret = turret;
  

    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.startTimer();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.shooterIdle();
    
    m_timer.alarm(new Drive(m_drive, .7), 3000);
    //m_timer.alarm(new Turn(m_drive, 90), 1000);
    m_timer.alarm3(new HopperUp(m_hopper),new ShooterHigh(m_shooter),new TurretAssist(m_turret, m_limelight) ,3000);
    m_timer.alarm(new Hold(), 10000);



    /*
    m_timer.increaseTime(1000);
    if(m_timer.alarm() == false){
      m_drive.drive(.5, .5);
    }
    m_drive.drive(0, 0);

    //m_timer.increaseTime(1000);

    //while(m_timer.alarm() == false){
    //  m_drive.turn(90.0);
    //}
    m_timer.increaseTime(3000);
    while(m_timer.alarm() == false){
      m_shooter.shootLow();
    }
    m_timer.increaseTime(500);
    while(m_timer.alarm() == false){
     // m_hopper.up();
    }
    m_shooter.set(0);
    m_hopper.set(0);
   */ 
  }
    
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_timer.reset();
    m_drive.drive(0, 0);
    m_shooter.set(0);
    m_hopper.set(0);


  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  
}
