package utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerLoad {
	private static Logger logger = LogManager.getLogger();
	static {
        // Set system property to explicitly specify the location of log4j2 properties file
		System.setProperty("log4j.configurationFile", "C:\\Users\\onlin\\eclipse-workspace\\DsAlgo_Galaxy\\src\\test\\resources\\log4j2.xml");

    }
	
	public static void info(String message) {
		logger.info(message);
	}
	public static void info(int num) {
		logger.info(num);
	}
	public static void warn(String message) {
		logger.warn(message);
	}
	public static void error(String message) {
		logger.error(message);
	}
	public static void debug(String message) {
		logger.debug(message);
	}
	

}




