// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.PIDMap;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  TalonFX tal = new TalonFX(RobotMap.TALON_ID);
  TalonFX tal2 = new TalonFX(RobotMap.TALON_ID2);

  public static XboxController mainStick;


  double currentVel = 0;
  double newVel = 0;


  @Override
  public void robotInit() {
    tal.configFactoryDefault();
    tal.configNeutralDeadband(0.001);
    tal.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,
        PIDMap.kPIDLoopIdx,
        PIDMap.kTimeoutMs);

    tal.configNominalOutputForward(0, PIDMap.kTimeoutMs);
		tal.configNominalOutputReverse(0, PIDMap.kTimeoutMs);
		tal.configPeakOutputForward(1, PIDMap.kTimeoutMs);
		tal.configPeakOutputReverse(-1, PIDMap.kTimeoutMs);

    tal.config_kF(PIDMap.kPIDLoopIdx, PIDMap.kF, PIDMap.kTimeoutMs);
		tal.config_kP(PIDMap.kPIDLoopIdx, PIDMap.kP, PIDMap.kTimeoutMs);
		tal.config_kI(PIDMap.kPIDLoopIdx, PIDMap.kI, PIDMap.kTimeoutMs);
		tal.config_kD(PIDMap.kPIDLoopIdx, PIDMap.kD, PIDMap.kTimeoutMs);

    tal2.configFactoryDefault();
    tal2.configNeutralDeadband(0.001);
    tal2.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,
        PIDMap.kPIDLoopIdx,
        PIDMap.kTimeoutMs);

    tal2.configNominalOutputForward(0, PIDMap.kTimeoutMs);
		tal2.configNominalOutputReverse(0, PIDMap.kTimeoutMs);
		tal2.configPeakOutputForward(1, PIDMap.kTimeoutMs);
		tal2.configPeakOutputReverse(-1, PIDMap.kTimeoutMs);

    tal2.config_kF(PIDMap.kPIDLoopIdx, PIDMap.kF, PIDMap.kTimeoutMs);
		tal2.config_kP(PIDMap.kPIDLoopIdx, PIDMap.kP, PIDMap.kTimeoutMs);
		tal2.config_kI(PIDMap.kPIDLoopIdx, PIDMap.kI, PIDMap.kTimeoutMs);
		tal2.config_kD(PIDMap.kPIDLoopIdx, PIDMap.kD, PIDMap.kTimeoutMs);

    mainStick = new XboxController(0);
  }

  @Override
  public void teleopInit() {
    SmartDashboard.putNumber("velocity", currentVel);
  }

  @Override
  public void teleopPeriodic() {
    double setVel = mainStick.getRightY();
    setVel *= (300 * 4096.0 / 600.0);

    double setVel2 = mainStick.getRightX();
    setVel2 *= (300 * 4096.0 / 600.0);

    tal.set(ControlMode.Velocity, setVel);
    tal2.set(ControlMode.Velocity, setVel2);
    SmartDashboard.putNumber("velocity", tal.getSelectedSensorVelocity(0) * (600.0 / 4096.0));
  }
}
