/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OI extends CommandBase {
  public Joystick driverController =  new Joystick(RobotMap.OI_DRIVERCONTROLLER);

  Button D0 = new JoystickButton(driverController, 0);
  Button D1 = new JoystickButton(driverController, 1);
  Button D2 = new JoystickButton(driverController, 2);
  Button D3 = new JoystickButton(driverController, 3);
  Button D4 = new JoystickButton(driverController, 4);
  Button D5 = new JoystickButton(driverController, 5);
  Button D6 = new JoystickButton(driverController, 6);
  Button D7 = new JoystickButton(driverController, 7);
  Button D8 = new JoystickButton(driverController, 8);
  Button D9 = new JoystickButton(driverController, 9);
  Button D10 = new JoystickButton(driverController, 10);

  public OI() {
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
