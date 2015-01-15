package at.htl.enginecontrol;

/**
 * This class contains methods and constants to control the movement of
 * SmartRobot.
 * 
 * @author Dominik Simon
 * @version 3.1
 */
public class EngineControl_V2 {

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

	private double robot_width = 0.26;

	private double robot_avgTurnRadius = (robot_width * 2) / 3;

	private double robot_maxSpeed = 3.25;

	private double robot_wheelDiameter = 0.12;

	private double robot_maxRPM = 293;

	private double robot_gearRatio = 34;

	private int robot_maxDutyCycle = 0xFF;

	private double curveRadius = 0.5;

	private double speed = 1.0;

	public double getRobot_width() {
		return robot_width;
	}

	public void setRobot_width(double robot_width) {
		this.robot_width = robot_width;
	}

	public double getRobot_avgTurnRadius() {
		return robot_avgTurnRadius;
	}

	public void setRobot_avgTurnRadius(double robot_avgTurnRadius) {
		this.robot_avgTurnRadius = robot_avgTurnRadius;
	}

	public double getRobot_maxSpeed() {
		return robot_maxSpeed;
	}

	public void setRobot_maxSpeed(double robot_maxSpeed) {
		this.robot_maxSpeed = robot_maxSpeed;
	}

	public double getRobot_wheelDiameter() {
		return robot_wheelDiameter;
	}

	public void setRobot_wheelDiameter(double robot_wheelDiameter) {
		this.robot_wheelDiameter = robot_wheelDiameter;
	}

	public double getRobot_maxRPM() {
		return robot_maxRPM;
	}

	public void setRobot_maxRPM(double robot_maxRPM) {
		this.robot_maxRPM = robot_maxRPM;
	}

	public double getRobot_gearRatio() {
		return robot_gearRatio;
	}

	public void setRobot_gearRatio(double robot_gearRatio) {
		this.robot_gearRatio = robot_gearRatio;
	}

	public int getRobot_maxDutyCycle() {
		return robot_maxDutyCycle;
	}

	public void setRobot_maxDutyCycle(int robot_maxDutyCycle) {
		this.robot_maxDutyCycle = robot_maxDutyCycle;
	}

	public double getCurveRadius() {
		return curveRadius;
	}

	public void setCurveRadius(double curveRadius) {
		this.curveRadius = curveRadius;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		if (speed <= this.robot_maxSpeed) {
			this.speed = speed;
		} else {
			this.speed = this.robot_maxSpeed;
		}
	}

	public double getRPM() {
		return speed / (robot_wheelDiameter * Math.PI) * robot_gearRatio;
	}

	public int getDutyCycle() {
		return (int) Math.round((robot_maxDutyCycle / robot_maxRPM) * this.getRPM());
	}

	public int speedToDutyCycle(double speed) {
		return (int) Math.round((robot_maxDutyCycle / robot_maxRPM) * (speed / (robot_wheelDiameter * Math.PI) * robot_gearRatio));
	}

	/**
	 * Calculates the needed time for the given distance
	 * 
	 * @param distance
	 *            Distance in m.
	 * @return The needed time in milliseconds.
	 */
	public int getTimeToDrive(double distance) {
		// v = s / t => t = s / v
		return (int) Math.round((distance * 1000) / (speed));
	}

	public EngineTask accelerateStraight(boolean forward) {
		return new EngineTask(ID_BROADCAST, A_ACCELERATE, forward ? DIRECTION_FORWARD : DIRECTION_BACKWARD, (byte) this.getDutyCycle(),
				(byte) this.getDutyCycle(), 0);
	}

	public EngineTask driveStraight(boolean forward, double distance) {
		return new EngineTask(ID_BROADCAST, A_NEW, forward ? DIRECTION_FORWARD : DIRECTION_BACKWARD, (byte) this.getDutyCycle(), (byte) this.getDutyCycle(),
				this.getTimeToDrive(distance));
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
	public EngineTask driveCurve(boolean forward, boolean clockwise, double radius, double angle) {

		double distance = radius * angle;
		int time = getTimeToDrive(distance);

		System.out.println("mid dist: " + distance + " time: " + time);

		double innerDistance = (radius - (robot_width / 2)) * angle;
		double outerDistance = (radius + (robot_width / 2)) * angle;

		// System.out.println("Dist: " + innerDistance + " ; " + outerDistance);

		double innerSpeed = innerDistance * 1000 / time;
		double outerSpeed = outerDistance * 1000 / time;

		if (outerSpeed > robot_maxSpeed) {
			double diff = outerSpeed - robot_maxSpeed;
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

		return new EngineTask(ID_BROADCAST, A_NEW, forward ? DIRECTION_FORWARD : DIRECTION_BACKWARD, (byte) leftDutyCycle, (byte) rightDutyCycle, time);
	}

	public EngineTask abortAll() {
		return new EngineTask(ID_IMMEDIATE, A_DELETE_ALL, (byte) 0, (byte) 0, (byte) 0, 0);
	}

	public EngineTask stop() {
		return new EngineTask(ID_BROADCAST, A_STOP, (byte) 0, (byte) 0, (byte) 0, 0);
	}

	public EngineTask turn(boolean clockwise, double angle) {

		double distance = angle * robot_avgTurnRadius;
		System.out.println(distance);
		int time = getTimeToDrive(distance);
		return new EngineTask(ID_BROADCAST, A_NEW, clockwise ? DIRECTION_TURN_CLOCKWISE : DIRECTION_TURN_ANTICLOCKWISE, (byte) this.getDutyCycle(),
				(byte) this.getDutyCycle(), time);
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
		return START + (forward ? DIRECTION_FORWARD : DIRECTION_BACKWARD) + (char) left + (char) right + END + "";
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
	public static String getECPtoString(int id, byte directionCode, int leftDutyCycle, int rightDutyCycle, int duration) {
		return START + (char) id + (char) directionCode + (char) leftDutyCycle + (char) rightDutyCycle + (char) duration + END + "";
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
	public static byte[] getECP(int id, byte directionCode, int leftDutyCycle, int rightDutyCycle, int duration) {
		return new byte[] { START, (byte) id, directionCode, (byte) leftDutyCycle, (byte) rightDutyCycle, (byte) duration, END };
	}

	/**
	 * Returns the Engine Control Protocol for communication with the arduino.
	 * 
	 * @param task
	 *            The {@link EngineTask} that will be send.
	 * @return The {@link EngineTask} with header and footer for the
	 *         communication.
	 * @deprecated Use the getECP-method of the {@link EngineTask}
	 */
	public static byte[] getECP(EngineTask task) {
		return new byte[] { START, task.getId(), task.getActionCode(), task.getDirectionCode(), task.getDutyCircleLeft(), task.getDutyCircleRight(),
				task.getDurationMSB(), task.getDurationLSB(), END };
	}

}
