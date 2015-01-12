package at.htl.enginecontrol;

/**
 * This class contains methods and constants to control the movement of
 * SmartRobot.
 * 
 * @author Dominik Simon
 * @version 3.1
 */
public class EngineControl {

	// ----------Protokollparameter----------

	/**
	 * Start Parameter of each Transmission
	 */
	public static final byte START = 0x53;

	/**
	 * End of the Transmission.
	 */
	public static final byte END = 0x54;

	// ----------Action Codes----------

	/**
	 * Action Code to delete the current task list and abort all actions
	 */
	public static final byte A_DELETE_ALL = 0x01;

	/**
	 * Action Code to create a new task
	 */
	public static final byte A_NEW = 0x02;

	/**
	 * Action Code to delete the task with ID
	 */
	public static final byte A_DELETE_ID = 0x03;

	/**
	 * Action Code to update the task with ID
	 */
	public static final byte A_UPDATE_ID = 0x04;

	/**
	 * Action Code to slowly accelerate to target duty cycle without overload at
	 * the engines.
	 */
	public static final byte A_ACCELERATE = 0x11;

	/**
	 * Action Code to stop the engines (is NOT like duty cycle = 0).
	 */
	public static final byte A_STOP = 0x12;

	// ----------Direction Codes----------

	/**
	 * Set the direction of the engines to forward
	 */
	public static final byte DIRECTION_FORWARD = 0x11;

	/**
	 * Set the direction of the engines to backward
	 */
	public static final byte DIRECTION_BACKWARD = 0x22;

	/**
	 * Set the direction of the left engine to forward and the right engine to
	 * backward.
	 */
	public static final byte DIRECTION_TURN_CLOCKWISE = 0X12;

	/**
	 * Set the direction of the left engine to backward and the right engine to
	 * forward.
	 */
	public static final byte DIRECTION_TURN_ANTICLOCKWISE = 0x21;

	// ----------Error Codes----------

	/**
	 * Code for Acknowladge.
	 */
	public static final byte E_ACK = 0x01;

	/**
	 * Error-Code for Transmission faild.
	 */
	public static final byte E_TRANSMISSION = 0x02;

	/**
	 * Error-Code for Overcurrent.
	 */
	public static final byte E_OVERCURRENT = 0x10;

	/**
	 * Error-Code for Bumper active in front left
	 */
	public static final byte E_BUMPER_FRONT_LEFT = 0x24;

	/**
	 * Error-Code for Bumper active in front center
	 */
	public static final byte E_BUMPER_FRONT_CENTER = 0x28;

	/**
	 * Error-Code for Bumper active in front right
	 */
	public static final byte E_BUMPER_FRONT_RIGHT = 0x2C;

	/**
	 * Error-Code for Bumper active in back left
	 */
	public static final byte E_BUMPER_BACK_LEFT = 0x21;

	/**
	 * Error-Code for Bumper active in back center
	 */
	public static final byte E_BUMPER_BACK_CENTER = 0x22;

	/**
	 * Error-Code for Bumper active in back right
	 */
	public static final byte E_BUMPER_BACK_RIGHT = 0x23;

	/**
	 * Error-Code when the bot runs out of tasks
	 */
	public static final byte E_OUT_OF_TASKS = 0x31;

	/**
	 * Error-Code when the task with the ID(in message) is finished
	 */
	public static final byte E_TASK_ID_COMPLETE = 0x32;

	/**
	 * Error-Code when the task could not be completed
	 */
	public static final byte E_EXECUTION_ERROR = 0x33;

	/**
	 * Error-Code when the ID-Counter has an overflow. The ID will be reset to
	 * 0.
	 */
	public static final byte E_ID_OVERFLOW = 0x34;

	// Static Addresses

	/**
	 * ID for messages that don't need an ID
	 */
	public static final byte ID_BROADCAST = (byte) 0xff;

	/**
	 * ID for messages that have to be executed immediately.
	 */
	public static final byte ID_IMMEDIATE = (byte) 0xfe;

	// Robot parameters

	/**
	 * Width of the SmartRobot in meters.
	 */
	public static final double ROBOT_WIDTH = 0.20;

	public static final double ROBOT_AVG_TURN_RADIUS = (ROBOT_WIDTH * 2) / 3;

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

	private static double speed = SPEED_NORMAL;

