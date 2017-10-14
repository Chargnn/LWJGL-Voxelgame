package utils;

public class Time {

	private static int time = 0;
	
	public static void update()
	{
		time ++;
	}

	public static double getTime() {
		return time;
	}

	public static void setTime(int time) {
		Time.time = time;
	}
}
