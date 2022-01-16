// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.PIDMap;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.TimedRobot;
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
  }

  @Override
  public void teleopInit() {
    SmartDashboard.putNumber("velocity", currentVel);
    SmartDashboard.putNumber("set velocity", newVel);
  }

  @Override
  public void teleopPeriodic() {
    double setVel = SmartDashboard.getNumber("set velocity", 0.0);
    setVel *= (2048.0 / 600.0);
    if(newVel != setVel) {
      newVel = setVel;
    }

    tal.set(ControlMode.Velocity, newVel);
    SmartDashboard.putNumber("velocity", tal.getSelectedSensorVelocity(0));
  }
}