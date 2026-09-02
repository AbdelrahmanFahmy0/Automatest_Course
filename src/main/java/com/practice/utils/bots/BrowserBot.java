package com.practice.utils.bots;

import com.practice.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;

import java.util.Set;

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

    /**
     * Navigates back to the previous page.
     *
     * @return The current instance of BrowserBot for method chaining.
     */
    public BrowserBot back() {
        LogsManager.info("Navigating back to previous page.");
        driver.navigate().back();
        return this;
    }

    /**
     * Navigates forward to the next page.
     *
     * @return The current instance of BrowserBot for method chaining.
     */
    public BrowserBot forward() {
        LogsManager.info("Navigating forward to next page.");
        driver.navigate().forward();
        return this;
    }

    /**
     * Closes the current window or tab.
     *
     * @return The current instance of BrowserBot for method chaining.
     */
    public BrowserBot closeCurrentWindow() {
        LogsManager.info("Closing current window.");
        driver.close();
        return this;
    }

    /**
     * Retrieves the HTML source code of the current page.
     *
     * @return The page source as a string.
     */
    public String getPageSource() {
        String pageSource = driver.getPageSource();
        LogsManager.info("Retrieved page source.");
        return pageSource;
    }

    /**
     * Switches to a window by its handle.
     *
     * @param windowHandle The handle of the window to switch to.
     * @return The current instance of BrowserBot for method chaining.
     */
    public BrowserBot switchToWindow(String windowHandle) {
        LogsManager.info("Switching to window: " + windowHandle);
        driver.switchTo().window(windowHandle);
        return this;
    }

    /**
     * Retrieves all window handles currently open.
     *
     * @return A set of window handles.
     */
    public Set<String> getWindowHandles() {
        Set<String> handles = driver.getWindowHandles();
        LogsManager.info("Retrieved window handles. Total windows: " + handles.size());
        return handles;
    }
}