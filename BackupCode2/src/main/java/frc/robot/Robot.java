/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  // Master Controllers
  WPI_TalonSRX _frontleftMotor = new WPI_TalonSRX(1);
  WPI_TalonSRX _frontrightMotor = new WPI_TalonSRX(2);
  // Slave Controllers
  WPI_VictorSPX _leftSlave = new WPI_VictorSPX(5);
  WPI_VictorSPX _rightSlave = new WPI_VictorSPX(7);
  // Shooter Controllers
  private static final int leftDeviceID = 1;
  private static final int rightDeviceID = 2;
  private CANSparkMax m_ShooterMotor1;
  private CANSparkMax m_ShooterMotor2;
  // Feeder Controllers
  public Spark m_Feeder1 = new Spark(2);
  public Spark m_Feeder2 = new Spark(3);
  // Intake Side 1
  public Spark m_IntakeSide1 = new Spark(4);
  // Intake Side 2
  public Spark m_IntakeSide2 = new Spark(5);
  // Compressor
  public Compressor compressor = new Compressor();
  // Double Solenoid
  public DoubleSolenoid Intake = new DoubleSolenoid(0, 1);
  public DoubleSolenoid Intake2 = new DoubleSolenoid(2, 3);

  // Drivetrain via Master Controllers
  DifferentialDrive _drive = new DifferentialDrive(_frontleftMotor, _frontrightMotor);
  // Joystick for Drive
  Joystick _joy1 = new Joystick(0);
  // Joystick for Extra Controls
  Joystick _joy2 = new Joystick(1);

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

  @Override
  public void robotInit() {
    // Factory Default all Hardware to prevent unexpected behaviour
    _frontleftMotor.configFactoryDefault();
    _frontrightMotor.configFactoryDefault();
    _leftSlave.configFactoryDefault();
    _rightSlave.configFactoryDefault();
    // Slaves following the Master controllers
    _leftSlave.follow(_frontleftMotor);
    _rightSlave.follow(_frontrightMotor);
    // Sets inversions for drive
    _frontrightMotor.setInverted(false);
    _frontleftMotor.setInverted(true);
    _leftSlave.setInverted(InvertType.FollowMaster);
    _rightSlave.setInverted(InvertType.FollowMaster);
    // start Compressor
    compressor.start();


    m_ShooterMotor1 = new CANSparkMax(leftDeviceID, MotorType.kBrushless);
    m_ShooterMotor2 = new CANSparkMax(rightDeviceID, MotorType.kBrushless);

    m_ShooterMotor1.restoreFactoryDefaults();
    m_ShooterMotor2.restoreFactoryDefaults();
  
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString line to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional comparisons to the
   * switch structure below with additional strings. If using the SendableChooser
   * make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    //Gamepad processing
    double forward = -1.0 * _joy1.getY();
    double turn = +1.0 * _joy1.getZ();
    //Deadband - within 10% joystick, make it zero
    if (Math.abs(forward) < 0.10) {
      forward = 0;
    }
    if (Math.abs(turn) < 0.10) {
      turn = 0;
    }
    //Prints joystick values
    System.out.println("JoyY:" + forward + " turn:" + turn);
    //Drives the robot
    _drive.arcadeDrive(forward, turn);
    //Shooter
    double ShooterPower1 = 0;
    double ShooterPower2 = 0;
    if (_joy2.getRawButton(1)==true){
      ShooterPower1 = -1;
      ShooterPower2 = 1;
    }else if (_joy2.getRawButton(2)) {
      ShooterPower1 = 1;
      ShooterPower2 = -1;
    }
    m_ShooterMotor1.set(ShooterPower1);
    m_ShooterMotor2.set(ShooterPower2);
    //Intake 1
    double IntakePower1 = 0;
    if (_joy1.getRawButton(1)) {
      IntakePower1 = 1;
    } else {
      IntakePower1 = 0;
    }
    m_IntakeSide1.set(IntakePower1);
    //Intake 2
    double IntakePower2 = 0;
    if (_joy1.getRawButton(2)) {
      IntakePower2 = 1;
    } else {
      IntakePower2 = 0;
    }
    m_IntakeSide2.set(IntakePower2);
    //Feeder
    double FeederPower1 = 0;
    if (_joy2.getRawButton(3)) {
      FeederPower1 = 1;
    } else {
      FeederPower1 = 0;
    }
    m_Feeder1.set(FeederPower1);
    double FeederPower2 = 0;
    if (_joy2.getRawButton(4)) {
      FeederPower2 = 1;
    } else {
      FeederPower2 = 0;
    }
    m_Feeder2.set(FeederPower2);
    //Pnuematics
    if (_joy1.getRawButton(3)) {
      Intake.set(Value.kReverse);
    } else {
      Intake.set(Value.kForward);
    }
    if (_joy1.getRawButton(4)) {
      Intake2.set(Value.kReverse);
    } else {
      Intake2.set(Value.kForward);
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
