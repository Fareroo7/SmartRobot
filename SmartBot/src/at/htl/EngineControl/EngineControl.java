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
	public static final double MAX_SPEED = 100.0; 
	
	/**
	 * Curve radius in meters.
	 */
	private static double curveRadius = 0.5;
	
	private static double innerCurveRadius = curveRadius - (ROBOT_WIDTH / 2);
	private static double outerCurveRadius = curveRadius + (ROBOT_WIDTH / 2);
	
	/**
	 * Speed of the SmartRobot in meter per seconds.
	 */
	private static double speed = 1.0;
	
}
