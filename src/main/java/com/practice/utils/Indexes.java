package com.practice.utils;

public final class Indexes {

    private Indexes() {
    }

    public static final String USER_DIR = System.getProperty("user.dir") + "/";
    public static final String LOGS_PATH = USER_DIR + "test-output/logs/";
    public static final String DOWNLOADS_PATH = USER_DIR + "src/test/resources/downloads/";
    public static final String TEST_DATA_PATH = USER_DIR + "src/test/resources/test-data/";
    public static final String ALLURE_RESULTS_PATH = USER_DIR + "test-output/reports/allure-results/";
    public static final String SCREENSHOTS_PATH = USER_DIR + "test-output/screenshots/";
    public static final String ALLURE_CONFIGS_PATH = USER_DIR + "allurerc.mjs";
    public static final String REPORT_PATH = USER_DIR + "test-output/reports/allure-report/index.html";
}