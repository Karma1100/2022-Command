// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class BoostDrive extends CommandBase {
  private final DriveTrain m_DriveTrain;
  DoubleSupplier m_left;
  DoubleSupplier m_right;  

  public BoostDrive(DriveTrain driveTrain, DoubleSupplier left, DoubleSupplier right) {
    m_DriveTrain = driveTrain;
    m_left = left;
    m_right = right;
    addRequirements(m_DriveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_DriveTrain.boostDrive(m_left.getAsDouble(), m_right.getAsDouble());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
