package com.practice.utils.bots;

import com.practice.utils.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AssertionsBot {

    // Variables
    private final WaitManager wait;
    private final ActionsBot actions;

    public AssertionsBot(WebDriver driver) {
        this.wait = new WaitManager(driver);
        this.actions = new ActionsBot(driver);
    }

    /**
     * Verifies that the specified element is displayed.
     *
     * @param locator The locator of the element.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot exists(By locator) {
        wait.fluentWait().until(driver -> {
            actions.scrollToElement(locator);
            Assert.assertTrue(driver.findElement(locator).isDisplayed(), "Element is not displayed: " + locator);
            return true;
        });
        return this;
    }

    /**
     * Verifies that the specified element does not exist.
     *
     * @param locator The locator of the element.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot notExists(By locator) {
        wait.fluentWait().until(driver -> {
            ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            Assert.assertTrue(driver.findElements(locator).isEmpty(), "Element is displayed: " + locator);
            return true;
        });
        return this;
    }

    /**
     * Verifies the page title.
     *
     * @param expectedTitle The expected page title.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot checkTitle(String expectedTitle) {
        wait.fluentWait().until(driver -> {
            String actualTitle = driver.getTitle();
            Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match expected title: " + expectedTitle + " but found: " + actualTitle);
            return true;
        });
        return this;
    }

    /**
     * Verifies the page url.
     *
     * @param expectedUrl The expected page url.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot checkUrl(String expectedUrl) {
        wait.fluentWait().until(driver -> {
            String actualUrl = driver.getCurrentUrl();
            Assert.assertEquals(actualUrl, expectedUrl, "Page url does not match expected url: " + expectedUrl + " but found: " + actualUrl);
            return true;
        });
        return this;
    }

    /**
     * Verifies the text of the specified element.
     *
     * @param locator      The locator of the element.
     * @param expectedText The expected text of the element.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot checkTextEquals(By locator, String expectedText) {
        wait.fluentWait().until(driver -> {
            actions.scrollToElement(locator);
            String actualText = driver.findElement(locator).getText();
            Assert.assertEquals(actualText, expectedText, "Element text does not match: " + locator + " expected: " + expectedText + " but found: " + actualText);
            return true;
        });
        return this;
    }

    /**
     * Verifies that the attribute value of the specified element equals the expected value.
     *
     * @param locator       The locator of the element.
     * @param attribute     The attribute to verify.
     * @param expectedValue The expected value of the attribute.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot checkAttributeEquals(By locator, String attribute, String expectedValue) {
        wait.fluentWait().until(driver -> {
            actions.scrollToElement(locator);
            String actualValue = driver.findElement(locator).getDomAttribute(attribute);
            Assert.assertEquals(actualValue, expectedValue, "Element attribute does not match: " + locator + " expected: " + expectedValue + " but found: " + actualValue);
            return true;
        });
        return this;
    }

    /**
     * Verifies that the attribute value of the specified element contains the expected value.
     *
     * @param locator       The locator of the element.
     * @param attribute     The attribute to verify.
     * @param expectedValue The expected value that should be contained in the attribute.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot checkAttributeContains(By locator, String attribute, String expectedValue) {
        wait.fluentWait().until(driver -> {
            actions.scrollToElement(locator);
            String actualValue = driver.findElement(locator).getDomAttribute(attribute);
            Assert.assertTrue(actualValue.contains(expectedValue), "Element attribute does not contain expected value: " + locator + " expected to contain: " + expectedValue + " but found: " + actualValue);
            return true;
        });
        return this;
    }

    /**
     * Verifies that the specified checkbox is checked.
     *
     * @param locator The locator of the checkbox element.
     * @return The current instance of AssertionsBot for method chaining.
     */
    public AssertionsBot checkCheckboxIsChecked(By locator) {
        wait.fluentWait().until(driver -> {
            actions.scrollToElement(locator);
            boolean isChecked = driver.findElement(locator).isSelected();
            Assert.assertTrue(isChecked, "Checkbox is not checked: " + locator);
            return true;
        });
        return this;
    }
}