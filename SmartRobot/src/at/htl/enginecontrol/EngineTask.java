package at.htl.enginecontrol;

/**
 * This class saves all parameters to transmite an engine - control message to the arduino board. 
 * Objects of EngineTask can be converted into a message via {@link ECP} with the getECP() method. 
 * 
 * @author Dominik Simon
 *
 */
public class EngineTask {

	/**
	 * Position of the lowbyte of a unsigned int16 saved as byte-array
	 */
	public static final int LOWBYTE = 0;
	
	/**
	 * Position of the highbyte of a unsigned int16 saved as byte-array
	 */
	public static final int HIGHBYTE = 1;

	private byte id;
	private byte actionCode;
	private byte directionCode;
	private byte dutyCircleLeft;
	private byte dutyCircleRight;
	private byte[] durations = new byte[2];

	/**
	 * Constructs a empty EngineTask-Object.
	 */
	public EngineTask() {
	}

	/**
	 * Constructs a EngineTask-Object.
	 * 
	 * @param id
	 *            The ID of the Task.
	 * @param actionCode
	 * 			  The code that defines the action performed by the task.
	 * @param directionCode
	 *            DirectionCode from EngineControl.
	 * @param dutyCircleLeft
	 *            Dutycircle for the left engine.
	 * @param dutyCircleRight
	 *            Dutycircle for the right engine.
	 * @param duration
	 *            Duration of the Task. After this time the next Task will be
	 *            execute. Time in milliseconds.
	 */

	public EngineTask(byte id, byte actionCode, byte directionCode, byte dutyCircleLeft, byte dutyCircleRight, int duration) {
		this.id = id;
		this.actionCode = actionCode;
		this.directionCode = directionCode;
		this.dutyCircleLeft = dutyCircleLeft;
		this.dutyCircleRight = dutyCircleRight;
		this.durations[HIGHBYTE] = (byte) (duration >> 8);
		this.durations[LOWBYTE] = (byte) duration;
	}

	/**
	 * Getter
	 * @return 
	 */
	public byte getDirectionCode() {
		return directionCode;
	}

	/**
	 * Setter
	 * @param actionCode
	 */
	public void setDirectionCode(byte directionCode) {
		this.directionCode = directionCode;
	}
	
	/**
	 * Getter
	 * @return 
	 */
	public byte getDutyCircleLeft() {
		return dutyCircleLeft;
	}

	/**
	 * Setter
	 * @param actionCode
	 */
	public void setDutyCircleLeft(byte dutyCircleLeft) {
		this.dutyCircleLeft = dutyCircleLeft;
	}

	/**
	 * Getter
	 * @return 
	 */
	public byte getDutyCircleRight() {
		return dutyCircleRight;
	}

	/**
	 * Setter
	 * @param actionCode
	 */
	public void setDutyCircleRight(byte dutyCircleRight) {
		this.dutyCircleRight = dutyCircleRight;
	}

	/**
	 * Getter
	 * @return 
	 */
	public int getDuration() {
		return ((this.durations[HIGHBYTE]<<8)|(this.durations[LOWBYTE]&0xff));
	}

	/**
	 * Getter
	 * @return 
	 */
	public byte getDurationMSB(){
		return this.durations[HIGHBYTE];
	}

	/**
	 * Getter
	 * @return 
	 */
	public byte getDurationLSB(){
		return this.durations[LOWBYTE];
	}

	/**
	 * Getter
	 * @return 
	 */
	public byte getId() {
		return id;
	}

	/**
	 * Getter
	 * @return 
	 */
	public byte getActionCode() {
		return actionCode;
	}

	/**
	 * Setter
	 * @param actionCode
	 */
	public void setActionCode(byte actionCode) {
		this.actionCode = actionCode;
	}
	/**
	 * Custom toString.
	 * @return Returns a String 
	 */
	@Override
	public String toString() {
		String direction = "";
		switch (directionCode) {
		case EngineControl.DIRECTION_FORWARD:
			direction = "FORWARD";
			break;
		case EngineControl.DIRECTION_BACKWARD:
			direction = "BACKWARD";
			break;
		case EngineControl.DIRECTION_TURN_CLOCKWISE:
			direction = "CLOCKWISE";
			break;
		case EngineControl.DIRECTION_TURN_ANTICLOCKWISE:
			direction = "ANTICLOCKWISE";
			break;
		}

		return "EngineTask [ id=" + id + ", Action Code=" + actionCode + ", Direction Code=" + direction + ", Dutycircle Left=" + dutyCircleLeft
				+ ", Dutycircle Right=" + dutyCircleRight + ", Duration=" + this.getDuration() + " ms ]";

	}

}
