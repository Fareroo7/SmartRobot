package at.htl.EngineControl;

import java.util.Arrays;


/**
 * Defines methodes returning the correct {@link String}s for communication with Engine control arduino
 * @author Dominik Simon
 * @version 2.2
 */
public class ECP {
	
//----------Protokollparameter----------
	
	/**
	 * Start Parameter of each Transmission
	 */
	public static final byte START = 0x53;
	
	/**
	 * End of the Transmission.
	 */
	public static final byte END = 0x54;
	
//----------Action Codes----------	
	
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

//----------Direction Codes----------
	
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

//----------Error Codes----------
	
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
	 * Error-Code when the ID-Counter has an overflow. The ID will be reset to 0.
	 */
	public static final byte E_ID_OVERFLOW = 0x34;
	
	/**
	 * Returns a ECPMessage as {@link String}.
	 * @param forward Directions flag (true = forward and false = backward).
	 * @param left Duty Circle for motors on the left side.
	 * @param right Duty Circle for motors on the right side.
	 * @return A ECPMessage as {@link String}.
	 * @deprecated with version 2.1
	 */
	public static String getECMessage(boolean forward, int left, int right){
		return START + (forward ? DIRECTION_FORWARD : DIRECTION_BACKWARD) + (char)left + (char)right + END + "";
	}
	
	/**
	 * <b>Only for user output. Use {@link getECP()} for communication with arduino.</b><br>
	 * Returns the ECP-Message as {@link String} for version 3.0.
	 * @param id The ID of the Task.
	 * @param directionCode The direction code from {@link ECPParameter}.
	 * @param leftDutyCycle Duty Cycle for left engines.
	 * @param rightDutyCycle Duty Cycle for right engines.
	 * @param duration Duration of the Task in milliseconds.
	 * @return ECP-Message as {@link String}.
	 * @deprecated with version 3.0
	 */
	public static String getECPtoString(int id, byte directionCode, int leftDutyCycle, int rightDutyCycle, int duration){
		return START + (char)id + (char) directionCode + (char) leftDutyCycle + (char) rightDutyCycle + (char)duration + END + "";
	}
	
	/**
	 * Returns the Engine Control Protocol for communication with the arduino.
	 * @param id The ID of the Task.
	 * @param directionCode The direction code from {@link ECP}.
	 * @param leftDutyCycle Duty Cycle for left engines.
	 * @param rightDutyCycle Duty Cycle for right engines.
	 * @param duration Duration of the Task in milliseconds.
	 * @return Engine Control Protocol as {@link Byte}-Array.
	 * @deprecated with version 3.0
	 */
	public static byte[] getECP(int id, byte directionCode, int leftDutyCycle, int rightDutyCycle, int duration){
		return new byte[] { START, (byte) id, directionCode, (byte) leftDutyCycle, (byte) rightDutyCycle, (byte)duration, END };		
	}
	
	/**
	 * Returns the Engine Control Protocol for communication with the arduino.
	 * @param task The {@link EngineTask} that will be send.
	 * @return The {@link EngineTask} with header and footer for the communication.
	 */
	public static byte[] getECP(EngineTask task){
		return new byte[] {START, task.getId(),task.getActionCode(), task.getDirectionCode(), task.getDutyCircleLeft(), task.getDutyCircleRight(), task.getDurationMSB(),task.getDurationLSB(), END};
	}
	
	public static void main(String args[]){
		EngineTask t = new EngineTask((byte)1, ECP.A_NEW, ECP.DIRECTION_FORWARD, (byte)100, (byte)100, 500);
		System.out.println(Arrays.toString(ECP.getECP(t)));
	}
	
}
