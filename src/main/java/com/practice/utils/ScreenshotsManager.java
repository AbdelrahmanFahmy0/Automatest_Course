package com.practice.utils;

import com.practice.utils.logs.LogsManager;
import com.practice.utils.report.AllureAttachmentManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenshotsManager {

    /**
     * Captures a full-page screenshot of the current browser window, saves it under
     * {@link Indexes#SCREENSHOTS_PATH} (named with {@code screenshotName} and a timestamp),
     * and attaches it to the Allure report.
     *
     * @param driver         the WebDriver to capture the screenshot from
     * @param screenshotName the base name used for the saved file and the Allure attachment
     */
    public static void takeFullPageScreenshot(WebDriver driver, String screenshotName) {
        try {
            // Capture screenshot using TakesScreenshot
            File screenshotSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Save screenshot to a file if needed
            File screenshotFile = new File(Indexes.SCREENSHOTS_PATH + screenshotName + "-" + TimeManager.getTimestamp() + ".png");
            FileUtils.copyFile(screenshotSrc, screenshotFile);
            // Attach the screenshot to Allure report
            AllureAttachmentManager.attachScreenshot(screenshotName, screenshotFile.getAbsolutePath());
            LogsManager.info("Capturing Screenshot Succeeded");
        } catch (Exception e) {
            LogsManager.error("Failed to Capture Screenshot " + e.getMessage());
        }
    }

    /**
     * Captures a screenshot of a single element, using the element's accessible name to
     * name both the saved file (under {@link Indexes#SCREENSHOTS_PATH}, with a timestamp)
     * and the Allure attachment.
     *
     * @param driver  the WebDriver used to locate the element
     * @param locator the locator of the element to screenshot
     */
    public static void takeElementScreenshot(WebDriver driver, By locator) {
        try {
            // Capture screenshot using TakesScreenshot
            String ariaName = driver.findElement(locator).getAccessibleName();
            File screenshotSrc = driver.findElement(locator).getScreenshotAs(OutputType.FILE);
            // Save screenshot to a file if needed
            File screenshotFile = new File(Indexes.SCREENSHOTS_PATH + ariaName + "-" + TimeManager.getTimestamp() + ".png");
            FileUtils.copyFile(screenshotSrc, screenshotFile);
            // Attach the screenshot to Allure report
            AllureAttachmentManager.attachScreenshot(ariaName, screenshotFile.getAbsolutePath());
            LogsManager.info("Capturing Screenshot Succeeded");
        } catch (Exception e) {
            LogsManager.error("Failed to Capture Element Screenshot", e.getMessage());
        }
    }
}