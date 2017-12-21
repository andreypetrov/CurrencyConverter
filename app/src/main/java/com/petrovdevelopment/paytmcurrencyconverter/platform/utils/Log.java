package com.petrovdevelopment.paytmcurrencyconverter.platform.utils;

/**
 * Convenience logger on top of Anroid's android.util.Log
 */
public class Log {

    /**
     * Log a message
     * @param author - usually the class you are calling from. It will be written in the console
     * @param message
     */
    public static void log(Object author, String message) {
        android.util.Log.i(author.getClass().getSimpleName(), message);
    }

    public static void log(Object author, boolean b) {
        android.util.Log.i(author.getClass().getSimpleName(), String.valueOf(b));
    }

    public static void log(Object author, Object object) {
        String message = (object == null ? "null" : object.toString());
        log(author, message);
    }
}