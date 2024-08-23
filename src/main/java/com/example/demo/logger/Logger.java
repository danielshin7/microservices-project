package com.example.demo.logger;

public class Logger {
	private static String module = "na";
	private static boolean on = true;

	public Logger(String module) {
		Logger.module = module;
	}

	public Logger() {
	}

	public void mlog(String message) {
		System.out.println(module.toUpperCase() + ": " + message);
	}

	public static void log(String message) {
		System.out.println(module.toUpperCase() + ": " + message);
	}

	public static void on() {
		Logger.on = true;
	}

	public static void off() {
		Logger.on = true;
	}

}