package com.practice.utils.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;

import static com.practice.utils.WaitManager.waitUntilClickable;
import static com.practice.utils.WaitManager.waitUntilVisible;

public class ElementActions {

    private ElementActions() {
    }

    public static void click(WebDriver driver, By locator) {
        waitUntilClickable(driver, locator);
        scrollToElementJS(driver, locator);
        findElement(driver, locator).click();
    }

    public static void fill(WebDriver driver, By locator, String text) {
        waitUntilVisible(driver, locator);
        scrollToElementJS(driver, locator);
        findElement(driver, locator).sendKeys(text);
    }

    public static void fill(WebDriver driver, By locator, Keys key) {
        waitUntilVisible(driver, locator);
        scrollToElementJS(driver, locator);
        findElement(driver, locator).sendKeys(key);
    }

    public static void uploadFile(WebDriver driver, By locator, String filePath) {
        waitUntilVisible(driver, locator);
        scrollToElementJS(driver, locator);
        findElement(driver, locator).sendKeys(new File("src/test/resources/" + filePath).getAbsolutePath());
    }

    public static String getText(WebDriver driver, By locator) {
        waitUntilVisible(driver, locator);
        scrollToElementJS(driver, locator);
        return findElement(driver, locator).getText();
    }

    public static String getAttributeValue(WebDriver driver, By locator, String attributeName) {
        waitUntilVisible(driver, locator);
        scrollToElementJS(driver, locator);
        return findElement(driver, locator).getAttribute(attributeName);
    }

    public static void dragAndDrop(WebDriver driver, By source, By destination) {
        waitUntilVisible(driver, source);
        waitUntilVisible(driver, destination);
        scrollToElementJS(driver, source);
        new Actions(driver).dragAndDrop(findElement(driver, source),
                findElement(driver, destination)).perform();
    }

    public static boolean isDisplayed(WebDriver driver, By locator) {
        waitUntilVisible(driver, locator);
        return findElement(driver, locator).isDisplayed();
    }

    public static boolean isChecked(WebDriver driver, By locator) {
        waitUntilVisible(driver, locator);
        return findElement(driver, locator).isSelected();
    }

    public static WebElement findElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    public static void scrollToElementJS(WebDriver driver, By locator) {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(""" 
                        arguments[0].scrollIntoView({behaviour:"auto",block:"center",inline:"center"});""", findElement(driver, locator));
    }
}