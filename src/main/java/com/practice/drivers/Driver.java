package com.practice.drivers;

import com.practice.utils.bots.ActionsBot;
import com.practice.utils.bots.AssertionsBot;
import com.practice.utils.bots.BrowserBot;
import com.practice.utils.dataReader.PropertyReader;
import org.openqa.selenium.WebDriver;

public class Driver {

    private final WebDriver driver;

    // Constructor to initialize the WebDriver based on the specified browser type
    public Driver() {
        String browser = PropertyReader.getProperty("TargetBrowser");
        Browser browserType = Browser.valueOf(browser.toUpperCase());
        AbstractDriver abstractDriver = browserType.getDriverFactory();
        driver = abstractDriver.createDriver();
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

    // Get the WebDriver instance
    public WebDriver get() {
        return driver;
    }

    // Quit the WebDriver instance
    public void quit() {
        driver.quit();
    }
}