// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.List;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.BackBallIntake;
import frc.robot.commands.BackIntakeExtend;
import frc.robot.commands.BackIntakeRetract;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.FeedBall;
import frc.robot.commands.FrontBallIntake;
import frc.robot.commands.FrontIntakeExtend;
import frc.robot.commands.FrontIntakeRetract;
import frc.robot.commands.ShootBall;
import frc.robot.commands.ShooterPistonExtend;
import frc.robot.commands.ShooterPistonRetract;
import frc.robot.subsystems.BackIntake;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.FrontIntake;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Shooter;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  

  //Controller Declare
  public static Joystick Joystick2;
  //Drivetrain Declare
  private final DriveTrain driveTrain;
  private final DriveWithJoysticks driveWithJoysticks;
  public static XboxController driverJoystick;
  //Shooter Declare
  private final Shooter shooter;
  private final ShootBall shootBall;
  //Back Intake Declare
  private final BackIntake backIntake;
  private final BackBallIntake backBallIntake;
  //Front Intake Declare
  private final FrontIntake frontIntake;
  private final FrontBallIntake frontBallIntake;
  //Feeder Declare
  private final Feeder feeder;
  private final FeedBall feedBall;
  //Pneumatics Declare
  Pneumatics pneumatics = new Pneumatics();
  BackIntakeExtend backIntakeExtend = new BackIntakeExtend(pneumatics);
  BackIntakeRetract backIntakeRetract = new BackIntakeRetract(pneumatics);
  FrontIntakeExtend frontIntakeExtend = new FrontIntakeExtend(pneumatics);
  FrontIntakeRetract frontIntakeRetract = new FrontIntakeRetract(pneumatics);
  ShooterPistonExtend shooterPistonExtend = new ShooterPistonExtend(pneumatics);
  ShooterPistonRetract shooterPistonRetract = new ShooterPistonRetract(pneumatics);
  //Drivetrain Extras
  

  

/** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    //Drive Train
    driveTrain = new DriveTrain();
    driveWithJoysticks = new DriveWithJoysticks(driveTrain);
    driveWithJoysticks.addRequirements(driveTrain);
    driveTrain.setDefaultCommand(driveWithJoysticks);

    //Joysticks
    driverJoystick = new XboxController(Constants.JOYSTICK_NUMBER);
    Joystick2 = new Joystick(Constants.JOYSTICK2);
    //Back Intake
    backIntake = new BackIntake();
    backBallIntake = new BackBallIntake(backIntake);
    backBallIntake.addRequirements(backIntake);
    //Front Intake
    frontIntake = new FrontIntake();
    frontBallIntake = new FrontBallIntake(frontIntake);
    frontBallIntake.addRequirements(frontIntake);
    //Feeder
    feeder = new Feeder();
    feedBall = new FeedBall(feeder);
    feedBall.addRequirements(feeder);
    //Shooter
    shooter = new Shooter();
    shootBall = new ShootBall(shooter);
    shootBall.addRequirements(shooter);
      //Initialize Camera
      UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
      camera.setResolution(Constants.CAMERA_RES_X, Constants.CAMERA_RES_Y);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //Shoot Button
    JoystickButton shootButton = new JoystickButton(driverJoystick, XboxController.Button.kBumperRight.value);
    shootButton.whileHeld(new ShootBall(shooter));
    //Back Intake Pneumatics
    JoystickButton BackIntakeExtendButton = new JoystickButton(Joystick2, 3);
    BackIntakeExtendButton.whenHeld(backIntakeExtend);
    JoystickButton BackIntakeRetractButton = new JoystickButton(Joystick2, 4);
    BackIntakeRetractButton.whenHeld(backIntakeRetract);
    //Front Intake Pneumatics
    JoystickButton frontIntakeExtendButton = new JoystickButton(Joystick2, 1);
    frontIntakeExtendButton.whenHeld(frontIntakeExtend);
    JoystickButton frontIntakeRetractButton = new JoystickButton(Joystick2, 2);
    frontIntakeRetractButton.whenHeld(frontIntakeRetract);
    //Shooter Pneumatics
    JoystickButton shooterPistonExtendButton = new JoystickButton(Joystick2, 5);
    shooterPistonExtendButton.whenHeld(shooterPistonExtend);
    JoystickButton shooterPistonRetractButton = new JoystickButton(Joystick2, 6);
    shooterPistonRetractButton.whenHeld(shooterPistonRetract);
    //Back Intake Button
    JoystickButton backIntakeButton = new JoystickButton(driverJoystick, XboxController.Button.kX.value);
    backIntakeButton.whileHeld(new BackBallIntake(backIntake));
    //Front Intake Button
    JoystickButton frontIntakeButton = new JoystickButton(driverJoystick, XboxController.Button.kY.value);
    frontIntakeButton.whileHeld(new FrontBallIntake(frontIntake));
    //Feeder Button
    JoystickButton feedButton = new JoystickButton(driverJoystick, XboxController.Button.kA.value);
    feedButton.whileHeld(new FeedBall(feeder));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
    // Create a voltage constraint to ensure we don't accelerate too fast
    var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(
              Constants.ksVolts,
              Constants.kvVoltSecondsPerMeter,
              Constants.kaVoltSecondsSquaredPerMeter),
            Constants.kDriveKinematics,
            10);

    // Create config for trajectory
    TrajectoryConfig config =
        new TrajectoryConfig(Constants.kMaxSpeedMetersPerSecond,
                             Constants.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(Constants.kDriveKinematics)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);

    // An example trajectory to follow.  All units in meters.
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
            new Translation2d(1, 1),
            new Translation2d(2, -1)
        ),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(3, 0, new Rotation2d(0)),
        // Pass config
        config);

    RamseteCommand ramseteCommand = new RamseteCommand(
        exampleTrajectory,
        driveTrain::getPose,
        new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
        new SimpleMotorFeedforward(Constants.ksVolts,
                                   Constants.kvVoltSecondsPerMeter,
                                   Constants.kaVoltSecondsSquaredPerMeter),
        Constants.kDriveKinematics,
        driveTrain::getWheelSpeeds,
        new PIDController(Constants.kPDriveVel, 0, 0),
        new PIDController(Constants.kPDriveVel, 0, 0),
        // RamseteCommand passes volts to the callback
        driveTrain::tankDriveVolts,
        driveTrain
    );

    // Reset odometry to the starting pose of the trajectory.
    driveTrain.resetOdometry(exampleTrajectory.getInitialPose());

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> driveTrain.tankDriveVolts(0, 0));
  }
 
}
