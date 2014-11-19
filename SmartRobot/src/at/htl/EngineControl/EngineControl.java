package at.htl.EngineControl;

import java.io.IOException;

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
	 * One of the five speed steps in meters per second.<br>
	 * Maximum speed.
	 */
	public static final double SPEED_MAX = 3.25;

	/**
	 * One of the five speed steps in meters per second.<br>
	 */
	public static final double SPEED_FAST = SPEED_MAX * 4 / 5;

	/**
	 * One of the five speed steps in meters per second. Normal speed.
	 */
	public static final double SPEED_NORMAL = SPEED_MAX * 3 / 5;

	/**
	 * One of the five speed steps in meters per second.
	 */
	public static final double SPEED_SLOW = SPEED_MAX * 2 / 5;

	/**
	 * One of the five speed steps in meters per second. Minimum speed.
	 */
	public static final double SPEED_MIN = SPEED_MAX / 5;

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
	 * Defines the maximum duty cycle of the hardware (arduino => 8bit).
	 */
	public static final int MAX_DUTY_CYCLE = 0xFF;

	private static double curveRadius = 0.5;
	private static double innerCurveRadius = curveRadius - (ROBOT_WIDTH / 2);
	private static double outerCurveRadius = curveRadius + (ROBOT_WIDTH / 2);

	private static double speed = SPEED_NORMAL;

	public static void main(String[] args) {
		speed = 1.0;
		System.out.println(drive(true, 1).toString());

		System.out.println(turn(true, 3.0, Math.PI));
	}

	/**
	 * Calculates the RPM needed for committed speed.
	 * 
	 * @param speed
	 *            Speed in meters per second.
	 * @return Calculated RPM.
	 */
	private static double speedToRPM(double speed) {
		return speed / (WHEEL_DIAMETER * Math.PI) * GEAR_RATIO;
	}

	/**
	 * Calculates the PWM duty cycle (8bit) for arduino.
	 * 
	 * @param rpm
	 *            Rotation per minute.
	 * @return Duty cycle (max 255).
	 */
	private static int rpmToDutyCycle(double rpm) {
		return (int) Math.round((MAX_DUTY_CYCLE / MAX_RPM) * rpm);
	}

	/**
	 * Calculates the PWM duty cycle (8bit) for the arduino.
	 * 
	 * @param speed
	 *            Speed in meter per second.
	 * @return Duty cycle (max 255).
	 */
	public static int speedToDutyCycle(double speed) {
		return speed <= SPEED_MAX ? rpmToDutyCycle(speedToRPM(speed)) : rpmToDutyCycle(speedToRPM(SPEED_MAX));
	}

	/**
	 * Calculates the needed time for the given distance
	 * 
	 * @param distance
	 *            Distance in m.
	 * @return The needed time in milliseconds.
	 */
	private static int getTimeToDrive(double distance) {
		// v = s / t => t = s / v
		return (int) Math.round((distance * 1000) / (speed));
	}

	public static EngineTask drive(boolean forward, double distance) {
		return new EngineTask(ECP.ID_BROADCAST, ECP.A_NEW, forward ? ECP.DIRECTION_FORWARD : ECP.DIRECTION_BACKWARD, (byte) speedToDutyCycle(speed),
				(byte) speedToDutyCycle(speed), getTimeToDrive(distance));
	}

	/**
	 * 
	 * @param clockwise
	 *            if true direction is clockwise.
	 * @param radius
	 *            in m.
	 * @param angle
	 *            in rad.
	 * @return
	 */
	public static EngineTask turn(boolean clockwise, double radius, double angle) {

		double distance = radius * angle;
		int time = getTimeToDrive(distance);

		System.out.println("mid dist: " + distance + " time: " + time);

		double innerDistance = (radius - (ROBOT_WIDTH / 2)) * angle;
		double outerDistance = (radius + (ROBOT_WIDTH / 2)) * angle;

		System.out.println("Dist: " + innerDistance + " ; " + outerDistance);

		double innerSpeed = innerDistance * 1000 / time;
		double outerSpeed = outerDistance * 1000 / time;

		System.out.println("Speeds: " + innerSpeed + " ; " + outerSpeed);

		int leftDutyCycle;
		int rightDutyCycle;

		if (clockwise) {
			leftDutyCycle = speedToDutyCycle(outerSpeed);
			rightDutyCycle = speedToDutyCycle(innerSpeed);
		} else {
			leftDutyCycle = speedToDutyCycle(innerSpeed);
			rightDutyCycle = speedToDutyCycle(outerSpeed);
		}

		System.out.println("lDut: " + leftDutyCycle + " rdut: " + rightDutyCycle);

		return new EngineTask(ECP.ID_BROADCAST, ECP.A_NEW, ECP.DIRECTION_FORWARD, (byte) leftDutyCycle, (byte) rightDutyCycle, time);
	}

	public static EngineTask abortAll() {
		return new EngineTask(ECP.ID_IMMEDIATE, ECP.A_DELETE_ALL, (byte) 0, (byte) 0, (byte) 0, 0);
	}

}
