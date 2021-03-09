// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase {
  DoubleSolenoid Intake12 = new DoubleSolenoid(0, 1);
  DoubleSolenoid Intake22 = new DoubleSolenoid(2, 3);
  DoubleSolenoid ShooterPiston = new DoubleSolenoid(4, 5);
  /** Creates a new Pneumatics. */
  public Pneumatics() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }
  public void BackIntakeretract()
  {
    Intake12.set(DoubleSolenoid.Value.kReverse);
  }
  public void BackIntakeextend()
  {
    Intake12.set(DoubleSolenoid.Value.kForward);
  }
  public void FrontIntakeretract()
  {
    Intake22.set(DoubleSolenoid.Value.kReverse);
  }
  public void FrontIntakeextend()
  {
    Intake22.set(DoubleSolenoid.Value.kForward);
  }
  public void ShooterPistonextend()
  {
    ShooterPiston.set(DoubleSolenoid.Value.kForward);
  }
  public void ShooterPistonretract()
  {
    ShooterPiston.set(DoubleSolenoid.Value.kReverse);
  }
}  
