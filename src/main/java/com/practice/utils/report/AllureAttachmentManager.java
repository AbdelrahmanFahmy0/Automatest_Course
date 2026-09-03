package com.practice.utils.report;

import io.qameta.allure.Allure;
import com.practice.utils.logs.LogsManager;

import java.nio.file.Files;
import java.nio.file.Path;

public class AllureAttachmentManager {

    public static void attachScreenshot(String name, String path) {
        try {
            Path screenshot = Path.of(path);
            if (Files.exists(screenshot)) {
                Allure.addAttachment(name, Files.newInputStream(screenshot));
            } else {
                LogsManager.error("Screenshot not found: " + path);
            }
        } catch (Exception e) {
            LogsManager.error("Error attaching screenshot", e.getMessage());
        }
    }

    public static void attachLogs(String logs) {
        Allure.addAttachment("logs", "text/plain", logs, ".log");
    }
}