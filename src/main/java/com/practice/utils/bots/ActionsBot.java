package com.practice.utils.bots;

import com.practice.utils.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class ActionsBot {

    // Variables
    WebDriver driver;
    WaitManager wait;

    public ActionsBot(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitManager(driver);
    }

    // Actions

    /**
     * Clicks on an element located by the given locator.
     *
     * @param locator The locator of the element to click.
     * @return The current instance of ActionsBot for method chaining.
     */
    public ActionsBot click(By locator) {
        wait.fluentWait().until(driver -> {
            scrollToElement(locator);
            driver.findElement(locator).click();
            return true;
        });
        return this;
    }

    /**
     * Checks or unchecks a checkbox or radio button at the specified locator based on the toCheck parameter.
     *
     * @param locator The locator of the checkbox or radio button element.
     * @param toCheck A boolean indicating whether to check (true) or uncheck (false) the element.
     * @return The current instance of ActionsBot for method chaining.
     */
    public ActionsBot check(By locator, boolean toCheck) {
        wait.fluentWait().until(driver -> {
            scrollToElement(locator);
            driver.findElement(locator).click();
            return true;
        });
        return this;
    }

    /**
     * Fills an input field located by the given locator with the specified text.
     *
     * @param locator The locator of the input field.
     * @param input   The text or input to fill in the input field.
     * @return The current instance of ActionsBot for method chaining.
     */
    public ActionsBot fill(By locator, CharSequence... input) {
        wait.fluentWait().until(driver -> {
            scrollToElement(locator);
            driver.findElement(locator).sendKeys(input);
            return true;
        });
        return this;
    }

    /**
     * Clears the text from an input field located by the given locator.
     *
     * @param locator The locator of the input field to clear.
     * @return The current instance of ActionsBot for method chaining.
     */
    public ActionsBot clear(By locator) {
        wait.fluentWait().until(driver -> {
            scrollToElement(locator);
            driver.findElement(locator).clear();
            return true;
        });
        return this;
    }

    /**
     * Moves the mouse pointer over the specified element.
     *
     * @param locator The locator of the element to hover over.
     * @return The current instance of ActionsBot for method chaining.
     */
    public ActionsBot hover(By locator) {
        wait.fluentWait().until(driver -> {
            scrollToElement(locator);
            new Actions(driver).moveToElement(driver.findElement(locator)).perform();
            return true;
        });
        return this;
    }

    /**
     * Drags an element from the source locator and drops it onto the target locator.
     *
     * @param source The locator of the source element.
     * @param target The locator of the target element.
     * @return The current instance of ActionsBot for method chaining.
     */
    public ActionsBot dragAndDrop(By source, By target) {
        wait.fluentWait().until(driver -> {
            scrollToElement(source);
            new Actions(driver)
                    .dragAndDrop(driver.findElement(source), driver.findElement(target))
                    .perform();
            return true;
        });
        return this;
    }

    /**
     * Presses the specified keyboard keys.
     *
     * @param keys The keyboard keys to press.
     * @return The current instance of ActionsBot for method chaining.
     */
    public ActionsBot press(Keys... keys) {
        wait.fluentWait().until(driver -> {
            new Actions(driver).sendKeys(keys).perform();
            return true;
        });
        return this;
    }

    /**
     * Selects an option from a dropdown by its visible text.
     *
     * @param locator The locator of the dropdown element.
     * @param text    The visible text of the option to select.
     * @return The current instance of ActionsBot for method chaining.
     */
    public ActionsBot selectByText(By locator, String text) {
        wait.fluentWait().until(driver -> {
            scrollToElement(locator);
            new Select(driver.findElement(locator)).selectByVisibleText(text);
            return true;
        });
        return this;
    }

    /**
     * Selects an option from a dropdown by its value.
     *
     * @param locator The locator of the dropdown element.
     * @param value   The value of the option to select.
     * @return The current instance of ActionsBot for method chaining.
     */
    public ActionsBot selectByValue(By locator, String value) {
        wait.fluentWait().until(driver -> {
            scrollToElement(locator);
            new Select(driver.findElement(locator)).selectByValue(value);
            return true;
        });
        return this;
    }

    /**
     * Retrieves the text of an element located by the given locator.
     *
     * @param locator The locator of the element.
     * @return The text of the located element.
     */
    public String getText(By locator) {
        return wait.fluentWait().until(driver -> {
                    scrollToElement(locator);
                    return driver.findElement(locator).getText();
                }
        );
    }

    /**
     * Retrieves the value of the specified DOM attribute from an element located by the given locator.
     *
     * @param locator   The locator of the element.
     * @param attribute The name of the DOM attribute to retrieve.
     * @return The value of the specified DOM attribute.
     */
    public String getDomAttribute(By locator, String attribute) {
        return wait.fluentWait().until(driver -> {
                    scrollToElement(locator);
                    return driver.findElement(locator).getDomAttribute(attribute);
                }
        );
    }

    /**
     * Retrieves the value of the specified DOM property from an element located by the given locator.
     *
     * @param locator  The locator of the element.
     * @param property The name of the DOM property to retrieve.
     * @return The value of the specified DOM property.
     */
    public String getDomProperty(By locator, String property) {
        return wait.fluentWait().until(driver -> {
                    scrollToElement(locator);
                    return driver.findElement(locator).getDomProperty(property);
                }
        );
    }

    /**
     * Uploads a file to an input element located by the given locator.
     *
     * @param locator  The locator of the file input element.
     * @param filePath The path to the file to upload (relative to src/test/resources/).
     * @return The current instance of ActionsBot for method chaining.
     */
    public ActionsBot uploadFile(By locator, String filePath) {
        wait.fluentWait().until(driver -> {
            scrollToElement(locator);
            driver.findElement(locator).sendKeys(new File("src/test/resources/" + filePath).getAbsolutePath());
            return true;
        });
        return this;
    }

    /**
     * Scrolls the page to bring the element located by the given locator into view.
     *
     * @param locator The locator of the element.
     * @return The current instance of ActionsBot for method chaining.
     */
    public ActionsBot scrollToElement(By locator) {
        ((JavascriptExecutor) driver).executeScript(
                """ 
                        arguments[0].scrollIntoView({behaviour:"auto",block:"center",inline:"center"});""", driver.findElement(locator));
        return this;
    }
}