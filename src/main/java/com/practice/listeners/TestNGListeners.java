package com.practice.listeners;

import com.practice.utils.FileUtils;
import com.practice.utils.Indexes;
import com.practice.utils.dataReader.PropertyReader;
import com.practice.utils.logs.LogsManager;
import org.apache.logging.log4j.LogManager;
import org.testng.*;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestNGListeners implements ISuiteListener, IExecutionListener, IInvokedMethodListener, ITestListener {

    public void onStart(ISuite suite) {
        suite.getXmlSuite().setName("Automatest Test Suite");
        LogsManager.info("════════════════════════════════════════════════════");
        LogsManager.info("Suite:", suite.getName());
        LogsManager.info("Start time:", getCurrentDateTime());
        LogsManager.info("════════════════════════════════════════════════════");
    }

    public void onExecutionStart() {
        cleanTestOutputDirectories();
        LogsManager.info("Test output cleanup completed.");
        PropertyReader.loadProperties();
        LogsManager.info("Execution properties loaded.");
        LogsManager.info("Test execution started.");
    }

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            LogsManager.info("Test started:", testResult.getName());
        }
    }

    public void onExecutionFinish() {
        LogsManager.info("Test execution finished.");
    }

    @Override
    public void onFinish(ITestContext context) {
        LogsManager.info("╔══════════════════════════════════════════════╗");
        LogsManager.info("║  Suite:", context.getSuite().getName());
        LogsManager.info("║  End time:", getCurrentDateTime());
        LogsManager.info("║  Summary:",
                "passed=", String.valueOf(context.getPassedTests().size()) + ",",
                "failed=", String.valueOf(context.getFailedTests().size()) + ",",
                "skipped=", String.valueOf(context.getSkippedTests().size()));
        LogsManager.info("╚══════════════════════════════════════════════╝");
    }

    // Cleaning and creating dirs (logs, screenshots, recordings,allure-results)
    private void cleanTestOutputDirectories() {
        LogManager.shutdown();
        FileUtils.forceFileDelete(new File(Indexes.LOGS_PATH + "logs.log"));
    }

    // Getting the current date and time in the format "yyyy-MM-dd HH:mm:ss"
    private String getCurrentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}