// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Drive;
import frc.robot.subsystems.DriveTrain;

public class Auto2 extends SequentialCommandGroup {
  /** Creates a new Auto2. */
  public Auto2(DriveTrain m_drive) {
    addCommands(
      new Drive(m_drive, 2)



    );

  }

}