	public static void main(String[] args) {
		speed = 1.5;
		System.out.println(speedToRPM(speed));

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
		return speed <= SPEED_MAX ? rpmToDutyCycle(speedToRPM(speed))
				: rpmToDutyCycle(speedToRPM(SPEED_MAX));
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

	public static EngineTask accelerateStraight(boolean forward) {
		return new EngineTask(ID_BROADCAST, A_ACCELERATE,
				forward ? DIRECTION_FORWARD : DIRECTION_BACKWARD,
				(byte) speedToDutyCycle(speed), (byte) speedToDutyCycle(speed),
				0);
	}

	public static EngineTask driveStraight(boolean forward, double distance) {
		return new EngineTask(ID_BROADCAST, A_NEW, forward ? DIRECTION_FORWARD
				: DIRECTION_BACKWARD, (byte) speedToDutyCycle(speed),
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
	public static EngineTask driveCurve(boolean forward, boolean clockwise,
			double radius, double angle) {

		double distance = radius * angle;
		int time = getTimeToDrive(distance);

		System.out.println("mid dist: " + distance + " time: " + time);

		double innerDistance = (radius - (ROBOT_WIDTH / 2)) * angle;
		double outerDistance = (radius + (ROBOT_WIDTH / 2)) * angle;

		// System.out.println("Dist: " + innerDistance + " ; " + outerDistance);

		double innerSpeed = innerDistance * 1000 / time;
		double outerSpeed = outerDistance * 1000 / time;

		if (outerSpeed > SPEED_MAX) {
			double diff = outerSpeed - SPEED_MAX;
			outerSpeed -= diff;
			innerSpeed -= diff;
		}

		// System.out.println("Speeds: " + innerSpeed + " ; " + outerSpeed);

		int leftDutyCycle;
		int rightDutyCycle;

		if (clockwise) {
			leftDutyCycle = speedToDutyCycle(outerSpeed);
			rightDutyCycle = speedToDutyCycle(innerSpeed);
		} else {
			leftDutyCycle = speedToDutyCycle(innerSpeed);
			rightDutyCycle = speedToDutyCycle(outerSpeed);
		}

		// System.out.println("lDut: " + leftDutyCycle + " rdut: " +
		// rightDutyCycle);

		return new EngineTask(ID_BROADCAST, A_NEW, forward ? DIRECTION_FORWARD
				: DIRECTION_BACKWARD, (byte) leftDutyCycle,
				(byte) rightDutyCycle, time);
	}

	public static EngineTask abortAll() {
		return new EngineTask(ID_IMMEDIATE, A_DELETE_ALL, (byte) 0, (byte) 0,
				(byte) 0, 0);
	}

	public static EngineTask stop() {
		return new EngineTask(ID_BROADCAST, A_STOP, (byte) 0, (byte) 0,
				(byte) 0, 0);
	}

	public static EngineTask turn(boolean clockwise, double angle) {

		double distance = angle * ROBOT_AVG_TURN_RADIUS;
		int time = getTimeToDrive(distance);
		byte dutyCycle = (byte) speedToDutyCycle(speed);
		return new EngineTask(ID_BROADCAST, A_NEW,
				clockwise ? DIRECTION_TURN_CLOCKWISE
						: DIRECTION_TURN_ANTICLOCKWISE, dutyCycle, dutyCycle,
				time);
	}

	/**
	 * Returns a ECPMessage as {@link String}.
	 * 
	 * @param forward
	 *            Directions flag (true = forward and false = backward).
	 * @param left
	 *            Duty Circle for motors on the left side.
	 * @param right
	 *            Duty Circle for motors on the right side.
	 * @return A ECPMessage as {@link String}.
	 * @deprecated with version 2.1
	 */
	public static String getECMessage(boolean forward, int left, int right) {
		return START + (forward ? DIRECTION_FORWARD : DIRECTION_BACKWARD)
				+ (char) left + (char) right + END + "";
	}

	/**
	 * <b>Only for user output. Use {@link getECP()} for communication with
	 * arduino.</b><br>
	 * Returns the ECP-Message as {@link String} for version 3.0.
	 * 
	 * @param id
	 *            The ID of the Task.
	 * @param directionCode
	 *            The direction code from {@link ECPParameter}.
	 * @param leftDutyCycle
	 *            Duty Cycle for left engines.
	 * @param rightDutyCycle
	 *            Duty Cycle for right engines.
	 * @param duration
	 *            Duration of the Task in milliseconds.
	 * @return ECP-Message as {@link String}.
	 * @deprecated with version 3.0
	 */
	public static String getECPtoString(int id, byte directionCode,
			int leftDutyCycle, int rightDutyCycle, int duration) {
		return START + (char) id + (char) directionCode + (char) leftDutyCycle
				+ (char) rightDutyCycle + (char) duration + END + "";
	}

	/**
	 * Returns the Engine Control Protocol for communication with the arduino.
	 * 
	 * @param id
	 *            The ID of the Task.
	 * @param directionCode
	 *            The direction code from {@link ECP}.
	 * @param leftDutyCycle
	 *            Duty Cycle for left engines.
	 * @param rightDutyCycle
	 *            Duty Cycle for right engines.
	 * @param duration
	 *            Duration of the Task in milliseconds.
	 * @return Engine Control Protocol as {@link Byte}-Array.
	 * @deprecated with version 3.0
	 */
	public static byte[] getECP(int id, byte directionCode, int leftDutyCycle,
			int rightDutyCycle, int duration) {
		return new byte[] { START, (byte) id, directionCode,
				(byte) leftDutyCycle, (byte) rightDutyCycle, (byte) duration,
				END };
	}

	/**
	 * Returns the Engine Control Protocol for communication with the arduino.
	 * 
	 * @param task
	 *            The {@link EngineTask} that will be send.
	 * @return The {@link EngineTask} with header and footer for the
	 *         communication.
	 */
	public static byte[] getECP(EngineTask task) {
		return new byte[] { START, task.getId(), task.getActionCode(),
				task.getDirectionCode(), task.getDutyCircleLeft(),
				task.getDutyCircleRight(), task.getDurationMSB(),
				task.getDurationLSB(), END };
	}

}
