// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class FrontIntake extends SubsystemBase {
  WPI_VictorSPX frontIntake1;
  WPI_VictorSPX frontIntake2;
  /** Creates a new FrontIntake. */
  public FrontIntake() {
    frontIntake1 = new WPI_VictorSPX(Constants.FRONTINTAKE1);
    frontIntake2 = new WPI_VictorSPX(Constants.FRONTINTAKE2);
    frontIntake1.configFactoryDefault();
    frontIntake2.configFactoryDefault();
    frontIntake1.setInverted(false);
    frontIntake2.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  public void frontBallintake(double speed)
  {
    frontIntake1.set(speed);
    frontIntake2.set(speed);
  }
  public void stop()
  {
    frontIntake1.set(0);
    frontIntake2.set(0);
  }
}
