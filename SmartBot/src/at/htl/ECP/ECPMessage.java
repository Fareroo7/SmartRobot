package at.htl.ECP;


/**
 * Represents a ECP Message. All possible parameters can be added and the mandatory parameters Start and Stop are added automatically.
 * @author Jakob Ecker
 *
 */
public class ECPMessage {

	private StringBuffer message = new StringBuffer();

	/**
	 * Constructs a new ECP message including the start-parameter
	 */
	public ECPMessage() {
		message.append(Parameter.START);
	}

	/**
	 * Add a 'set both engine sides to xxxx rpm'-statement to the message 
	 * @param rpm only 4 digits are allowed, not checked
	 */
	public void addSetRPM(int rpm) {
		message.append(Parameter.ENGINES);
		message.append(rpm);
	}

	/**
	 * Add a 'set left engine side to xxxx rpm'-statement to the message 
	 * @param rpm only 4 digits are allowed, not checked
	 */
	public void addSetLeftRPM(int rpm) {
		message.append(Parameter.ENGINES_LEFT);
		message.append(rpm);
	}

	/**
	 * Add a 'set right engine side to xxxx rpm'-statement to the message 
	 * @param rpm only 4 digits are allowed, not checked
	 */
	public void addSetRightRPM(int rpm) {
		message.append(Parameter.ENGINES_RIGTH);
		message.append(rpm);
	}

	/**
	 * Add a 'increase both engine sides by xxxx rpm'-statement to the message 
	 * @param rpm only 4 digits are allowed, not checked
	 */
	public void addIncreaseRPM(int rpm) {
		message.append(Parameter.INCREASE_RPM);
		message.append(rpm);
	}

	/**
	 * Add a 'decrease both engine sides by xxxx rpm'-statement to the message 
	 * @param rpm only 4 digits are allowed, not checked
	 */
	public void addDecreaseRPM(int rpm) {
		message.append(Parameter.DECREASE_RPM);
		message.append(rpm);
	}

	/**
	 * Add a 'set direction forward'-statement to the message 
	 */
	public void addDirectionForward() {
		message.append(Parameter.DIRECTION_FORWARD);
	}

	/**
	 * Add a 'set direction backward'-statement to the message 
	 */
	public void addDirectionBackward() {
		message.append(Parameter.DIRECTION_BACKWARD);
	}

	/**
	 * Add a 'error'-statement to the message 
	 * @param error_code error code with 2 digits, not checked
	 */
	public void addError(int error_code) {
		message.append(Parameter.ERROR);
		message.append(error_code);
	}

	/**
	 * Add a 'acknowledge'-statement to the message 
	 */
	public void addAcknowledge() {
		message.append(Parameter.ACKNOWLEDGE);
	}

	/**
	 * Adds the 'end'-statement end returns the message as String
	 * @return
	 */
	public String getMessageAsString() {
		message.append(Parameter.END);
		return message.toString();
	}

	/**
	 * Returns a String to acknowledge a received message. 
	 * @return Acknowledge String: "SAT"
	 */
	public static String getAcknowledgeString() {
		return Parameter.START + Parameter.ACKNOWLEDGE + Parameter.END;
	}
}
