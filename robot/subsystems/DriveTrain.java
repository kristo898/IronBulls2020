// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class DriveTrain extends SubsystemBase {
  WPI_TalonSRX rightMaster;
  WPI_VictorSPX rightSlave;
  WPI_TalonSRX leftMaster;
  WPI_VictorSPX leftSlave;
  // The motors on the left side of the drive.
  private final SpeedControllerGroup leftMotors =
    new SpeedControllerGroup(
      new WPI_TalonSRX(Constants.leftMaster), 
      new WPI_VictorSPX(Constants.leftSlave));
  // The motors on the right side of the drive.
  private final SpeedControllerGroup rightMotors =
    new SpeedControllerGroup(new WPI_TalonSRX(Constants.rightMaster), new WPI_VictorSPX(Constants.rightSlave));
  // The robot's drive
  private final DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);
  // The left-side drive encoder
  private final Encoder leftEncoder =
    new Encoder(
      Constants.LeftEncoderPorts[0], 
      Constants.LeftEncoderPorts[1],
      Constants.LeftEncoderReversed);
    // The right-side drive encoder
    private final Encoder rightEncoder =
    new Encoder(
      Constants.kRightEncoderPorts[0], 
      Constants.kRightEncoderPorts[1],
      Constants.kRightEncoderReversed);
  // The gyro sensor
  private final AHRS gyro = new AHRS(SPI.Port.kMXP);
  // Odometry class for tracking robot pose
  private final DifferentialDriveOdometry odometry;
  
  
  /** Creates a new RamseteCommand. */
  public DriveTrain() {
    rightMaster.configFactoryDefault();
    leftMaster.configFactoryDefault();
    leftSlave.configFactoryDefault();
    rightSlave.configFactoryDefault();
    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);
    rightMaster.setInverted(false);
    leftMaster.setInverted(true);
    leftSlave.setInverted(true);
    rightSlave.setInverted(false);
    // Sets the distance per pulse for the encoders
    leftEncoder.setDistancePerPulse(Constants.EncoderDistancePerPulse);
    rightEncoder.setDistancePerPulse(Constants.EncoderDistancePerPulse);

    resetEncoders();
    odometry = new DifferentialDriveOdometry(gyro.getRotation2d());
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Update the odometry in the periodic block
    odometry.update(
      gyro.getRotation2d(), leftEncoder.getDistance(), rightEncoder.getDistance());
  }
    /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  /**
   * Returns the current wheel speeds of the robot.
   *
   * @return The current wheel speeds.
   */
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(leftEncoder.getRate(), rightEncoder.getRate());
  }

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    odometry.resetPosition(pose, gyro.getRotation2d());
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    drive.arcadeDrive(fwd, rot);
  }

  /**
   * Controls the left and right sides of the drive directly with voltages.
   *
   * @param leftVolts  the commanded left output
   * @param rightVolts the commanded right output
   */
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    leftMotors.setVoltage(leftVolts);
    rightMotors.setVoltage(-rightVolts);
    drive.feed();
  }

  /**
   * Resets the drive encoders to currently read a position of 0.
   */
  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  /**
   * Gets the average distance of the two encoders.
   *
   * @return the average of the two encoder readings
   */
  public double getAverageEncoderDistance() {
    return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2.0;
  }

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  public Encoder getLeftEncoder() {
    return leftEncoder;
  }

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */
  public Encoder getRightEncoder() {
    return rightEncoder;
  }

  /**
   * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    drive.setMaxOutput(maxOutput);
  }

  /**
   * Zeroes the heading of the robot.
   */
  public void zeroHeading() {
    gyro.reset();
  }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from -180 to 180
   */
  public double getHeading() {
    return gyro.getRotation2d().getDegrees();
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {
    return -gyro.getRate();
  }


public boolean driveToDistance(double setpointForward, double autonomousSpeed) {
	return false;
}


public void stop() {
}


public void driveWithJoystick(XboxController driverJoystick, double drivetrainspeed) {
}


public void driveForward(double autonomousSpeed) {
}
}
