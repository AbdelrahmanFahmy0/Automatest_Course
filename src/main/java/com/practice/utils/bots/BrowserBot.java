package com.practice.utils.bots;

import com.practice.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;

public class BrowserBot {

    // Variables
    private final WebDriver driver;

    public BrowserBot(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Navigates to the specified URL.
     *
     * @param url The URL to navigate to.
     * @return The current instance of BrowserBot for method chaining.
     */
    public BrowserBot navigateTo(String url) {
        LogsManager.info("Navigating to URL:", url);
        driver.navigate().to(url);
        return this;
    }

    /**
     * Refreshes the current page.
     *
     * @return The current instance of BrowserBot for method chaining.
     */
    public BrowserBot refresh() {
        LogsManager.info("Refreshing current browser page.");
        driver.navigate().refresh();
        return this;
    }

    /**
     * Retrieves the title of the current page.
     *
     * @return The current page title.
     */
    public String getTitle() {
        String title = driver.getTitle();
        LogsManager.info("Current page title:", title);
        return title;
    }

    /**
     * Retrieves the URL of the current page.
     *
     * @return The current page URL.
     */
    public String getCurrentUrl() {
        String currentUrl = driver.getCurrentUrl();
        LogsManager.info("Current page url:", currentUrl);
        return currentUrl;
    }

    /**
     * Quits the browser session.
     */
    public void quit() {
        LogsManager.info("Closing browser.");
        driver.quit();
    }
}