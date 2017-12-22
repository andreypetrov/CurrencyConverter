package com.petrovdevelopment.paytmcurrencyconverter.platform.utils;

/**
 * Convenience logger on top of Android's android.util.Log
 */
public class Log {

    /**
     * Log a message. Convenience method, which allow us not to worry about declaring tags everywhere
     * @param author - usually the class you are calling from. It will be written in the console
     * @param message - message to be printed in the android logcat console
     */
    public static void log(Object author, String message) {
        android.util.Log.i(author.getClass().getSimpleName(), message);
    }
}