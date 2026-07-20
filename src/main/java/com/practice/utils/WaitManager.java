package com.practice.utils;

import com.practice.utils.dataReader.PropertyReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WaitManager {

    private final WebDriver driver;

    public WaitManager(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Creates and returns a FluentWait instance configured for the WebDriver.
     * The wait is configured with a timeout based on the DEFAULT_WAIT property,
     * polls every 100 milliseconds, and ignores common Selenium exceptions.
     *
     * @return a FluentWait instance ready for use with the WebDriver
     */
    public FluentWait<WebDriver> fluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Long.parseLong(PropertyReader.getProperty("DEFAULT_WAIT"))))
                .pollingEvery(Duration.ofMillis(100))
                .ignoreAll(getExceptions());

    }

    /**
     * Retrieves a list of exception classes to be ignored during wait operations.
     * These exceptions commonly occur during Selenium operations and should not cause waits to fail.
     *
     * @return an ArrayList containing exception classes to ignore during wait operations
     */
    private List<Class<? extends Throwable>> getExceptions() {
        ArrayList<Class<? extends Throwable>> exceptions = new ArrayList<>();
        exceptions.add(NoSuchElementException.class);
        exceptions.add(StaleElementReferenceException.class);
        exceptions.add(ElementNotInteractableException.class);
        exceptions.add(ElementClickInterceptedException.class);
        exceptions.add(AssertionError.class);
        return exceptions;
    }
}