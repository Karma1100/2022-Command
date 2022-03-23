// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.HopperDown;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.BoostDrive;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.HopperUp;
import frc.robot.commands.LiftDown;
import frc.robot.commands.LiftUp;
import frc.robot.commands.Run;
import frc.robot.commands.ShootLow;
import frc.robot.commands.ShooterAssist;
import frc.robot.commands.ShooterHigh;
import frc.robot.commands.ShooterIdle;
import frc.robot.commands.TurretAssist;
import frc.robot.controllers.ControllerAxisToButton;
import frc.robot.controllers.StickJoy;
import frc.robot.controllers.Xbox;
import frc.robot.commands.DefaultTurret;
import frc.robot.commands.DefautAuton;
 

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_DriveTrain = new DriveTrain();
  private final Turret m_turret = new Turret();
  private final Shooter m_shooter = new Shooter();
  private final Hopper m_hopper = new Hopper();
  private final Limelight m_limelight = new Limelight();
  private final Lift m_lift = new Lift();

  

 //controllers
  private Joystick leftJoy = new Joystick(Constants.joystickLeftPort);
  private Joystick rightJoy = new Joystick(Constants.joystickRightPort);
  private Joystick controller = new Joystick(Constants.joystickControllerPort);

//custom made controller classes, experemental dont use for comp unless tested
  private StickJoy m_left = new StickJoy(Constants.joystickLeftPort);
  private StickJoy m_right = new StickJoy(Constants.joystickRightPort);
  private Xbox m_controller = new Xbox();
  
  double turretwoosh = controller.getRawAxis(2);

  private final CommandBase m_autonomousCommand =
      new DefautAuton(m_DriveTrain, m_shooter, m_limelight, m_hopper, m_turret);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    //Inoder to use any kind of Axis you must use a DoubleSupplier and then pass it through
    //getY is a method in the Joystick class that gets the values from the specified axis. This is the same as GetRawAxis()
    m_turret.setDefaultCommand(new DefaultTurret(m_turret, m_controller::getRightAxisX));

    m_DriveTrain.setDefaultCommand(new DefaultDrive(m_DriveTrain, m_left::getAxisY, m_right::getAxisY));
    //m_shooter.setDefaultCommand(new ShooterIdle(m_shooter));

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //Built in class that allows me to pull buttons
    final JoystickButton hopperUp = new JoystickButton(controller, 5);
    final JoystickButton hopperDown = new JoystickButton(controller, 6);
    final JoystickButton HighShot =  new JoystickButton(controller, 7);  //I have to change these
    final JoystickButton LowShot = new JoystickButton(controller, 8);
    final JoystickButton liftUp = new JoystickButton(controller, 1);
    final JoystickButton liftDown = new JoystickButton(controller, 4);

    final JoystickButton boost = new JoystickButton(leftJoy, 1);
    final JoystickButton boost2 = new JoystickButton(rightJoy, 1);

    final JoystickButton turretAssist = new JoystickButton(controller, 12);
    final JoystickButton shooterAssist = new JoystickButton(controller, 10);
    
    // hopper up is the custom command and .whenHeld is from the Button class. It needs a boolean to activate 
    //working
    hopperUp.whenHeld(new HopperUp(m_hopper));
    //working
    hopperDown.whenHeld(new HopperDown(m_hopper));

    //working
    HighShot.whenHeld(new ShooterHigh(m_shooter));
    LowShot.whenHeld(new ShootLow(m_shooter));
    //Working
    boost.whenHeld(new BoostDrive(m_DriveTrain, m_left::getAxisY, m_right::getAxisY));
    boost2.whenHeld(new BoostDrive(m_DriveTrain,m_left::getAxisY, m_right::getAxisY));

    //untested
    liftUp.whenHeld(new LiftUp(m_lift));
    liftDown.whenHeld(new LiftDown(m_lift));
    //untested
    turretAssist.whenHeld(new TurretAssist(m_turret, m_limelight));
    //untested
    shooterAssist.whenHeld(new ShooterAssist(m_shooter, m_limelight));
    


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autonomousCommand;
  }
}
