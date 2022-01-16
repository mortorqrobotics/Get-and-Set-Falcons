package frc.robot;

public class PIDMap {
    public static double kP = 0.15;
    public static double kI = 0.0;
    public static double kD = 0.1;
    public static double kF = 0.0;
    public static double iZone = 0.0;
    public static double peakOutput = 1.0;

    public static final int kPIDLoopIdx = 0;
    public static final int kTimeoutMs = 30;
    public static boolean kSensorPhase = true;
}
