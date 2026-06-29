package com.practice.pages.heroku;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.practice.utils.actions.BrowserActions.navigateTo;
import static com.practice.utils.actions.ElementActions.*;
import static com.practice.utils.dataReader.PropertyReader.getProperty;

public class UploadPage {

    WebDriver driver;

    // Constructor
    public UploadPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By chooseFileButton = By.id("file-upload");
    private final By uploadButton = By.id("file-submit");
    private final By successUploadMessage = By.xpath("//h3[text() = 'File Uploaded!']");

    // Actions
    public UploadPage navigate() {
        navigateTo(driver, getProperty("herokuUploadUrl"));
        return this;
    }

    public UploadPage chooseFile(String filePath) {
        uploadFile(driver, chooseFileButton, filePath);
        return this;
    }

    public UploadPage clickUploadButton() {
        click(driver, uploadButton);
        return this;
    }

    // Assertions
    public UploadPage checkFileIsUploaded() {
        boolean successMessageIsDisplayed = isDisplayed(driver, successUploadMessage);
        Assert.assertTrue(successMessageIsDisplayed);
        return this;
    }
}