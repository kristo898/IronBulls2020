// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
	//CAN Bus
	public static final int leftMaster = 1;
	public static final int rightMaster = 2;
	public static final int leftSlave = 5;
	public static final int rightSlave = 7;
	public static final int FRONTINTAKE1 = 9;
	public static final int FRONTINTAKE2 = 10;
	public static final int FEEDER1 = 11;
	public static final int FEEDER2 = 12;
	public static final int BACKINTAKE1 = 3;
	public static final int BACKINTAKE2 = 8;
	public static final int SHOOTER1 = 4;
	public static final int SHOOTER2 = 6;
	//Teleop Speeds
	public static final double DRIVETRAINSPEED = 0.85;
	public static final double shootSpeed1 = 0.75;
	public static final double intake_speed1 = 0.5;
	public static final double intake_speed2 = 0.5;
	public static final double feeder_speed = 0.5;
	//Joysticks
	public static final int left_X_Axis = 0;
	public static final int left_Y_AXIS = 1;
	public static final int JOYSTICK2 = 1;
	public static final int JOYSTICK_NUMBER = 0;
	//Auto Speeds
	public static final double DRIVE_FORWARD_TIME = 3;
	public static final double AUTONOMOUS_SPEED = 0.5;
	public static final double SETPOINT_FORWARD = 0;
	//Misc.
	public static final int CAMERA_RES_X = 0;
	public static final int CAMERA_RES_Y = 0;
	public static final int RANGE_FINDER = 1;
	public static final int[] LeftEncoderPorts = new int[] {0,1};
	public static final boolean LeftEncoderReversed = false;
	public static final double EncoderDistancePerPulse = 3;
	public static final double kPDriveVel = 0;
	public static final double kvVoltSecondsPerMeter = 0;
	public static final double kaVoltSecondsSquaredPerMeter = 0;
	public static final DifferentialDriveKinematics kDriveKinematics = null;
	public static final double kMaxSpeedMetersPerSecond = 0;
	public static final double kMaxAccelerationMetersPerSecondSquared = 0;
	public static final double kRamseteB = 0;
	public static final double kRamseteZeta = 0;
	public static final double ksVolts = 0;
	public static int[] kRightEncoderPorts = new int[] {2,3};
	public static boolean kRightEncoderReversed = true;
}
