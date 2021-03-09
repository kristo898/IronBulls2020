// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {
  WPI_VictorSPX Feeder1;
  WPI_VictorSPX Feeder2;
  /** Creates a new Feeder. */
  public Feeder() {
    Feeder1 = new WPI_VictorSPX(Constants.FEEDER1);
    Feeder2 = new WPI_VictorSPX(Constants.FEEDER2);
    Feeder1.configFactoryDefault();
    Feeder2.configFactoryDefault();
    Feeder1.setInverted(false);
    Feeder2.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void feedBall(double speed)
  {
    Feeder1.set(speed);
    Feeder2.set(speed);
  }
  public void stop()
  {
    Feeder1.set(0);
    Feeder2.set(0);
  }
}
