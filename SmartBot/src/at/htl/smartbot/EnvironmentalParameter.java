package at.htl.smartbot;

/**
 * Defines and calculates environmental parameters
 * Primary the change of the acoustic velocity with the temperature. 
 * 
 * @author Jakob Ecker
 * @author Domink Simon
 *
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
	 * 
	 * @return The acoustic velocity for the air temperature set in setAirTemperature(...) <br /> The default value for temperature is 20 Celius
	 * 
	 */
	public static double getAcousticVelocity() {
		return ACOUSTIC_VELOCITY_0 * Math.sqrt(1 + EXPANSION_COEFFICIENT * air_temperature);
	}

	/**
	 * Getter
	 * @return air_temperature
	 */
	public static double getAirTemperature() {
		return air_temperature;
	}

	/**
	 * Setter
	 * @param air_temperature
	 */
	public static void setAirTemperature(double air_temperature) {
		EnvironmentalParameter.air_temperature = air_temperature;
	}

}
