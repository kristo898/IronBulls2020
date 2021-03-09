// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    CANSparkMax Shooter1;
    CANSparkMax Shooter2;
  /** Creates a new Shooter. */
  public Shooter() {
    Shooter1 = new CANSparkMax(Constants.SHOOTER1, MotorType.kBrushless);
    Shooter2 = new CANSparkMax(Constants.SHOOTER2, MotorType.kBrushless);
    Shooter1.restoreFactoryDefaults();
    Shooter2.restoreFactoryDefaults();
    Shooter1.setInverted(true);
    Shooter2.setInverted(false);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shootBall(double speed)
  {
    Shooter1.set(speed);
    Shooter2.set(speed);
  }

  public void stop()
  {
    Shooter1.set(0);
    Shooter2.set(0);
  }
}
