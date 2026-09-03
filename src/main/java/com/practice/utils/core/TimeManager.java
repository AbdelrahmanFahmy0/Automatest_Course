package com.practice.utils.core;

public class TimeManager {

    /**
     * Returns a human-readable timestamp (e.g. {@code 2024-01-31_02-15-30}), used to
     * name screenshot, log, and report files.
     */
    public static String getTimestamp() {
        return new java.text.SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new java.util.Date());
    }

    /**
     * Returns the current epoch time in milliseconds as a string, used to generate a
     * unique identifier for a piece of data.
     */
    public static String getSimpleTimestamp() {
        return Long.toString(System.currentTimeMillis());
    }
}