package core;

public class Configure {
	
	// General
	public static boolean isInit = true;
	
	// Time Table Conf.
	public static String strDate="2016-10-20"; // YYYY-MM-DD
	
	public static String strTime="10:00:00"; // HH:mm:ss
	public static String endTime="11:30:00"; // HH:mm:ss
	
	public static int interval = 1; // Second
	
	// User Conf.
	public static int numUser = 8; // Second

	// Sensor_List Conf.
	public static int numSensor = 8*3; // Second
	public static String[] sensorType = {
			"video","audio","bio"
	};
	// Emotion_List Conf.
	public static String[] emotionList = {
			"angry","delight","fear","happy","surprise","disgust","bored","neutral"
	};


}
