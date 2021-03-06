package frc.robot.Util;

import frc.robot.Util.PIDMap;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;

public class Falcon {
    TalonFX tal;

    public Falcon(int talonID, PID pid) {
        tal = new TalonFX(talonID);
        tal.configFactoryDefault();
        tal.configNeutralDeadband(0.001);
        tal.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,
                pid.kPIDLoopIdx,
                PIDMap.kTimeoutMs);

        tal.configNominalOutputForward(0, PIDMap.kTimeoutMs);
        tal.configNominalOutputReverse(0, PIDMap.kTimeoutMs);
        tal.configPeakOutputForward(1, PIDMap.kTimeoutMs);
        tal.configPeakOutputReverse(-1, PIDMap.kTimeoutMs);

        tal.config_kF(pid.kPIDLoopIdx, pid.kF, PIDMap.kTimeoutMs);
        tal.config_kP(pid.kPIDLoopIdx, pid.kP, PIDMap.kTimeoutMs);
        tal.config_kI(pid.kPIDLoopIdx, pid.kI, PIDMap.kTimeoutMs);
        tal.config_kD(pid.kPIDLoopIdx, pid.kD, PIDMap.kTimeoutMs);
    }

    public void set(ControlMode controlMode, double value) {
        if(controlMode == ControlMode.Velocity) {
            setVelocity(value);
        }
        else if(controlMode == ControlMode.Position) {
            setPosition(value);
        }
    }

    private void setVelocity(double speed) {
                                      // speed in RPM
        tal.set(ControlMode.Velocity, speed * (600 / 4096));
    }

    private void setPosition(double position) {
                                      // position in degrees
        tal.set(ControlMode.Position, position * (360 / 4096));
    }
}
