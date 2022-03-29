// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Timer;

public class HopperAuto extends CommandBase {
  Hopper m_hoper;
  Timer m_timer;
  public HopperAuto(Hopper hoper, Timer timer) {
m_hoper = hoper;
m_timer = timer;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_timer.alarm(new HopperUp(m_hoper), 3000);
    this.isFinished();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    m_hoper.set(0);
    return true;
  }
}
