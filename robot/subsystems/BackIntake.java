// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;



import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BackIntake extends SubsystemBase {
  WPI_VictorSPX backIntake1;
  WPI_VictorSPX backIntake2;
  /** Creates a new Intake. */

  public BackIntake() {
    backIntake1 = new WPI_VictorSPX(Constants.BACKINTAKE1);
    backIntake2 = new WPI_VictorSPX(Constants.BACKINTAKE2);
    backIntake1.configFactoryDefault();
    backIntake2.configFactoryDefault();
    backIntake1.setInverted(false);
    backIntake2.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }
  public void backBallintake(double speed)
  {
    backIntake1.set(speed);
    backIntake2.set(speed);
  }
  public void stop()
  {
    backIntake1.set(0);
    backIntake2.set(0);
  }


}
