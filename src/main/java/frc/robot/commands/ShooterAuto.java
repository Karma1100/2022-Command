// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Timer;

public class ShooterAuto extends CommandBase {
  Shooter m_shooter;
  Timer m_timer;
  Hopper m_hoper;
  public ShooterAuto(Shooter shoot, Timer time, Hopper hopper) {
  m_shooter = shoot;
  m_timer = time;
  m_hoper = hopper;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_timer.alarm2(new ShooterHighAuto(m_shooter), new HopperUp(m_hoper), 3000);
    this.isFinished();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    m_shooter.set(0);
    return true;
  }
}
