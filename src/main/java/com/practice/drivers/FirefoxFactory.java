package com.practice.drivers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

public class FirefoxFactory extends AbstractDriver {

    // Options for FirefoxDriver
    private FirefoxOptions getOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-width", "1920");
        options.addArguments("-height", "1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("dom.disable_open_during_load", true);
        options.setAcceptInsecureCerts(true);
        if (com.practice.utils.dataReader.PropertyReader.getProperty("ExecutionType").equalsIgnoreCase("LocalHeadless")) {
            options.addArguments("--headless");
            options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36");
        }
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        return options;
    }

    @Override
    public WebDriver createDriver() {
        return new FirefoxDriver(getOptions());
    }
}