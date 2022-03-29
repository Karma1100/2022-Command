// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.DriverStation;
// import frc.robot.subsystems.Timer;
import edu.wpi.first.wpilibj.Timer;

public class IntakeAuto extends CommandBase {
  Intake m_intake;
  Timer m_timer;
  double m_duration;

  public IntakeAuto(Intake intake, double duration) {
  m_intake = intake;
  m_timer = new Timer();
  m_duration = duration;
  addRequirements(intake);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.stop();
    m_timer.reset();
    m_timer.start();
    m_intake.setPistons(Value.kForward);
    m_intake.setVertical(-.5);
    m_intake.setRollers(1);


  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    m_intake.setVertical(-.5);
    m_intake.setRollers(1);
    //System.out.println("hello");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.setRollers(0);
    //m_intake.setVertical(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println(m_timer.get() + " " +  (m_duration <= m_timer.get()));
    return m_duration <= m_timer.get() || !DriverStation.isAutonomous();
  }
}
