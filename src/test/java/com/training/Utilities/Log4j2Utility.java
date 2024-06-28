package com.training.Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Utility {
	
	private static Logger mylogger;
	public static Logger getLogger(Object name) {
		return mylogger=LogManager.getLogger(name.getClass());
	}
	
	public static void logInfo(String message) {
		mylogger.info(message);
	}
	
	public static void logError(String message) {
		mylogger.error(message);
	}
	
	public static void logWarn(String message) {
		mylogger.warn(message);
	}
	
	public static void logDebud(String message) {
		mylogger.debug(message);
	}
}
