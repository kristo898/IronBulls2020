/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RobotMap extends CommandBase {
  //Sparks
  public static final int DIVETRAIN_RIGHT_SPARK = 0;
  public static final int DRIVETRAIN_LEFT_SPARK = 1;
  //Joystick
  public static final int OI_DRIVERCONTROLLER = 0;
  //Axis
  public static final int DRIVER_CONTROLLER_MOVE_AXIS = 0;
  public static final int DRIVER_CONTROLLER_ROTATE_AXIS = 1;
/**
   * Creates a new RobotMap.
   */
  public RobotMap() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
