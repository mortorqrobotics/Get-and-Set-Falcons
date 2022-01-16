package frc.robot;

public class PIDMap {
    public static double kP = 0.1;
    public static double kI = 0.001;
    public static double kD = 5;
    public static double kF = 1023.0/20660.0;
    public static double iZone = 300;
    public static double peakOutput = 1.00;

    public static final int kPIDLoopIdx = 0;
    public static final int kTimeoutMs = 30;
    public static boolean kSensorPhase = true;
}
