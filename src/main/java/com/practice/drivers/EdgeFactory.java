package com.practice.drivers;

import com.practice.utils.dataReader.PropertyReader;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class EdgeFactory extends AbstractDriver {

    // Options for EdgeDriver
    private EdgeOptions getOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--window-size=1920,1080");
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        options.setAcceptInsecureCerts(true);
        if (PropertyReader.getProperty("ExecutionType").equalsIgnoreCase("LocalHeadless")) {
            options.addArguments("--headless");
            options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36");
        }
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        return options;
    }

    @Override
    public WebDriver createDriver() {
        return new EdgeDriver(getOptions());
    }
}