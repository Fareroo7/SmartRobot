package at.htl.smartbot;

public class EnvironmentalParameter {

	/*
	 * Acoustic velocity in air for temperature is 0°C
	 */
	private static final double ACOUSTIC_VELOCITY_0 = 331.5;

	public static final double EXPANSION_COEFFICIENT = 1 / 273.15;

	// air temperature in °C
	private static double air_temperature = 20;

	public static double getAcousticVelocity() {
		return ACOUSTIC_VELOCITY_0 * Math.sqrt(1 + EXPANSION_COEFFICIENT * air_temperature);
	}

	public static double getAirTemperature() {
		return air_temperature;
	}

	public static void setAirTemperature(double air_temperature) {
		EnvironmentalParameter.air_temperature = air_temperature;
	}

}
