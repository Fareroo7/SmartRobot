package at.htl.EngineControl;

/**
 * This class contains methods to control the movement of SmartRobot.
 * 
 * @author Dominik Simon
 * @version 1.0
 */
public class EngineControl {
	
	/**
	 * Width of the SmartRobot in meters.
	 */
	public static final double ROBOT_WIDTH = 0.20;
	
	/**
	 * Maximum Speed of the SmartRobot in meter per seconds.
	 */
	public static final double MAX_SPEED = 3.25; 
	
	/**
	 * Diameter of the wheels from SmartRobot in meter.
	 */
	public static final double WHEEL_DIAMETER = 0.12;
	
	/**
	 * Maximum RPM of the SmartRobot's engines.
	 */
	public static final double MAX_RPM = 293;
	
	/**
	 * Gear ratio of the engines in 1:x.
	 */
	public static final double GEAR_RATIO = 34;
	
	/**
	 * Defines the maximum duty cycle of the hardware (arduino => 8bit)
	 */
	public static final int MAX_DUTY_CYCLE = 0xFF;
	
	private static double curveRadius = 0.5;
	private static double innerCurveRadius = curveRadius - (ROBOT_WIDTH / 2);
	private static double outerCurveRadius = curveRadius + (ROBOT_WIDTH / 2);
	
	private static double speed = 1.0;
	
	/**
	 * Calculates the RPM needed for committed speed.
	 * @param speed Speed in meters per second.
	 * @return Calculated RPM.
	 */
	private static double speedToRPM(double speed){
		return speed / (WHEEL_DIAMETER * Math.PI) * GEAR_RATIO;
	}
	
	/**
	 * Calculates the PWM duty cycle (8bit) for arduino.
	 * @param rpm Rotation per minute.
	 * @return Duty cycle (max 255).
	 */
	private static int rpmToDutyCycle(double rpm){
		return (int) Math.round((MAX_DUTY_CYCLE/MAX_RPM)*rpm);
	}
	
	/**
	 * Calculates the PWM duty cycle (8bit) for the arduino.
	 * @param speed Speed in meter per second.
	 * @return Duty cycle (max 255).
	 */
	public static int speedToDutyCycle(double speed){
		return speed <= MAX_SPEED ? rpmToDutyCycle(speedToRPM(speed)) : (int) MAX_SPEED;
	}
	
	
}
