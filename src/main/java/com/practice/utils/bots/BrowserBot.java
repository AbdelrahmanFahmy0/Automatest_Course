package com.practice.utils.bots;

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
        driver.navigate().to(url);
        return this;
    }

    /**
     * Refreshes the current page.
     *
     * @return The current instance of BrowserBot for method chaining.
     */
    public BrowserBot refresh() {
        driver.navigate().refresh();
        return this;
    }

    /**
     * Retrieves the title of the current page.
     *
     * @return The current page title.
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * Retrieves the URL of the current page.
     *
     * @return The current page URL.
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Quits the browser session.
     */
    public void quit() {
        driver.quit();
    }
}