// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int joystickLeftPort = 0;
    public static final int joystickRightPort = 1;
    public static final int joystickControllerPort = 2;
    public static final int driveTrainAxis = 1;
    public static final int solinoidIntakeOutPort = 0;
    public static final int solinoidIntakeInPort = 7;

    public static final int sparkLeftOneID = 10;
    public static final int sparkLeftTwoID = 11;
    public static final int sparkRightOneID = 12;
    public static final int sparkRightTwoID = 13;
    public static final int sparkShooterOneID = 14;
    public static final int sparkShooterTwoID = 15;
    public static final int sparkTurret = 16;



    public static final int sparkHopperOneID = 19;
    public static final int sparKHopperTwoID = 20;

    public static final int sparkLiftOneID = 21;
    public static final int sparkLiftTwoID = 22;


    public static final int driveTrainCurrent = 60;
    public static final int hopperCurrentLimit = 20;
    public static final int turretCurrentLimit = 20;
    public static final int shooterCurrentLimit = 80;
    public static final int liftCurrentLimit = 60;

    public static final double hopperSpeedUp = -.5;
    public static final double hopperSpeedDown = .5;

    public static final double liftSpeedUp = -.75;
    public static final double liftSpeedDown = .5;

    public static final int boostButton = 1;

    public static final double TurretPIDFactor = .02;

    public static final double LowShotFactor = -.3;
    public static final double HighShotFactor = -.9;

    public static final double kP = 1;
    public static final double kI = .0005;
    public static final double kD = .00005;








}
