package com.practice.utils.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class BrowserActions {

    /**
     * Retrieves the current URL of the browser.
     *
     * @return the current URL as a String
     */
    public static String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    /**
     * Retrieves the current page title.
     *
     * @return the current page title as a String
     */
    public static String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    /**
     * Navigates to a specific URL in the browser.
     *
     * @param url the URL to navigate to
     */
    public static void navigateTo(WebDriver driver, String url) {
        driver.navigate().to(url);
    }

    /**
     * Refreshes the current page in the browser.
     */
    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    /**
     * Closes the current browser window.
     */
    public static void closeCurrentWindow(WebDriver driver) {
        driver.close();
    }

    /**
     * Opens a new browser window.
     */
    public static void openNewWindow(WebDriver driver) {
        driver.switchTo().newWindow(WindowType.WINDOW);
    }
}