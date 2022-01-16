package frc.robot.Util;

public class PID {
    public double kP;
    public double kI;
    public double kD;
    public double kF;
    public int kPIDLoopIdx;

    public PID(double kP, double kI, double kD, double kF, int kPIDLoopIdx) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
        this.kPIDLoopIdx = kPIDLoopIdx;
    }

    public PID(double kP, double kI, double kD, double kF) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
    }
}
