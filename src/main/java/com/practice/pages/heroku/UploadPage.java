package com.practice.pages.heroku;

import com.practice.drivers.Driver;
import org.openqa.selenium.By;

import static com.practice.utils.dataReader.PropertyReader.getProperty;

public class UploadPage {

    Driver driver;

    // Constructor
    public UploadPage(Driver driver) {
        this.driver = driver;
    }

    // Locators
    private final By chooseFileButton = By.id("file-upload");
    private final By uploadButton = By.id("file-submit");
    private final By successUploadMessage = By.xpath("//h3[text() = 'File Uploaded!']");

    // Actions
    public UploadPage navigate() {
        driver.browser().navigateTo(getProperty("herokuUploadUrl"));
        return this;
    }

    public UploadPage chooseFile(String filePath) {
        driver.action().uploadFile(chooseFileButton, filePath);
        return this;
    }

    public UploadPage clickUploadButton() {
        driver.action().click(uploadButton);
        return this;
    }

    // Assertions
    public UploadPage checkFileIsUploaded() {
        driver.check().exists(successUploadMessage);
        return this;
    }
}