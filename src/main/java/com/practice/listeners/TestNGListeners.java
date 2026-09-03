package com.practice.listeners;

import com.practice.drivers.Driver;
import com.practice.drivers.UITest;
import com.practice.utils.ScreenshotsManager;
import com.practice.utils.FileUtils;
import com.practice.utils.Indexes;
import com.practice.utils.dataReader.PropertyReader;
import com.practice.utils.logs.ConsoleOutputCapture;
import com.practice.utils.logs.LogsManager;
import com.practice.utils.report.AllureAttachmentManager;
import com.practice.utils.report.AllureEnvironmentManager;
import com.practice.utils.report.AllureReportGenerator;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
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
        ConsoleOutputCapture.install();
        cleanTestOutputDirectories();
        LogsManager.info("Test output cleanup completed.");
        PropertyReader.loadProperties();
        LogsManager.info("Properties loaded.");
        LogsManager.info("Test execution started.");
        AllureEnvironmentManager.setEnvironmentVariables();
    }

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            ConsoleOutputCapture.startCapture();
            LogsManager.info("Test started:", testResult.getName());
        }
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        WebDriver driver = Driver.get();
        if (method.isTestMethod()) {
            // Check if the test class is annotated with @UITest before taking screenshots
            if (testResult.getInstance().getClass().isAnnotationPresent(UITest.class)) {
                ScreenshotsManager.takeFullPageScreenshot(driver, testResult.getName() + " - Screenshot");
            }
            AllureAttachmentManager.attachLogs(ConsoleOutputCapture.stopCapture());
        }
    }

    public void onTestSuccess(ITestResult result) {
        LogsManager.info("✅ TEST PASSED: " + result.getName() + " (Duration: " + getDuration(result) + " ms)");
    }

    public void onTestFailure(ITestResult result) {
        LogsManager.info("❌ TEST FAILED: " + result.getName() + " (Duration: " + getDuration(result) + " ms)");
    }

    public void onTestSkipped(ITestResult result) {
        LogsManager.info("⏭  TEST SKIPPED: " + result.getName() + " (Duration: " + getDuration(result) + " ms)");
    }

    public void onExecutionFinish() {
        AllureReportGenerator.generateReport();
        if (PropertyReader.getProperty("OpenAllureReportAfterExecution").equalsIgnoreCase("true")) {
            AllureReportGenerator.openReport();
        }
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

    // Cleaning and creating dirs (logs, screenshots, recordings, allure-results)
    private void cleanTestOutputDirectories() {
        FileUtils.cleanDirectory(new File(Indexes.ALLURE_RESULTS_PATH));
        FileUtils.cleanDirectory(new File(Indexes.SCREENSHOTS_PATH));
        LogManager.shutdown();
        FileUtils.forceFileDelete(new File(Indexes.LOGS_PATH + "logs.log"));
    }

    // Getting the current date and time in the format "yyyy-MM-dd HH:mm:ss"
    private String getCurrentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // Helper method to calculate test duration
    private long getDuration(ITestResult result) {
        return result.getEndMillis() - result.getStartMillis();
    }

    private String getStatus(ITestResult result) {
        return switch (result.getStatus()) {
            case ITestResult.SUCCESS -> "PASSED";
            case ITestResult.FAILURE -> "FAILED";
            case ITestResult.SKIP -> "SKIPPED";
            default -> "UNKNOWN";
        };
    }
}