/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.commands.DriveArcade;

public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  Spark leftspark = null;
  Spark rightspark = null;

  DifferentialDrive differentialDrive = null;

  public Drivetrain() {
    leftspark = new Spark(RobotMap.DRIVETRAIN_LEFT_SPARK);
    rightspark = new Spark(RobotMap.DIVETRAIN_RIGHT_SPARK);

    SpeedControllerGroup leftMotors = new SpeedControllerGroup(leftspark);
    SpeedControllerGroup rightMotors = new SpeedControllerGroup(rightspark);

    differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
    
  } 
  public void arcadeDrive(double moveSpeed, double rotateSpeed){
    differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
  }
  public void initDefaultCommand() {
    setDefaultCommand(new DriveArcade());
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  
  }
}
