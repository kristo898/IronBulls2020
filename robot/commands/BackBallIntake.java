// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BackIntake;
import frc.robot.Constants;

public class BackBallIntake extends CommandBase {
  BackIntake backIntake;
  /** Creates a new BACKINTAKE. */
  public BackBallIntake(BackIntake bi) {
    backIntake = bi;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(backIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    backIntake.backBallintake(Constants.intake_speed1);
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
