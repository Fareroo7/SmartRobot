package at.htl.smartbot;

/**
 * Defines and calculates environmental parameters
 * Primary the change of the acoustic velocity with the temperature. 
 * 
 * @author Jakob Ecker
 * @author Domink Simon
 * @version 1.0
 */
public class EnvironmentalParameter {

	/**
	 * Acoustic velocity in air for temperature is 0 Celius
	 */
	private static final double ACOUSTIC_VELOCITY_0 = 331.5;

	public static final double EXPANSION_COEFFICIENT = 1 / 273.15;

	// air temperature in Celius
	private static double air_temperature = 20;

	/**
	 * Returns the acoustic velocity.
	 * @return The acoustic velocity for the air temperature set in setAirTemperature(...) <br /> The default value for temperature is 20 Celius.
	 */
	public static double getAcousticVelocity() {
		return ACOUSTIC_VELOCITY_0 * Math.sqrt(1 + EXPANSION_COEFFICIENT * air_temperature);
	}

	/**
	 * Returns the air temperature.
	 * @return air_temperature Air temperature in Celius.
	 */
	public static double getAirTemperature() {
		return air_temperature;
	}

	/**
	 * Sets the air temperature.
	 * @param air_temperature Air temperature in Celius.
	 */
	public static void setAirTemperature(double air_temperature) {
		EnvironmentalParameter.air_temperature = air_temperature;
	}

}
