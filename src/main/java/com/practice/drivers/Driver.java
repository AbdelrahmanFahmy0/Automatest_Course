package com.practice.drivers;

import com.practice.utils.bots.ActionsBot;
import com.practice.utils.bots.AssertionsBot;
import com.practice.utils.bots.BrowserBot;
import com.practice.utils.dataReader.PropertyReader;
import com.practice.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class Driver {

    private final WebDriver driver;

    private static final ThreadLocal<WebDriver> CURRENT = new ThreadLocal<>();

    // Constructor to initialize the WebDriver based on the specified browser type
    public Driver() {
        String browser = PropertyReader.getProperty("TargetBrowser");
        LogsManager.info("Initializing driver for browser:", browser);
        Browser browserType = Browser.valueOf(browser.toUpperCase());
        AbstractDriver abstractDriver = browserType.getDriverFactory();
        driver = ThreadGuard.protect(abstractDriver.createDriver());
        CURRENT.set(driver);
        LogsManager.info("Driver initialized:", driver.getClass().getSimpleName());
    }

    // Get the Driver instance created on the current thread, or null if none exists
    public static WebDriver get() {
        return CURRENT.get();
    }

    // Bots
    public ActionsBot action() {
        return new ActionsBot(driver);
    }

    public BrowserBot browser() {
        return new BrowserBot(driver);
    }

    public AssertionsBot check() {
        return new AssertionsBot(driver);
    }

    // Quit the WebDriver instance
    public void quit() {
        browser().quit();
        CURRENT.remove();
    }
}